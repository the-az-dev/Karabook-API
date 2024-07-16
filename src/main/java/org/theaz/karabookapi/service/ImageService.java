package org.theaz.karabookapi.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.theaz.karabookapi.dto.ImageChangeDTO;
import org.theaz.karabookapi.dto.ImageUpdateDTO;
import org.theaz.karabookapi.entity.Image;
import org.theaz.karabookapi.repository.ImageRepository;

import java.util.List;

@Service
@Transactional
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public List<ImageChangeDTO> getAllImageChanges(){
        List<ImageChangeDTO> result = imageRepository.findAll().stream().map( (Image image) -> new ImageChangeDTO(
                image.getImageId(),
                image.getModifiedDate().getTime(),
                image.getCategoryId(),
                image.getIsDaily(),
                image.getSort()
                )).toList();
        return result;
    }

    public Image getImageByImageId(Long imageId) {
        return imageRepository.findImageByImageId(imageId);
    }

    public List<Image> getAllImagesByEnabledAndCategoryIdAndIsDaily(Boolean enabled, Long categoryId, Boolean isDaily) {
        return imageRepository.findByEnabledAndCategoryIdAndIsDaily(enabled, categoryId, isDaily);
    }

    public List<Image> getAllImagesByIsDaily(Boolean isDaily) {
        return imageRepository.findByIsDaily(isDaily);
    }

    public List<Image> getAllImagesByEnabledAndIsDaily() {
        return imageRepository.findByEnabledAndIsDaily(true, true);
    }

    public List<Image> getAllImagesByCategoryIdAndIsDaily(Long categoryId) {
        return imageRepository.findByCategoryIdAndIsDaily(categoryId, true);
    }

    public Image save(Image image) {
        return imageRepository.save(image);
    }

    public Image update(Image existingImage, ImageUpdateDTO image) {
        existingImage.setImageId(
                image.getImageId() != null
                        ? image.getImageId()
                        : existingImage.getImageId()
        );

        existingImage.setImageRawData(
                image.getImageRawData() != null
                        ? image.getImageRawData()
                        : existingImage.getImageRawData()
        );

        existingImage.setCategoryId(
                image.getCategoryId() != null
                        ? image.getCategoryId()
                        : existingImage.getCategoryId()
        );

        existingImage.setEnabled(
                image.getEnabled() != null
                        ? image.getEnabled()
                        : existingImage.getEnabled()
        );

        existingImage.setIsDaily(
                image.getIsDaily() != null
                        ? image.getIsDaily()
                        : existingImage.getIsDaily()
        );

        existingImage.setSort(
                image.getSort() != null ? image.getSort(): existingImage.getSort()
        );

        imageRepository.save(existingImage);

        return existingImage;
    }

    public void delete(Long imageId) {
        imageRepository.deleteByImageId(imageId);
    }
}
