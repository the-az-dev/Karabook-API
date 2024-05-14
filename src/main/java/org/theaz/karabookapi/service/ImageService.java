package org.theaz.karabookapi.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
