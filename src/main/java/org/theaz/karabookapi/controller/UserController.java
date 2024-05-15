package org.theaz.karabookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.theaz.karabookapi.entity.User;
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

    @PostMapping(value = "/add", consumes = {"*/*"})
    public ResponseEntity<?> save(@ModelAttribute User user){
        this.userService.save(user);
        return new ResponseEntity<>("User added!", HttpStatus.OK);
    }
}
