package com.example.mysqldatabaseintegration.Controller;

import com.example.mysqldatabaseintegration.Model.Users;
import com.example.mysqldatabaseintegration.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("Admin/manage") // Updated to reflect user management
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllUsers(Model model) {
        List<Users> users = userService.getAllUsers(); // Updated to call getAllUsers
        model.addAttribute("users", users); // Updated attribute name
        return "EmployeeManagement"; // Updated view name
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Users> getAllUsersApi() {
        return userService.getAllUsers(); // Updated to call getAllUsers
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable("id") long userId) { // Updated variable name
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK); // Updated to call getUserById
    }

    @PutMapping("/api/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable("id") long id, @RequestBody Users user) { // Updated parameter name
        return new ResponseEntity<>(userService.updateUser(user, id), HttpStatus.OK); // Updated to call updateUser
    }

    @DeleteMapping("/api/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) { // Updated method name
        userService.deleteUser(id); // Updated to call deleteUser
        return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK); // Updated success message
    }
}