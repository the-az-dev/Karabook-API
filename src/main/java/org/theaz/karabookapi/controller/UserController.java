package org.theaz.karabookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.theaz.karabookapi.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(this.userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/get/all/ByUserEmail/")
    public ResponseEntity<?> getUserByUserEmail(@RequestParam(value = "value", required = true) String email) {
        return new ResponseEntity<>(this.userService.findByUserEmail(email), HttpStatus.OK);
    }
}
