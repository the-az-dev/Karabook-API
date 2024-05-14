package org.theaz.karabookapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.theaz.karabookapi.entity.TextI18N;

public interface TextI18NRepository extends JpaRepository<TextI18N,Long> {
}
