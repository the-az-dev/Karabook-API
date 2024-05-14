package org.theaz.karabookapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.theaz.karabookapi.entity.Locale;

public interface LocaleRepository extends JpaRepository<Locale, String> {
}
