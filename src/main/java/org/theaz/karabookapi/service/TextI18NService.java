package org.theaz.karabookapi.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.theaz.karabookapi.entity.TextI18N;
import org.theaz.karabookapi.repository.TextI18NRepository;

import java.util.List;

@Service
@Transactional
public class TextI18NService {
    @Autowired
    private TextI18NRepository textI18NRepository;

    public List<TextI18N> findAll() {
        return this.textI18NRepository.findAll();
    }

    public TextI18N findByLocaleAndTextKey(String locale, String textKey) {
        return this.textI18NRepository.findByLocaleAndTextKey(locale, textKey);
    }

    public void save(TextI18N textI18N){
        this.textI18NRepository.save(textI18N);
    }
}
