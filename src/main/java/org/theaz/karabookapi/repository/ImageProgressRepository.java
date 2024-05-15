package org.theaz.karabookapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.theaz.karabookapi.entity.ImageProgress;

import java.util.List;

public interface ImageProgressRepository extends JpaRepository<ImageProgress, Long> {
    ImageProgress findByImageProgressId(Long imageProgressId);
    List<ImageProgress> findAllByUserId(Long userId);
}
