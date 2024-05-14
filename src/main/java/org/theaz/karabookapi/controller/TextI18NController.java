package org.theaz.karabookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.theaz.karabookapi.service.TextI18NService;

@RestController
@RequestMapping("/api/text/i18n")
public class TextI18NController {
    @Autowired
    private TextI18NService textI18NService;
}
