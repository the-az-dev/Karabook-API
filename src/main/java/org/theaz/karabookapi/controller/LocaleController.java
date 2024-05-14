package org.theaz.karabookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.theaz.karabookapi.service.LocaleService;

@RestController
@RequestMapping("/api/text/locale")
public class LocaleController {
    @Autowired
    private LocaleService localeService;
}
