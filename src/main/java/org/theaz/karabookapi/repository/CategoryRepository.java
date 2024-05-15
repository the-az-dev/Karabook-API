package org.theaz.karabookapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.theaz.karabookapi.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByCategoryTypeId(Long categoryTypeId);
    Category findByCategoryId(Long categoryId);
    void deleteByCategoryId(Long categoryId);
}
