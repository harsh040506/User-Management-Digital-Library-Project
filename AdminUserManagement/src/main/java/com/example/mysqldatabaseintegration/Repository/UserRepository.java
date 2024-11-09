package com.example.mysqldatabaseintegration.Repository;

import com.example.mysqldatabaseintegration.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {

}