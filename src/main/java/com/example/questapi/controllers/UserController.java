package com.example.questapi.controllers;

import com.example.questapi.entities.User;
import com.example.questapi.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    /*
    - or constructor injection
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    */

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        System.out.println(newUser);
        return userRepository.save(newUser);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @PutMapping("/{userId}")
    public User updateUserById(@PathVariable Long userId, @RequestBody User updatedUser) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()) {
           User foundUser = userOptional.get();
           foundUser.setUsername(updatedUser.getUsername());
           foundUser.setPassword(updatedUser.getPassword());
           userRepository.save(foundUser);
           return foundUser;
        }
        return null;
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        userRepository.deleteById(userId);
    }



}
