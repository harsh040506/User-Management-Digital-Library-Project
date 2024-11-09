package com.example.adminregistrationandlogin.Service;


import com.example.adminregistrationandlogin.DataTransferObject.AdminDto;
import com.example.adminregistrationandlogin.Entity.Admin;

public interface AdminService {

    Admin findByUsername(String username);
    Admin save(AdminDto adminDto);
}