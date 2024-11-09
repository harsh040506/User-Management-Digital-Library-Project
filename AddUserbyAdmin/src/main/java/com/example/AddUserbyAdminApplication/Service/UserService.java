package com.example.AddUserbyAdminApplication.Service;

import com.example.AddUserbyAdminApplication.DataTransferObject.UserDto;
import com.example.AddUserbyAdminApplication.Entity.User;

public interface UserService {

    User findByUsername(String username);
    User save(UserDto userDto);
}