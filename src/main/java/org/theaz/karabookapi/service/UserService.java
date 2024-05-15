package org.theaz.karabookapi.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.theaz.karabookapi.entity.User;
import org.theaz.karabookapi.repository.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findByUserEmail(String userEmail) {
        return this.userRepository.findByUserEmail(userEmail);
    }
}
