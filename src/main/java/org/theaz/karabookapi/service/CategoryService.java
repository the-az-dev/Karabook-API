package org.theaz.karabookapi.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.theaz.karabookapi.dto.CategoryUpdateDTO;
import org.theaz.karabookapi.entity.Category;
import org.theaz.karabookapi.repository.CategoryRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return this.categoryRepository.findAllByOrderBySortAsc();
    }

    public Category getCategoryById(Long categoryId) {
        return this.categoryRepository.findByCategoryIdOrderBySortAsc(categoryId);
    }

    public List<Category> getAllCategoriesByCategoryTypeId(Long categoty_type_id) {
        return this.categoryRepository.findAllByCategoryTypeIdOrderBySortAsc(categoty_type_id);
    }

    public void save(Category category) {
        this.categoryRepository.save(category);
    }

    public Category update(Category exitingCategory, CategoryUpdateDTO newCategory) {
        Date nowDate = new Date();
        exitingCategory.setCategoryPreview(
                newCategory.getCategoryPreview() != null
                        ? newCategory.getCategoryPreview()
                        : exitingCategory.getCategoryPreview() != null
                        ? exitingCategory.getCategoryPreview()
                        : null);
        exitingCategory.setCategoryTypeId(
                newCategory.getCategoryTypeId() != null
                        ? newCategory.getCategoryTypeId()
                        : exitingCategory.getCategoryTypeId());
        exitingCategory.setCategoryNameKey(
                newCategory.getCategoryNameKey() != null
                        ? newCategory.getCategoryNameKey()
                        : exitingCategory.getCategoryNameKey());
        exitingCategory.setCategoryDescriptionKey(
                newCategory.getCategoryDescriptionKey() != null
                        ? newCategory.getCategoryDescriptionKey()
                        : exitingCategory.getCategoryDescriptionKey());
        exitingCategory.setEnabled(
                newCategory.getEnabled() != null
                        ? newCategory.getEnabled()
                        : exitingCategory.getEnabled());
        exitingCategory.setModifiedDate(nowDate);
        this.categoryRepository.save(exitingCategory);
        return exitingCategory;
    }

    public void delete(Long categoryId){
        this.categoryRepository.deleteByCategoryId(categoryId);
    }
}
