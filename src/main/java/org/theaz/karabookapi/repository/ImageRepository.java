package org.theaz.karabookapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.theaz.karabookapi.entity.Image;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findImageByImageId(Long imageId);
    List<Image> findByEnabledAndCategoryIdAndIsDaily(Boolean enabled, Long categoryId, Boolean isDaily);
    List<Image> findByEnabledAndIsDaily(Boolean enabled, Boolean isDaily);
    List<Image> findByIsDaily(Boolean isDaily);
    List<Image> findByCategoryIdAndIsDaily(Long categoryId, Boolean isDaily);
    void deleteByImageId(Long imageId);
}
