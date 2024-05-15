package org.theaz.karabookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.theaz.karabookapi.entity.TextI18N;
import org.theaz.karabookapi.service.TextI18NService;

import java.util.ArrayList;
import java.util.List;

class LocaleText {
    public String textKey;
    public String textValue;
    public String locale;
}

@RestController
@RequestMapping("/api/text/i18n")
public class TextI18NController {
    @Autowired
    private TextI18NService textI18NService;

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(this.textI18NService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/get/all/ByTextKey/{locale}/")
    public ResponseEntity<?> getTextByLocaleAndTextKey(@PathVariable(name = "locale") String locale, @RequestParam(value = "value", required = true) String textKey) {
        return new ResponseEntity<>(this.textI18NService.findByLocaleAndTextKey(locale, textKey), HttpStatus.OK);
    }

    @GetMapping("/get/all/ByTextKeys/{locale}/")
    public ResponseEntity<?> getTextByLocaleAndTextKeys(@PathVariable(name = "locale") String locale, @RequestParam(value = "value", required = true) String textKeys) {
        try {
            String[] textKeysArray = textKeys.split(",");
            List<LocaleText> ChangedTexts = new ArrayList<>();
            for (String textKey : textKeysArray) {
                LocaleText localeText = new LocaleText();
                TextI18N textI18N = this.textI18NService.findByLocaleAndTextKey(locale, textKey);
                if (textI18N != null) {
                    localeText.textKey = textI18N.getTextKey();
                    localeText.textValue = textI18N.getTextValue();
                    localeText.locale = textI18N.getLocale();
                }
                ChangedTexts.add(localeText);
            }
            return new ResponseEntity<>(ChangedTexts, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/add", consumes = {"*/*"})
    public ResponseEntity<?> save(@ModelAttribute TextI18N textI18N){
        this.textI18NService.save(textI18N);
        return new ResponseEntity<>("Text added!", HttpStatus.OK);
    }
}
