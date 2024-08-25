package org.theaz.karabookapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.theaz.karabookapi.entity.AchivementProgress;
import java.util.*;

public interface AchivementProgressRepository extends JpaRepository<AchivementProgress, Long> {
    public List<AchivementProgress> findAllByUserId(Long userId);

    public AchivementProgress findByAchivementIdAndUserId(Long achivementId, Long userId);

    public void deleteByAchivementsProgressId(Long achivementsProgressId);

    void deleteAllByUserId(Long userId);

    void deleteByAchivementId(Long achivementId);
}
