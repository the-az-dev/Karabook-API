package org.theaz.karabookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.theaz.karabookapi.entity.CategoryParent;
import org.theaz.karabookapi.service.CategoryParentService;

@RestController
@RequestMapping("/api/category/parent")
public class CategoryParentController {
    @Autowired
    private CategoryParentService categoryParentService;

    @Value("${dev.static.token}")
    private String staticDevToken;

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll(@RequestHeader(value = "dev_access_token", required = false) String devAccessToken) {
        try {
            if(staticDevToken.equals(devAccessToken)) {
                return new ResponseEntity<>(this.categoryParentService.findAll(), HttpStatus.OK);
            } else throw new Exception("Need to set dev_access_token");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public void add(@RequestBody CategoryParent categoryParent, @RequestHeader(value = "dev_access_token", required = false) String devAccessToken) {
        if(staticDevToken.equals(devAccessToken)) {
            this.categoryParentService.save(categoryParent);
        } else return;
    }
}
