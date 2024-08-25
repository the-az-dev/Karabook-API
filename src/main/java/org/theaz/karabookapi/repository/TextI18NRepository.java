package org.theaz.karabookapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.theaz.karabookapi.entity.TextI18N;

import java.util.List;

@Repository
public interface TextI18NRepository extends JpaRepository<TextI18N, Long> {
    TextI18N findByLocaleAndTextKey(String locale, String textKey);

    TextI18N findByTextKey(String textKey);

    void deleteByTextKey(String textKey);

    List<TextI18N> findAllByLocale(String locale);
}
