package com.lab1.service;

import com.lab1.model.User;
import com.lab1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public Map<String, String> login(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB == null) {
            return Map.of("message", "No such user");
        }

        if (!userFromDB.getUsername().equals(user.getUsername()) || !user.getPassword().equals(userFromDB.getPassword())) {
            return Map.of("message", "Incorrect login or password");
        }

        return Map.of("message", "authorized");
    }

    public Map<String, String> saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return Map.of("message", "Such a user already exists");
        }
        userRepository.save(user);
        return Map.of("message", "authorized");
    }
}
