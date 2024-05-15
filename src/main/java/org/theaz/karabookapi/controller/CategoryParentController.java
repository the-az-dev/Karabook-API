package org.theaz.karabookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.theaz.karabookapi.entity.CategoryParent;
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

    @PostMapping(value = "/add", consumes = {"*/*"})
    public ResponseEntity<?> add(@ModelAttribute CategoryParent categoryParent) {
        this.categoryParentService.save(categoryParent);
        return new ResponseEntity<>("Category parent Added!", HttpStatus.OK);
    }
}
