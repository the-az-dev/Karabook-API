package org.theaz.karabookapi.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.theaz.karabookapi.dto.update.AchivementsProgressUpdateDTO;
import org.theaz.karabookapi.entity.AchivementProgress;
import org.theaz.karabookapi.repository.AchivementProgressRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AchivementProgressService {
    @Autowired
    private AchivementProgressRepository achivementProgressRepository;

    public List<AchivementProgress> getAll() {
        return this.achivementProgressRepository.findAll();
    }

    public List<AchivementProgress> getAllByUserId(Long userId) {
        return this.achivementProgressRepository.findAllByUserId(userId);
    }

    public AchivementProgress getOneByUserIdAndAchivementId(Long achivementsId, Long userId) {
        return this.achivementProgressRepository.findByAchivementIdAndUserId(achivementsId, userId);
    }

    public void save(AchivementProgress achivementsProgress) {
        this.achivementProgressRepository.save(achivementsProgress);
    }

    public void update(AchivementsProgressUpdateDTO newAchivementsProgress,
            AchivementProgress exitingAchivementsProgress) {

        Date currentDate = new Date();

        exitingAchivementsProgress.setCompletedPoints(
                newAchivementsProgress.getCompletedPoints() != null ? newAchivementsProgress.getCompletedPoints()
                        : exitingAchivementsProgress.getCompletedPoints());

        exitingAchivementsProgress.setIsCompleted(
                newAchivementsProgress.getIsCompleted() != null ? newAchivementsProgress.getIsCompleted()
                        : exitingAchivementsProgress.getIsCompleted());

        exitingAchivementsProgress.setModifiedDate(currentDate);

        this.achivementProgressRepository.save(exitingAchivementsProgress);
    }

    public void deleteAllByUserId(Long userId) {
        this.achivementProgressRepository.deleteAllByUserId(userId);
    }

    public void deleteByAchivementId(Long achivementId) {
        this.achivementProgressRepository.deleteByAchivementId(achivementId);
    }
}
