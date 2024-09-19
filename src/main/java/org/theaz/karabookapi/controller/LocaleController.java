package org.theaz.karabookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.theaz.karabookapi.entity.Locale;
import org.theaz.karabookapi.service.LocaleService;

import java.util.List;

@RestController
@RequestMapping("/api/text/locale")
public class LocaleController {
    @Autowired
    private LocaleService localeService;

    @Value("${dev.static.token}")
    private String staticDevToken;

    @GetMapping("/get/all")
    public List<Locale> getAll(@RequestHeader(value = "dev_access_token", required = false) String devAccessToken) {
        return this.localeService.findAll();
    }

    @PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public void add(@RequestBody Locale locale, @RequestHeader(value = "dev_access_token", required = false) String devAccessToken) {
        this.localeService.save(locale);
    }
}
