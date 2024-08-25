package org.theaz.karabookapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.theaz.karabookapi.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserEmail(String userEmail);

    void deleteByUserId(Long user_id);
}
