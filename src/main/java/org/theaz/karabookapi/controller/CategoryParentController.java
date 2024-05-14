package org.theaz.karabookapi.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.theaz.karabookapi.service.CategoryParentService;

@RestController
@RequestMapping("/api/category/parent")
public class CategoryParentController {
    @Autowired
    private CategoryParentService categoryParentService;

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(this.categoryParentService.findAll(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
