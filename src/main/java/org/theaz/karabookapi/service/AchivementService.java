package org.theaz.karabookapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.theaz.karabookapi.repository.AchivementRepository;

import jakarta.transaction.Transactional;

import org.theaz.karabookapi.dto.update.AchivementUpdateDTO;
import org.theaz.karabookapi.entity.*;

import java.util.*;

@Service
@Transactional
public class AchivementService {
    @Autowired
    private AchivementRepository achivementRepository;

    public List<Achivement> getAll() {
        return this.achivementRepository.findAll();
    }

    public Achivement getOneById(Long Id) {
        return this.achivementRepository.findByAchivementId(Id);
    }

    public void save(Achivement achivement) {
        this.achivementRepository.save(achivement);
    }

    public void update(AchivementUpdateDTO newAchivement, Achivement exAchivement) {
        Date currentDate = new Date();

        exAchivement.setAchivementsNameKey(
                newAchivement.getAchivementsNameKey() != null ? newAchivement.getAchivementsNameKey()
                        : exAchivement.getAchivementsNameKey());

        exAchivement.setEnabled(
                newAchivement.getEnabled() != null ? newAchivement.getEnabled() : exAchivement.getEnabled());

        exAchivement.setAchivementsMaxPoints(
                newAchivement.getAchivementsMaxPoints() != null ? newAchivement.getAchivementsMaxPoints()
                        : exAchivement.getAchivementsMaxPoints());

        exAchivement.setAchivementsDescriptionKey(
                newAchivement.getAchivementsDescriptionKey() != null ? newAchivement.getAchivementsDescriptionKey()
                        : exAchivement.getAchivementsNameKey());

        exAchivement.setEnabled(
                newAchivement.getEnabled() != null ? newAchivement.getEnabled()
                        : exAchivement.getEnabled());

        exAchivement.setTipsNumber(
                newAchivement.getTipsNumber() != null ? newAchivement.getTipsNumber()
                        : exAchivement.getTipsNumber());

        exAchivement.setImage(
                newAchivement.getImage() != null ? newAchivement.getImage() : exAchivement.getImage()
        );

        exAchivement.setModifiedDate(currentDate);

        this.achivementRepository.save(exAchivement);
    }
}