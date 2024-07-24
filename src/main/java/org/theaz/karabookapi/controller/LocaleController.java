package org.theaz.karabookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/get/all")
    public List<Locale> getAll() {
        return this.localeService.findAll();
    }

    @PostMapping(value = "/add", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public String add(@RequestBody Locale locale){
        this.localeService.save(locale);
        return "Locale added successfully!";
    }
}
