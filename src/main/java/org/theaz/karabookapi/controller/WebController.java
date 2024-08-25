package org.theaz.karabookapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;

@Controller
public class WebController {
    
    @GetMapping("/")
    public String mainPage(Model model) {
        return "main";
    }

    @GetMapping("/error/403")
    public String forbiddenPage(Model model) {
        return "403";
    }
}
