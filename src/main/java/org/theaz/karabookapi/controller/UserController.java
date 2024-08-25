package org.theaz.karabookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.theaz.karabookapi.entity.User;
import org.theaz.karabookapi.service.AchivementProgressService;
import org.theaz.karabookapi.service.ImageProgressService;
import org.theaz.karabookapi.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ImageProgressService imageProgressService;
    @Autowired
    private AchivementProgressService achivementProgressService;

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(this.userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/get/all/ByUserEmail/")
    public ResponseEntity<?> getUserByUserEmail(@RequestParam(value = "value", required = true) String email) {
        return new ResponseEntity<>(this.userService.findByUserEmail(email), HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> save(@RequestBody User user) {
        if (!user.getUserEmail().contains("@") || user.getUserEmail().length() < 6)
            return new ResponseEntity<>(
                    !user.getUserEmail().contains("@")
                            ? "Users mail does not contain @ character! Please check user mail and try again!"
                            : user.getUserEmail().length() < 6
                                    ? "Users mail is too short! Please check user mail and try again!"
                                    : "Users mail is invalid! Please check user mail and try again!",
                    HttpStatus.CONFLICT);
        this.userService.save(user);
        User newUser = this.userService.findByUserEmail(user.getUserEmail());
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @DeleteMapping({ "/delete/byId" })
    public void delete(@RequestParam(value = "value", required = true) Long user_id) {
        this.imageProgressService.deleteByUserId(user_id);
        this.achivementProgressService.deleteAllByUserId(user_id);
        this.userService.deleteByUserId(user_id);
    }
}
