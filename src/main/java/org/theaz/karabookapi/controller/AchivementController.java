package org.theaz.karabookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.theaz.karabookapi.entity.Achivement;
import org.theaz.karabookapi.service.AchivementService;
import org.theaz.karabookapi.dto.change.*;
import org.theaz.karabookapi.dto.update.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.time.Instant;

@RestController
@RequestMapping("/api/achivement")
public class AchivementController {

    @Autowired
    private AchivementService achivementService;

    @Value("${dev.static.token}")
    private String staticDevToken;

    @GetMapping({ "/get/all" })
    public ResponseEntity<?> getAll(@RequestHeader(value = "dev_access_token", required = false) String devAccessToken) {
        try {
            if(staticDevToken.equals(devAccessToken)) {
                return new ResponseEntity<>(this.achivementService.getAll(), HttpStatus.OK);
            } else throw new Exception("Need to set dev_access_token");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping({ "/get/all/ByModifiedDate" })
    public ResponseEntity<?> getAllByModifiedDate() {
        try {

            List<Achivement> achivementsAll = this.achivementService.getAll();
            List<AchivementChangeDTO> achivementsChangeList = new ArrayList<AchivementChangeDTO>();

            for (Achivement achivement : achivementsAll) {
                Instant instant = achivement.getModifiedDate().toInstant();
                long milliseconds = instant.toEpochMilli();

                AchivementChangeDTO achivementChangeDTO = new AchivementChangeDTO();
                achivementChangeDTO.achivementId = achivement.getAchivementId();
                achivementChangeDTO.modifiedDate = milliseconds;

                achivementsChangeList.add(achivementChangeDTO);
            }

            return new ResponseEntity<>(achivementsChangeList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping({ "/get/ById" })
    public ResponseEntity<?> getById(@RequestParam(value = "value", required = true) Long id) {
        try {
            return new ResponseEntity<>(this.achivementService.getOneById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping({ "/get/byIds" })
    public ResponseEntity<?> getAllByIds(@RequestParam(value = "value", required = true) String ids) {
        try {
            List<Achivement> findedAchivements = new ArrayList<>();
            if (ids.split(",") == null) {
                Achivement achivement = this.achivementService.getOneById(Long.parseLong(ids));
                return new ResponseEntity<>(achivement, HttpStatus.OK);
            } else {
                String[] ids_arr = ids.split(",");
                for (String id : ids_arr) {
                    findedAchivements.add(this.achivementService.getOneById(Long.parseLong(id)));
                }
                return new ResponseEntity<>(findedAchivements, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = { "/add" }, consumes = { "application/json", "multipart/form-data" })
    public void save(@RequestBody Achivement achivement, @RequestHeader(value = "dev_access_token") String devAccessToken) {
        try {
            if(staticDevToken.equals(devAccessToken)) {
                Date currentDate = new Date();
                achivement.setModifiedDate(currentDate);
                this.achivementService.save(achivement);
            }
            else throw new Exception("Need to set dev_access_token");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @PutMapping(value = { "/update" }, consumes = { "application/json", "multipart/form-data" })
    public void update(@RequestBody AchivementUpdateDTO achivement, @RequestHeader(value = "dev_access_token", required = false) String devAccessToken) {
        try {
            if(staticDevToken.equals(devAccessToken)) {
                Achivement exAchivement = this.achivementService.getOneById(achivement.getAchivementId());
                this.achivementService.update(achivement, exAchivement);
            } else throw new Exception("Need to set dev_access_token");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
