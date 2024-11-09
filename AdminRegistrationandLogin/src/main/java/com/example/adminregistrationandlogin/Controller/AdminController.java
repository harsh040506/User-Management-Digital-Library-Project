package com.example.adminregistrationandlogin.Controller;

import java.security.Principal;

import com.example.adminregistrationandlogin.DataTransferObject.AdminDto;
import com.example.adminregistrationandlogin.Entity.Admin;
import com.example.adminregistrationandlogin.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Admin")
public class AdminController {

    private final UserDetailsService userDetailsService;
    private final AdminService adminService;

    @Autowired
    public AdminController(UserDetailsService userDetailsService, AdminService adminService) {
        this.userDetailsService = userDetailsService;
        this.adminService = adminService;
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userdetail", userDetails);
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model, AdminDto adminDto) {
        model.addAttribute("admin", adminDto);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model, AdminDto adminDto) {
        model.addAttribute("admin", adminDto);
        return "register";
    }

    @PostMapping("/register")
    public String registerSave(@ModelAttribute("user") AdminDto adminDto, Model model) {
        Admin admin = adminService.findByUsername(adminDto.getUsername());
        if (admin != null) {
            model.addAttribute("userExist", true);
            return "register";
        }

        adminService.save(adminDto);
        return "redirect:/login?success";
    }
}