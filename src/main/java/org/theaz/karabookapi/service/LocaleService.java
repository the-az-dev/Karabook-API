package org.theaz.karabookapi.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.theaz.karabookapi.entity.Locale;
import org.theaz.karabookapi.repository.LocaleRepository;

import java.util.List;

@Service
@Transactional
public class LocaleService {
    @Autowired
    private LocaleRepository localeRepository;

    public void save(Locale locale) {
        this.localeRepository.save(locale);
    }

    public List<Locale> findAll() {
        return this.localeRepository.findAll();
    }
}
