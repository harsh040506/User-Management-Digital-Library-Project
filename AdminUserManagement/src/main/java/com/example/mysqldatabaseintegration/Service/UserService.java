package com.example.mysqldatabaseintegration.Service;

import com.example.mysqldatabaseintegration.Model.Users;
import com.example.mysqldatabaseintegration.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Renamed for clarity

    // Get all users from the database
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a user by ID
    public Users getUserById(long id) {
        Optional<Users> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }

    // Update an existing user
    public Users updateUser(Users user, long id) {
        Users existingUser = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found with ID: " + id)
        );

        // Update fields
        existingUser.setFullname(user.getFullname()); // Ensure these match your Users class
        existingUser.setPassword(user.getPassword());
        existingUser.setUsername(user.getUsername());

        // Save updated user
        return userRepository.save(existingUser);
    }

    // Delete a user by ID
    public void deleteUser(long id) {
        // Check if the user exists
        userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found with ID: " + id)
        );
        // Delete the user
        userRepository.deleteById(id);
    }
}