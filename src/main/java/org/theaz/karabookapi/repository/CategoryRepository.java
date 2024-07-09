package org.theaz.karabookapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.theaz.karabookapi.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByOrderBySortAsc();

    List<Category> findAllByCategoryTypeIdOrderBySortAsc(Long categoryTypeId);
    Category findByCategoryIdOrderBySortAsc(Long categoryId);
    void deleteByCategoryId(Long categoryId);
}
