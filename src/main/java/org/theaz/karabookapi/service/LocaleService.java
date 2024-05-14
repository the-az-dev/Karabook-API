package org.theaz.karabookapi.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.theaz.karabookapi.repository.LocaleRepository;

@Service
@Transactional
public class LocaleService {
    @Autowired
    private LocaleRepository localeRepository;
}
