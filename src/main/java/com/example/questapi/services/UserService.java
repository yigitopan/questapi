package com.example.questapi.services;

import com.example.questapi.entities.User;
import com.example.questapi.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
   public UserService(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveOneUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getOneUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User getOneUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User updateOneUser(Long userId, User updatedUser) {
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

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
}
