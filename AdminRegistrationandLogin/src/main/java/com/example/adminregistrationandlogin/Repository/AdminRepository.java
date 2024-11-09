package com.example.adminregistrationandlogin.Repository;

import com.example.adminregistrationandlogin.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
}