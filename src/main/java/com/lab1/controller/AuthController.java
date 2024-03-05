package com.lab1.controller;

import com.lab1.model.User;
import com.lab1.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final Logger logger;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
        this.logger = LoggerFactory.getLogger(AuthController.class);
    }

    @PostMapping("/login")
    public Map<String, String> signIn(@Valid @RequestBody User user) {
        logger.info("request to login");
        return userService.login(user);
    }

    @PostMapping("/register")
    public Map<String, String> signUp(@Valid @RequestBody User user) {
        logger.info("request to register");
        return userService.saveUser(user);
    }

}
