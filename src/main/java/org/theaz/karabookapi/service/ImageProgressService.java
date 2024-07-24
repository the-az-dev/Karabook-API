package org.theaz.karabookapi.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.theaz.karabookapi.dto.ImageProgressUpdateDTO;
import org.theaz.karabookapi.entity.ImageProgress;
import org.theaz.karabookapi.repository.ImageProgressRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ImageProgressService {
    @Autowired
    private ImageProgressRepository imageProgressRepository;


    public List<ImageProgress> getAllImageProgress() {
        return this.imageProgressRepository.findAll();
    }

    public ImageProgress getImageProgressById(Long imageProgressId) {
        return this.imageProgressRepository.findByImageProgressId(imageProgressId);
    }

    public List<ImageProgress> getImageProgressByUserId(Long userId) {
        return this.imageProgressRepository.findAllByUserId(userId);
    }

    public void save(ImageProgress imageProgress){
        this.imageProgressRepository.save(imageProgress);
    }

    public ImageProgress update(ImageProgress exitingImageProgress, ImageProgressUpdateDTO imageProgress){
        Date newDate = new Date();

        exitingImageProgress.setImageProgressId(
                imageProgress.getImageProgressId() != null
                        ? imageProgress.getImageProgressId()
                        : exitingImageProgress.getImageProgressId()
        );

        exitingImageProgress.setUserId(
                imageProgress.getUserId() != null
                        ? imageProgress.getUserId()
                        : exitingImageProgress.getUserId()
        );

        exitingImageProgress.setImageId(
                imageProgress.getImageId() != null
                        ? imageProgress.getImageId()
                        : exitingImageProgress.getImageId()
        );

        exitingImageProgress.setCompletedImageParts(
                imageProgress.getCompletedImageParts() != null
                        ? imageProgress.getCompletedImageParts()
                        : exitingImageProgress.getCompletedImageParts()
        );

        exitingImageProgress.setIsCompleted(
                imageProgress.getIsCompleted() != null
                        ? imageProgress.getIsCompleted()
                        : exitingImageProgress.getIsCompleted()
        );

        exitingImageProgress.setModifiedDate(newDate);

        this.imageProgressRepository.save(exitingImageProgress);
        return exitingImageProgress;
    }

    public void deleteById(Long imageProgressId){
        this.imageProgressRepository.deleteByImageProgressId(imageProgressId);
    }

    public void deleteByImageId(Long imageProgressId){
        this.imageProgressRepository.deleteByImageId(imageProgressId);
    }

    public void deleteByUserId(Long userId){
        this.imageProgressRepository.deleteByUserId(userId);
    }

    public void deleteByUserIdAndImageId(Long imageId, Long userId){
        this.imageProgressRepository.deleteByImageIdAndUserId(imageId, userId);
    }
}
