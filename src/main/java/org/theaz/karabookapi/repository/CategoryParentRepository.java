package org.theaz.karabookapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.theaz.karabookapi.entity.CategoryParent;

public interface CategoryParentRepository extends JpaRepository<CategoryParent, Long> {
}
