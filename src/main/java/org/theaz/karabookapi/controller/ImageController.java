package org.theaz.karabookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.theaz.karabookapi.dto.ImageChangeDTO;
import org.theaz.karabookapi.entity.Image;
import org.theaz.karabookapi.service.ImageService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(this.imageService.getAllImages(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get/all/ByIds/")
    public ResponseEntity<?> getAllById(@RequestParam(value = "value", required = true) String ids) {
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

    @GetMapping("/get/all/ByCategory/")
    public ResponseEntity<?> getAllImagesByEnabledAndCategoryIdAndIsNotDaily(@RequestParam(value = "value", required = true) Long categoryId) {
        try {
            return new ResponseEntity<>(this.imageService.getAllImagesByEnabledAndCategoryIdAndIsDaily(true, categoryId, false), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get/all/ByDaily/forUser")
    public ResponseEntity<?> getAllImagesByDailyAndEnabled() {
        try {
            return new ResponseEntity<>(this.imageService.getAllImagesByEnabledAndIsDaily(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get/all/ByModifiedDate")
    public ResponseEntity<?> getAllImagesByModifiedDate() {
        try {
            List<Image> images = this.imageService.getAllImages();
            List<ImageChangeDTO> imageChanges = new ArrayList<ImageChangeDTO>();
            for (Image image : images) {
                Instant instant = image.getModifiedDate().toInstant();
                long milliseconds = instant.toEpochMilli();
                ImageChangeDTO imageChangeDTO = new ImageChangeDTO();
                imageChangeDTO.imageId = image.getImageId();
                imageChangeDTO.modifiedDate = String.valueOf(milliseconds);
                imageChanges.add(imageChangeDTO);
            }
            return new ResponseEntity<>(imageChanges, HttpStatus.OK);
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

    @GetMapping("/get/all/ByDaily/forAdmin")
    public ResponseEntity<?> getAllImagesByDaily() {
        try {
            return new ResponseEntity<>(this.imageService.getAllImagesByIsDaily(true), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "/add", consumes = {"*/*"})
    public ResponseEntity<?> save(@ModelAttribute Image image){
        this.imageService.save(image);
        return new ResponseEntity<>("Image added!", HttpStatus.OK);
    }

}
