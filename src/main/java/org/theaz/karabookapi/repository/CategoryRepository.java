package org.theaz.karabookapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.theaz.karabookapi.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByCategoryTypeId(Long categoty_type_id);
    Category findByCategoryId(Long categoty_sid);
}
