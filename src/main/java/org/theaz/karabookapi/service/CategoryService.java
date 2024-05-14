package org.theaz.karabookapi.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.theaz.karabookapi.entity.Category;
import org.theaz.karabookapi.repository.CategoryRepository;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    public Category getCategoryById(Long categoryId) {
        return this.categoryRepository.findByCategoryId(categoryId);
    }

    public List<Category> getAllCategoriesByCategoryTypeId(Long categoty_type_id) {
        return this.categoryRepository.findAllByCategoryTypeId(categoty_type_id);
    }

    public void save(Category category) {
        this.categoryRepository.save(category);
    }
}
