package org.theaz.karabookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.theaz.karabookapi.dto.change.ImageChangeDTO;
import org.theaz.karabookapi.dto.update.ImageUpdateDTO;
import org.theaz.karabookapi.entity.Image;
import org.theaz.karabookapi.service.ImageService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @Value("${dev.static.token}")
    private String staticDevToken;

    /**
     * Повертає список всіх картинок
     * Не рекомендується
     * Краще використовуйте /get/all/ByCategory/
     * 
     * @return
     */
    @GetMapping("/get/all")
    public ResponseEntity<?> getAll(@RequestHeader(value = "dev_access_token", required = false) String devAccessToken) {
        try {
            return new ResponseEntity<>(this.imageService.getAllImages(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Повертає список картинок за списком їх айді
     * 
     * @param ids передаються в строковому вигляді, розділені комою
     * @return
     */
    @GetMapping("/get/all/ByIds/")
    public ResponseEntity<?> getAllByIds(@RequestParam(value = "value", required = true) String ids) {
        try {
            List<Image> images = new ArrayList<Image>();
            if (ids.split(",") == null) {
                Image image = this.imageService.getImageByImageId(Long.parseLong(ids));
                images.add(image);
            } else {
                String[] imageIds = ids.split(",");
                for (String id : imageIds) {
                    Image image = this.imageService.getImageByImageId(Long.parseLong(id));
                    images.add(image);
                }
            }
            return new ResponseEntity<>(images, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.CONFLICT);
        }
    }

    // For User

    /**
     * Повертає список картинок для категорії за її айді
     * Не повертає daily картинки
     * 
     * @param categoryId
     * @return
     */
    @GetMapping("/get/all/ByCategory/")
    public ResponseEntity<?> getAllImagesByEnabledAndCategoryIdAndIsNotDaily(
            @RequestParam(value = "value", required = true) Long categoryId) {
        try {
            return new ResponseEntity<>(
                    this.imageService.getAllImagesByEnabledAndCategoryIdAndIsDaily(true, categoryId, false),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    /**
     * Повертає список дейлі картинок
     * Повертає тільки ті, що enabled
     * 
     * @return
     */
    @GetMapping("/get/all/ByDaily/forUser")
    public ResponseEntity<?> getAllImagesByDailyAndEnabled() {
        try {
            return new ResponseEntity<>(this.imageService.getAllImagesByEnabledAndIsDaily(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    /**
     * Повертає дто змін для всіх картинок
     * 
     * @return
     */
    @GetMapping("/get/all/ByModifiedDate")
    public ResponseEntity<?> getAllImageChangesByModifiedDate() {
        try {
            List<Image> imagesChanges = this.imageService.getAllImages();
            List<ImageChangeDTO> imageChangeDTOs = new ArrayList<ImageChangeDTO>();

            for (Image image : imagesChanges) {

                Instant instant = image.getModifiedDate().toInstant();
                long milliseconds = instant.toEpochMilli();

                ImageChangeDTO imageChangeDTO = new ImageChangeDTO();
                imageChangeDTO.categoryId = image.getCategoryId();
                imageChangeDTO.imageId = image.getImageId();
                imageChangeDTO.isDaily = image.getIsDaily();
                imageChangeDTO.modifiedDate = milliseconds;
                imageChangeDTO.sort = image.getSort();
                imageChangeDTO.tag = image.getTag();
                imageChangeDTO.achivementsIds = image.getAchivementsIds();

                imageChangeDTOs.add(imageChangeDTO);
            }

            return new ResponseEntity<>(imageChangeDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    // For Admin

    @GetMapping("/get/all/ByDailyAndCategory/")
    public ResponseEntity<?> getAllImagesByDailyAndCategoryId(
            @RequestParam(value = "value", required = true) Long categoryId, @RequestHeader(value = "dev_access_token", required = false) String devAccessToken) {
        try {
            return new ResponseEntity<>(this.imageService.getAllImagesByCategoryIdAndIsDaily(categoryId),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get/all/ByDaily/forAdmin")
    public ResponseEntity<?> getAllImagesByDaily(@RequestHeader(value = "dev_access_token", required = false) String devAccessToken) {
        try {
            return new ResponseEntity<>(this.imageService.getAllImagesByIsDaily(true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public void save(@RequestBody Image image, @RequestHeader(value = "dev_access_token", required = false) String devAccessToken) {
        try {
            Date currentDate = new Date();
            image.setModifiedDate(currentDate);
            this.imageService.save(image);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @PutMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public void update(@RequestBody ImageUpdateDTO image, @RequestHeader(value = "dev_access_token", required = false) String devAccessToken) {
        try {
            Image exitingImage = this.imageService.getImageByImageId(image.getImageId());
            this.imageService.update(exitingImage, image);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @DeleteMapping("/delete/")
    public void delete(@RequestParam(value = "id", required = true) Long imageId, @RequestHeader(value = "dev_access_token", required = false) String devAccessToken) {
        this.imageService.delete(imageId);
    }
}
