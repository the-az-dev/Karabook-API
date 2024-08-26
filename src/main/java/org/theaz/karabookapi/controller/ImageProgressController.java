package org.theaz.karabookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.theaz.karabookapi.dto.change.ImageProgressChangeDTO;
import org.theaz.karabookapi.dto.response.ImageProgressResponseDTO;
import org.theaz.karabookapi.dto.update.ImageProgressUpdateDTO;
import org.theaz.karabookapi.entity.ImageProgress;
import org.theaz.karabookapi.service.ImageProgressService;

import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("/api/image/progress")
public class ImageProgressController {
    @Autowired
    private ImageProgressService imageProgressService;

    @Value("${dev.static.token}")
    private String staticDevToken;

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll(@RequestHeader(value = "dev_access_token", required = false) String devAccessToken) {
        try {
            if(staticDevToken.equals(devAccessToken)) {
                return new ResponseEntity<>(this.imageProgressService.getAllImageProgress(), HttpStatus.OK);
            } else throw new Exception("Need to set dev_access_token");
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get/all/ByIds/")
    public ResponseEntity<?> getAllById(@RequestParam(value = "value", required = true) String ids) {
        try {
            List<ImageProgress> imageProgresses = new ArrayList<ImageProgress>();
            if (ids.split(",") == null) {
                ImageProgress imageProgress = this.imageProgressService.getImageProgressById(Long.parseLong(ids));
                if (imageProgress == null)
                    return new ResponseEntity<>("Image progress not found!", HttpStatus.NOT_FOUND);
                else
                    imageProgresses.add(imageProgress);
            } else {
                String[] imageProgressIds = ids.split(",");
                for (String id : imageProgressIds) {
                    ImageProgress imageProgress = this.imageProgressService.getImageProgressById(Long.parseLong(id));
                    if (imageProgress == null)
                        continue;
                    else
                        imageProgresses.add(imageProgress);
                }
                if (imageProgressIds.length == 0) {
                    return new ResponseEntity<>("Image progesses not found!", HttpStatus.NOT_FOUND);
                }
            }
            return new ResponseEntity<>(imageProgresses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get/all/ByUserId/")
    public ResponseEntity<?> getAllByUserId(@RequestParam(value = "value", required = true) String id) {
        try {
            return new ResponseEntity<>(this.imageProgressService.getImageProgressByUserId(Long.parseLong(id)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get/all/ByModifiedDateAndUserId/")
    public ResponseEntity<?> getAllImagesByModifiedDateAndUserId(
            @RequestParam(value = "value", required = true) Long userId) {
        try {
            List<ImageProgress> imageProgressesAll = this.imageProgressService.getImageProgressByUserId(userId);
            List<Map<Long, ImageProgressResponseDTO>> imageProgressChanges = new ArrayList<Map<Long, ImageProgressResponseDTO>>();
            for (ImageProgress imageProgress : imageProgressesAll) {

                Instant instant = imageProgress.getModifiedDate().toInstant();
                long milliseconds = instant.toEpochMilli();

                ImageProgressResponseDTO changeDTO = new ImageProgressResponseDTO();
                changeDTO.imageId = imageProgress.getImageId();
                changeDTO.completedImageParts = imageProgress.getCompletedImageParts();
                changeDTO.isCompleted = imageProgress.getIsCompleted();
                changeDTO.modifiedDate = milliseconds;

                Map<Long, ImageProgressResponseDTO> imageProgressResponse = new HashMap<Long, ImageProgressResponseDTO>();
                imageProgressResponse.put(imageProgress.getUserId(), changeDTO);
                imageProgressChanges.add(imageProgressResponse);
            }
            return new ResponseEntity<>(imageProgressChanges, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get/all/ByModifiedDate")
    public ResponseEntity<?> getAllImagesByModifiedDate() {
        try {
            List<ImageProgress> imageProgressesAll = this.imageProgressService.getAllImageProgress();
            List<ImageProgressChangeDTO> imageProgresses = new ArrayList<ImageProgressChangeDTO>();
            for (ImageProgress imageProgress : imageProgressesAll) {
                Instant instant = imageProgress.getModifiedDate().toInstant();
                long milliseconds = instant.toEpochMilli();

                ImageProgressChangeDTO changeDTO = new ImageProgressChangeDTO();
                changeDTO.imageProgressId = imageProgress.getImageProgressId();
                changeDTO.modifiedDate = milliseconds;

                imageProgresses.add(changeDTO);
            }
            return new ResponseEntity<>(imageProgresses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public void save(@RequestBody ImageProgress imageProgress) {
        this.imageProgressService.save(imageProgress);
    }

    @PutMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public void update(@RequestBody ImageProgressUpdateDTO imageProgress) {
        ImageProgress exitingImageProgress = this.imageProgressService
                .getImageProgressById(imageProgress.getImageProgressId());
        this.imageProgressService.update(exitingImageProgress, imageProgress);
    }

    @DeleteMapping("/delete/ById")
    public void delete(@RequestParam(value = "id", required = true) Long imageProgressId) {
        this.imageProgressService.deleteById(imageProgressId);
    }

    @DeleteMapping("/delete/ByUserIdAndImageId")
    public void deleteByUserIdAndImageId(@RequestParam(value = "userId", required = true) Long userId, @RequestParam(value = "imageId", required = true) Long imageId) {
        this.imageProgressService.deleteByUserIdAndImageId(imageId, userId);
    }

    @DeleteMapping("/delete/ByUserId")
    public void deleteByUserId(@RequestParam(value = "userId", required = true) Long userId) {
        this.imageProgressService.deleteByUserId(userId);
    }

    @DeleteMapping("/delete/ByImageId")
    public void deleteByImageId(@RequestParam(value = "imageId", required = true) Long userId) {
        this.imageProgressService.deleteByImageId(userId);
    }
}
