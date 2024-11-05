package org.theaz.karabookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.theaz.karabookapi.dto.change.AchivementProgressChangeDTO;
import org.theaz.karabookapi.dto.response.AchivementProgressResponseDTO;
import org.theaz.karabookapi.dto.update.AchivementsProgressUpdateDTO;
import org.theaz.karabookapi.entity.*;
import org.theaz.karabookapi.service.AchivementProgressService;
import org.theaz.karabookapi.service.AchivementService;

import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("/api/achivements-progress")
public class AchivementProgressController {
    @Autowired
    private AchivementProgressService achivementProgressService;
    @Autowired
    private AchivementService achivementService;

    @Value("${dev.static.token}")
    private String staticDevToken;

    @GetMapping({ "/get/all" })
    public ResponseEntity<?> getAll(@RequestHeader(value = "dev_access_token", required = false) String devAccessToken) {
        try {
            return new ResponseEntity<>(this.achivementProgressService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping({ "/get/byUserId" })
    public ResponseEntity<?> findByUserId(@RequestParam(value = "value", required = true) Long userId) {
        try {
            return new ResponseEntity<>(this.achivementProgressService.getAllByUserId(userId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping({ "/get/byModifiedDateAndUserId" })
    public ResponseEntity<?> findByModifiedDateAndUserId(@RequestParam(value = "value", required = true) Long userId) {
        try {
            List<AchivementProgress> achivementProgressesAll = this.achivementProgressService.getAllByUserId(userId);
            List<Map<Long, AchivementProgressResponseDTO>> achivementProgressChanges = new ArrayList<Map<Long, AchivementProgressResponseDTO>>();
            for (AchivementProgress achivementProgress : achivementProgressesAll) {

                Instant instant = achivementProgress.getModifiedDate().toInstant();
                long milliseconds = instant.toEpochMilli();

                AchivementProgressResponseDTO achivementProgressChangesDTO = new AchivementProgressResponseDTO();
                achivementProgressChangesDTO.userId = achivementProgress.getUserId();
                achivementProgressChangesDTO.achivementId = achivementProgress.getAchivementId();
                achivementProgressChangesDTO.completedPoints = achivementProgress.getCompletedPoints();
                achivementProgressChangesDTO.modifiedDate = milliseconds;
                achivementProgressChangesDTO.isCompleted = achivementProgress.getIsCompleted();
                achivementProgressChangesDTO.isRecived = achivementProgress.getIsRecived();

                Map<Long, AchivementProgressResponseDTO> achivementMap = new HashMap<Long, AchivementProgressResponseDTO>();
                achivementMap.put(achivementProgress.getAchivementsProgressId(), achivementProgressChangesDTO);
                achivementProgressChanges.add(achivementMap);
            }
            return new ResponseEntity<>(achivementProgressChanges, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping({ "/get/all/byModifiedDate" })
    public ResponseEntity<?> findAllByModifiedDate() {
        try {
            List<AchivementProgress> achivementProgressesAll = this.achivementProgressService.getAll();
            List<AchivementProgressChangeDTO> achivementProgressChanges = new ArrayList<AchivementProgressChangeDTO>();
            for (AchivementProgress achivementProgress : achivementProgressesAll) {

                Instant instant = achivementProgress.getModifiedDate().toInstant();
                long milliseconds = instant.toEpochMilli();

                AchivementProgressChangeDTO achivementProgressChangesDTO = new AchivementProgressChangeDTO();
                achivementProgressChangesDTO.achivementsProgressId = achivementProgress.getAchivementsProgressId();
                achivementProgressChangesDTO.modifiedDate = milliseconds;

                achivementProgressChanges.add(achivementProgressChangesDTO);
            }
            return new ResponseEntity<>(achivementProgressChanges, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping({ "/get/byUserIdAndAchivementId" })
    public ResponseEntity<?> findByUserIdAndAchivementId(@RequestParam(value = "userId", required = true) Long userId,
            @RequestParam(value = "achivementId", required = true) Long achivementId) {
        try {
            return new ResponseEntity<>(
                    this.achivementProgressService.getOneByUserIdAndAchivementId(achivementId, userId), HttpStatus.OK);
        } catch (Exception var2) {
            return new ResponseEntity<>(var2, HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = { "/add" }, consumes = { "application/json", "multipart/form-data" })
    public void save(@RequestBody AchivementProgress achivementProgress) {
        try {
            Date currentDate = new Date();
            achivementProgress.setModifiedDate(currentDate);
            this.achivementProgressService.save(achivementProgress);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @PutMapping(value = { "/update" }, consumes = { "application/json", "multipart/form-data" })
    public void update(@RequestBody AchivementsProgressUpdateDTO achivementsProgress) {
        try {
            AchivementProgress exitingAchivementsProgress = this.achivementProgressService
                    .getOneByUserIdAndAchivementId(achivementsProgress.getAchivementId(),
                            achivementsProgress.getUserId());
            Achivement currentAchivement = this.achivementService.getOneById(achivementsProgress.getAchivementId());
            if(achivementsProgress.getCompletedPoints() == currentAchivement.getAchivementsMaxPoints()) achivementsProgress.setIsCompleted(true);
            this.achivementProgressService.update(achivementsProgress, exitingAchivementsProgress);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @DeleteMapping({ "/delete/all/ByUserId" })
    public void deleteAllByUserId(@RequestParam(value = "value", required = true) Long userId) {
        try {
            this.achivementProgressService.deleteAllByUserId(userId);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @DeleteMapping({ "/delete/ByAchivementId" })
    public void deleteByAchivementId(@RequestParam(value = "value", required = true) Long achivementId) {
        try {
            this.achivementProgressService.deleteByAchivementId(achivementId);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
