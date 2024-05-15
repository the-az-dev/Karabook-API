package org.theaz.karabookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.theaz.karabookapi.dto.TextI18NUpdateDTO;
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
    public ResponseEntity<?> save(@RequestBody TextI18N textI18N){
        this.textI18NService.save(textI18N);
        return new ResponseEntity<>("Text added!", HttpStatus.OK);
    }

    @PutMapping(value = "/update", consumes = {"*/*"})
    public ResponseEntity<?> update(@RequestBody TextI18NUpdateDTO textI18N){
        TextI18N exitingTextI18N = this.textI18NService.findByTextKey(textI18N.getTextKey());
        this.textI18NService.update(exitingTextI18N, textI18N);
        return new ResponseEntity<>(this.textI18NService.update(exitingTextI18N, textI18N), HttpStatus.OK);
    }

    @DeleteMapping("/delete/")
    public ResponseEntity<?> delete(@RequestParam(value = "id", required = true) String textKey){
        this.textI18NService.delete(textKey);
        return new ResponseEntity<>("Text deleted!", HttpStatus.OK);
    }
}
