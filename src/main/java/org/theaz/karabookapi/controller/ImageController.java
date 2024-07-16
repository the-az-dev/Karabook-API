package org.theaz.karabookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.theaz.karabookapi.dto.ImageChangeDTO;
import org.theaz.karabookapi.entity.Image;
import org.theaz.karabookapi.service.ImageService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    /**
     * Повертає список всіх картинок
     * Не рекомендується
     * Краще використовуйте /get/all/ByCategory/
     * @return
     */
    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(this.imageService.getAllImages(), HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    /**
     * Повертає список картинок за списком їх айді
     * @param ids передаються в строковому вигляді, розділені комою
     * @return
     */
    @GetMapping("/get/all/ByIds/")
    public ResponseEntity<?> getAllByIds(@RequestParam(value = "value", required = true) String ids) {
        try {
            List<Image> images = new ArrayList<Image>();
            String[] imageIds = ids.split(",");
            for (String imageId : imageIds) {
                Image image = this.imageService.getImageByImageId(Long.parseLong(imageId));
                images.add(image);
            }
            return new ResponseEntity<>(images, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.CONFLICT);
        }
    }

    // For User

    /**
     * Повертає список картинок для категорії за її айді
     * Не повертає daily картинки
     * @param categoryId
     * @return
     */
    @GetMapping("/get/all/ByCategory/")
    public ResponseEntity<?> getAllImagesByEnabledAndCategoryIdAndIsNotDaily(@RequestParam(value = "value", required = true) Long categoryId) {
        try {
            return new ResponseEntity<>(this.imageService.getAllImagesByEnabledAndCategoryIdAndIsDaily(true, categoryId, false), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.CONFLICT);
        }
    }

    /**
     * Повертає список дейлі картинок
     * Повертає тільки ті, що enabled
     * @return
     */
    @GetMapping("/get/all/ByDaily/forUser")
    public ResponseEntity<?> getAllImagesByDailyAndEnabled() {
        try {
            return new ResponseEntity<>(this.imageService.getAllImagesByEnabledAndIsDaily(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.CONFLICT);
        }
    }

    /**
     * Повертає дто змін для всіх картинок
     * @return
     */
    @GetMapping("/get/all/ByModifiedDate")
    public ResponseEntity<?> getAllImageChangesByModifiedDate() {
        try {
            List<ImageChangeDTO> imagesChanges = this.imageService.getAllImageChanges();
            return new ResponseEntity<>(imagesChanges, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.CONFLICT);
        }
    }

    // For Admin

    @GetMapping("/get/all/ByDailyAndCategory/")
    public ResponseEntity<?> getAllImagesByDailyAndCategoryId(@RequestParam(value = "value", required = true) Long categoryId) {
        try {
            return new ResponseEntity<>(this.imageService.getAllImagesByCategoryIdAndIsDaily(categoryId), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.CONFLICT);
        }
    }

    /**
     * Повертає ВСІ daily картинки
     * @return
     */
    @GetMapping("/get/all/ByDaily/forAdmin")
    public ResponseEntity<?> getAllImagesByDaily() {
        try {
            return new ResponseEntity<>(this.imageService.getAllImagesByIsDaily(true), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.CONFLICT);
        }
    }

//    @PostMapping(value = "/add", consumes = {"*/*"})
//    public ResponseEntity<?> save(@RequestBody Image image){
//        try{
//            this.imageService.save(image);
//            return new ResponseEntity<>("Image added!", HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
//        }
//    }

//    @PutMapping(value = "/update", consumes = {"*/*"})
//    public ResponseEntity<?> update(@RequestBody ImageUpdateDTO image){
//        try{
//            Image exitingImage = this.imageService.getImageByImageId(image.getImageId());
//            return new ResponseEntity<>(this.imageService.update(exitingImage, image), HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
//        }
//    }
//
//    @DeleteMapping("/delete/")
//    public ResponseEntity<?> delete(@RequestParam(value = "id", required = true) Long imageId){
//        this.imageService.delete(imageId);
//        return new ResponseEntity<>("Image deleted!", HttpStatus.OK);
//    }

}
