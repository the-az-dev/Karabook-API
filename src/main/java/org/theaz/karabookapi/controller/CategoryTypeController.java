package org.theaz.karabookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.theaz.karabookapi.service.CategoryTypeService;

@RestController
@RequestMapping("/api/category/type")
public class CategoryTypeController {
    @Autowired
    private CategoryTypeService categoryTypeService;
}
