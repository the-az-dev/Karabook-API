package org.theaz.karabookapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.theaz.karabookapi.entity.CategoryType;

public interface CategoryTypeRepository extends JpaRepository<CategoryType, Long> {
}
