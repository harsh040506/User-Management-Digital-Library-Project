package com.example.registrationandlogin.Repository;

import com.example.registrationandlogin.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}