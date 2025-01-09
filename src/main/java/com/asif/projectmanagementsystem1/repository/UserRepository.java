package com.asif.projectmanagementsystem1.repository;

import com.asif.projectmanagementsystem1.model.Chat;
import com.asif.projectmanagementsystem1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

}
