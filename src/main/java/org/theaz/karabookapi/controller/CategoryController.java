package org.theaz.karabookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.theaz.karabookapi.dto.CategoryChangeDTO;
import org.theaz.karabookapi.entity.Category;
import org.theaz.karabookapi.entity.Image;
import org.theaz.karabookapi.service.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryTypeService categoryTypeService;

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(this.categoryService.getAllCategories(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get/all/ByIds/")
    public ResponseEntity<?> getAllById(@RequestParam(value = "value", required = true) String ids) {
        try {
            List<Category> categories = new ArrayList<Category>();
            String[] categoryIds = ids.split(",");
            for (String categoryId : categoryIds) {
                Category category = this.categoryService.getCategoryById(Long.parseLong(categoryId));
                categories.add(category);
            }
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get/all/byTypeID/")
    public ResponseEntity<?> getByCategoryTypeID(@RequestParam(value = "value", required = true) Long categoty_type_id) {
        try {
            var categoryTypes = this.categoryTypeService.findAll();
            if(categoty_type_id > categoryTypes.getLast().getCategoryTypeId() || categoty_type_id < categoryTypes.getFirst().getCategoryTypeId()) {
                throw new Exception("Category type id is invalid. Cause: "
                        + categoty_type_id + " is not in range "
                        + categoryTypes.getLast().getCategoryTypeId()
                        + " - " + categoryTypes.getFirst().getCategoryTypeId() + " !");
            }
            return new ResponseEntity<>(this.categoryService.getAllCategoriesByCategoryTypeId(categoty_type_id), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get/all/ByModifiedDate")
    public ResponseEntity<?> getAllImagesByModifiedDate() {
        try {
            List<Category> categories = this.categoryService.getAllCategories();
            List<CategoryChangeDTO> categoryChange = new ArrayList<CategoryChangeDTO>();
            for (Category category : categories) {
                Instant instant = category.getModifiedDate().toInstant();
                long milliseconds = instant.toEpochMilli();
                CategoryChangeDTO changeDTO = new CategoryChangeDTO();
                changeDTO.categoryId = category.getCategoryId();
                changeDTO.modifiedDate = String.valueOf(milliseconds);
                categoryChange.add(changeDTO);
            }
            return new ResponseEntity<>(categoryChange, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "/add", consumes = {"*/*"})
    public ResponseEntity<?> addCategory(@RequestHeader String access_token, @ModelAttribute Category category) {
        try {
            this.categoryService.save(category);
            return new ResponseEntity<>("Category added to DB!", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping(value = "/update/", consumes = {"*/*"})
    public ResponseEntity<?> updateCategory(@ModelAttribute Category category, @RequestParam(value = "id", required = true) Long categoty_id) {
        try {
            Category exitingCategory = this.categoryService.getCategoryById(categoty_id);
            category.setCategoryId(exitingCategory.getCategoryId());
            this.categoryService.save(category);
            return new ResponseEntity<>("Category added to DB!", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
