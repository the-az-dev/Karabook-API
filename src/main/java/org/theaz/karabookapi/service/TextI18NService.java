package org.theaz.karabookapi.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.theaz.karabookapi.dto.TextI18NUpdateDTO;
import org.theaz.karabookapi.entity.TextI18N;
import org.theaz.karabookapi.repository.TextI18NRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TextI18NService {
    @Autowired
    private TextI18NRepository textI18NRepository;

    public List<TextI18N> findAll() {
        return this.textI18NRepository.findAll();
    }

    public TextI18N findByTextKey(String textKey) {
        return this.textI18NRepository.findByTextKey(textKey);
    }

    public TextI18N findByLocaleAndTextKey(String locale, String textKey) {
        return this.textI18NRepository.findByLocaleAndTextKey(locale, textKey);
    }

    public void save(TextI18N textI18N){
        this.textI18NRepository.save(textI18N);
    }

    public TextI18N update(TextI18N exitingTextI18N, TextI18NUpdateDTO textI18N){
        Date newDate = new Date();

        exitingTextI18N.setTextKey(
                textI18N.getTextKey() != null
                        ? textI18N.getTextKey()
                        : exitingTextI18N.getTextKey()
        );

        exitingTextI18N.setTextValue(
                textI18N.getTextValue() != null
                        ? textI18N.getTextValue()
                        : exitingTextI18N.getTextValue()
        );

        exitingTextI18N.setLocale(
                textI18N.getLocale() != null
                        ? textI18N.getLocale()
                        : exitingTextI18N.getLocale()
        );

        exitingTextI18N.setModifiedDate(newDate);

        this.textI18NRepository.save(exitingTextI18N);

        return exitingTextI18N;
    }
}
