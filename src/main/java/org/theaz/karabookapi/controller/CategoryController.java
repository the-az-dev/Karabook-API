package org.theaz.karabookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.theaz.karabookapi.dto.change.CategoryChangeDTO;
import org.theaz.karabookapi.dto.update.CategoryUpdateDTO;
import org.theaz.karabookapi.entity.Category;
import org.theaz.karabookapi.service.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryTypeService categoryTypeService;

    @Value("${dev.static.token}")
    private String staticDevToken;

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll(@RequestHeader(value = "dev_access_token", required = false) String devAccessToken) {
        try {
            if(staticDevToken.equals(devAccessToken)) {
                return new ResponseEntity<>(this.categoryService.getAllCategories(), HttpStatus.OK);
            } else throw new Exception("Need to set dev_access_token");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get/all/ByIds/")
    public ResponseEntity<?> getAllById(@RequestParam(value = "value", required = true) String ids) {
        try {
            List<Category> categories = new ArrayList<Category>();
            if (ids.split(",") == null) {
                Category category = this.categoryService.getCategoryById(Long.parseLong(ids));
                categories.add(category);
            } else {
                String[] categoryIds = ids.split(",");
                for (String id : categoryIds) {
                    Category category = this.categoryService.getCategoryById(Long.parseLong(id));
                    categories.add(category);
                }
            }
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get/all/byTypeID/")
    public ResponseEntity<?> getByCategoryTypeID(
            @RequestParam(value = "value", required = true) Long categoty_type_id) {
        try {
            var categoryTypes = this.categoryTypeService.findAll();
            if (categoty_type_id <= categoryTypes.getLast().getCategoryTypeId()
                    && categoty_type_id >= categoryTypes.getFirst().getCategoryTypeId()) {
                return new ResponseEntity<>(this.categoryService.getAllCategoriesByCategoryTypeId(categoty_type_id),
                        HttpStatus.OK);
            } else {
                throw new Exception("Category type id is invalid. Cause: "
                        + categoty_type_id + " is not in range "
                        + categoryTypes.getLast().getCategoryTypeId()
                        + " - " + categoryTypes.getFirst().getCategoryTypeId() + " !");
            }
        } catch (Exception e) {
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
                changeDTO.modifiedDate = milliseconds;
                categoryChange.add(changeDTO);
            }
            return new ResponseEntity<>(categoryChange, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public void addCategory(@RequestBody Category category, @RequestHeader(value = "dev_access_token", required = false) String devAccessToken) {
        try {
            if(staticDevToken.equals(devAccessToken)) {
                Date currentDate = new Date();
                category.setModifiedDate(currentDate);
                this.categoryService.save(category);
            } else throw new Exception("Need to set dev_access_token");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @PutMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public void updateCategory(@RequestBody CategoryUpdateDTO category, @RequestHeader(value = "dev_access_token", required = false) String devAccessToken) {
        try {
            if(staticDevToken.equals(devAccessToken)) {
                Category exitingCategory = this.categoryService.getCategoryById(category.getCategoryId());
                this.categoryService.update(exitingCategory, category);
            } else throw new Exception("Need to set dev_access_token");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @DeleteMapping("/delete/")
    public void deleteCategory(@RequestParam(value = "id", required = true) Long categotyId, @RequestHeader(value = "dev_access_token", required = false) String devAccessToken) {
        try {
            if(staticDevToken.equals(devAccessToken)) {
                this.categoryService.delete(categotyId);
            } else throw new Exception("Need to set dev_access_token");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
