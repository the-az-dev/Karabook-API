package org.theaz.karabookapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.theaz.karabookapi.entity.ImageProgress;

public interface ImageProgressRepository extends JpaRepository<ImageProgress, Long> {
}
