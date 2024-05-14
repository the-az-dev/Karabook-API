package org.theaz.karabookapi.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.theaz.karabookapi.repository.TextI18NRepository;

@Service
@Transactional
public class TextI18NService {
    @Autowired
    private TextI18NRepository textI18NRepository;
}
