package com.example.adminfilterusers.Repository;

import com.example.adminfilterusers.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long> {
    List<Users> findByFullname(String fullname);
    Users findByUsername(String username);

    @Query("SELECT u FROM Users u WHERE u.username LIKE %:name%")
    List<Users> findByUsernameLike(@Param("name") String name);

    @Query("SELECT u FROM Users u WHERE u.fullname LIKE %:name%")
    List<Users> findByFullnameLike(@Param("name") String name);

    @Query("SELECT COUNT(u) FROM Users u WHERE u.fullname = :name")
    long countByFullname(@Param("name") String name);
}