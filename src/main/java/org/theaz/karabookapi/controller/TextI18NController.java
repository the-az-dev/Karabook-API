package org.theaz.karabookapi.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.theaz.karabookapi.entity.TextI18N;
import org.theaz.karabookapi.service.TextI18NService;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor
class LocaleText {
    public LocaleText(TextI18N text) {
        this.locale = text.getLocale();
        this.textKey = text.getTextKey();
        this.textValue = text.getTextValue();
    }

    public String textKey;
    public String textValue;
    public String locale;
}

@RestController
@RequestMapping("/api/text/i18n")
public class TextI18NController {
    @Autowired
    private TextI18NService textI18NService;

    /**
     * Повертає всі тексти для всіх локалей.
     * Не рекомендується.
     * Краще використовувати /get/all/ByLocale/{locale}
     * @return несортований список
     */
    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(this.textI18NService.findAll(), HttpStatus.OK);
    }

    /**
     * Повертає всі тексти для однієї локалі
     * @param locale
     * @return несортований список
     */
    @GetMapping("/get/all/ByLocale/{locale}")
    public ResponseEntity<?> getAllTextByLocale(@PathVariable(name = "locale") String locale) {
        return new ResponseEntity<>(this.textI18NService.getAllTextByLocale(locale), HttpStatus.OK);
    }

    /**
     * Повертає тексти для локалі та за ключом
     * @param locale передається в адресі запиту
     * @param textKey передається як параметр запиту
     * @return несортований список
     */
    @GetMapping("/get/all/ByTextKey/{locale}/")
    public ResponseEntity<?> getTextByLocaleAndTextKey(@PathVariable(name = "locale") String locale, @RequestParam(value = "value", required = true) String textKey) {
        return new ResponseEntity<>(this.textI18NService.findByLocaleAndTextKey(locale, textKey), HttpStatus.OK);
    }

    /**
     * Повертає тексти для локалі та за списком ключів
     * @param locale передається в адресі запиту
     * @param textKeys передається як параметр запиту у вигляді строки, з ключами, розділеними комою
     * @return несортований список
     */
    @GetMapping("/get/all/ByTextKeys/{locale}/")
    public ResponseEntity<?> getTextByLocaleAndTextKeys(@PathVariable(name = "locale") String locale, @RequestParam(value = "value", required = true) String textKeys) {
        try {
            String[] textKeysArray = textKeys.split(",");
            List<LocaleText> ChangedTexts = new ArrayList<>();
            for (String textKey : textKeysArray) {
                TextI18N textI18N = this.textI18NService.findByLocaleAndTextKey(locale, textKey);
                if (textI18N != null) {
                    ChangedTexts.add(new LocaleText(textI18N));
                }else{
                    ChangedTexts.add(new LocaleText());
                }
            }
            return new ResponseEntity<>(ChangedTexts, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Створює новий запис тексту
     * @param textI18N
     * @return
     */

//    @PostMapping(value = "/add", consumes = {"*/*"})
//    public ResponseEntity<?> save(@RequestBody TextI18N textI18N){
//        this.textI18NService.save(textI18N);
//        return new ResponseEntity<>("Text added!", HttpStatus.OK);
//    }

//    @PutMapping(value = "/update", consumes = {"*/*"})
//    public ResponseEntity<?> update(@RequestBody TextI18NUpdateDTO textI18N){
//        TextI18N exitingTextI18N = this.textI18NService.findByTextKey(textI18N.getTextKey());
//        this.textI18NService.update(exitingTextI18N, textI18N);
//        return new ResponseEntity<>(this.textI18NService.update(exitingTextI18N, textI18N), HttpStatus.OK);
//    }

//    @DeleteMapping("/delete/")
//    public ResponseEntity<?> delete(@RequestParam(value = "id", required = true) String textKey){
//        this.textI18NService.delete(textKey);
//        return new ResponseEntity<>("Text deleted!", HttpStatus.OK);
//    }
}
