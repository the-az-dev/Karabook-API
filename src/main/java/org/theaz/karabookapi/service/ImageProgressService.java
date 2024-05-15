package org.theaz.karabookapi.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.theaz.karabookapi.entity.ImageProgress;
import org.theaz.karabookapi.repository.ImageProgressRepository;

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
}
