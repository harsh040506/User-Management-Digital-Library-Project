package com.example.AddUserbyAdminApplication.Controller;

import com.example.AddUserbyAdminApplication.DataTransferObject.UserDto;
import com.example.AddUserbyAdminApplication.Entity.User;
import com.example.AddUserbyAdminApplication.Service.UserService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/User")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController( UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerSave(@ModelAttribute("user") UserDto userDto, Model model) {
        User user = userService.findByUsername(userDto.getUsername());
        if (user != null) {
            model.addAttribute("userExist", true);
            return "register"; // Stay on the register page if user already exists
        }

        userService.save(userDto);
        return "redirect:http://localhost:8084/Admin/manage"; // Redirect to the specified URL after successful registration
    }
}