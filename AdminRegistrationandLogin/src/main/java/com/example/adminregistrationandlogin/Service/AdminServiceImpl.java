package com.example.adminregistrationandlogin.Service;

import com.example.adminregistrationandlogin.DataTransferObject.AdminDto;
import com.example.adminregistrationandlogin.Entity.Admin;
import com.example.adminregistrationandlogin.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    PasswordEncoder passwordEncoder;

    private AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        super();
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public Admin save(AdminDto userDto) {
        Admin admin = new Admin(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()),
                userDto.getFullname());
        return adminRepository.save(admin);
    }
}