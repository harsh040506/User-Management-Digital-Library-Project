package com.example.adminfilterusers.Controller;

import com.example.adminfilterusers.Entity.Users;
import com.example.adminfilterusers.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/Users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllUsers(Model model) {
        List<Users> users = userService.findAllUsers();
        model.addAttribute("users", users);  // Add users to the model
        return "Users";  // Return the template "Users.html"
    }

    @GetMapping("/search-by-username")
    public String searchUsersByUsername(@RequestParam("name") String name, Model model) {
        List<Users> users = userService.findUsersByUsernameLike(name);
        model.addAttribute("users", users);  // Add search results
        model.addAttribute("searchType", "Username");
        model.addAttribute("searchTerm", name);
        return "Users";  // Reuse the same template "Users.html"
    }

    @GetMapping("/search-by-fullname")
    public String searchUsersByFullname(@RequestParam("name") String name, Model model) {
        List<Users> users = userService.findUsersByFullnameLike(name);
        model.addAttribute("users", users);  // Add search results
        model.addAttribute("searchType", "Fullname");
        model.addAttribute("searchTerm", name);
        return "Users";  // Reuse the same template "Users.html"
    }
}

@RestController
@RequestMapping("/users")
class UserAPIController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Users> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/fullname/search/{name}")
    public List<Users> getUsersByFullnameLike(@PathVariable String name) {
        return userService.findUsersByFullnameLike(name);
    }

    @GetMapping("/username/search/{name}")
    public List<Users> getUsersByUsernameLike(@PathVariable String name) {
        return userService.findUsersByUsernameLike(name);
    }

    @GetMapping("/fullname/{fullname}")
    public List<Users> getUserByFullname(@PathVariable String fullname) {
        return userService.findUserByFullname(fullname);
    }

    @GetMapping("/username/{username}")
    public Users getUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }

    @GetMapping("/count/{fullname}")
    public long getCountByFullname(@PathVariable String fullname) {
        return userService.findCountByFullname(fullname);
    }
}