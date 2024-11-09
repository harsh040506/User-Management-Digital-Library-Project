package com.example.registrationandlogin.Service;

import com.example.registrationandlogin.DataTransferObject.UserDto;
import com.example.registrationandlogin.Entity.User;

public interface UserService {

    User findByUsername(String username);
    User save(UserDto userDto);
}