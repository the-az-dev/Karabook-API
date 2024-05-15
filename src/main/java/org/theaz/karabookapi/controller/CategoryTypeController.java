package org.theaz.karabookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.theaz.karabookapi.entity.CategoryType;
import org.theaz.karabookapi.service.CategoryTypeService;

@RestController
@RequestMapping("/api/category/type")
public class CategoryTypeController {
    @Autowired
    private CategoryTypeService categoryTypeService;

    @PostMapping(value = "/add", consumes = {"*/*"})
    public ResponseEntity<?> addCategoryType(@RequestBody CategoryType categoryType) {
        this.categoryTypeService.save(categoryType);
        return new ResponseEntity<>("Category type Added!", HttpStatus.OK);
    }
}
