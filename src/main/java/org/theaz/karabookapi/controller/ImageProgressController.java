package org.theaz.karabookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.theaz.karabookapi.service.ImageProgressService;

@RestController
@RequestMapping("/api/image/progress")
public class ImageProgressController {
    @Autowired
    private ImageProgressService imageProgressService;
}
