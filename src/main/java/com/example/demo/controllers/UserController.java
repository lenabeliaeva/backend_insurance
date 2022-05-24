package com.example.demo.controllers;

import com.example.demo.entity.User;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.NoPermissionException;
import javax.security.auth.login.LoginException;

@RestController
public class UserController {
    @Autowired
    UserServiceImpl service;

    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(@RequestParam(name = "userId") final Long userId) {
        return ResponseEntity.ok(service.getUserById(userId));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam(name = "login") String login, @RequestParam(name = "password") String password) throws LoginException {
        return ResponseEntity.ok(service.login(login, password));
    }

    @PostMapping("/registerInstantly")
    public ResponseEntity<User> signUpInstantly(@RequestParam(name = "login") String login, @RequestParam(name = "password") String password) throws NoPermissionException {
        return ResponseEntity.ok(service.instantSignUp(login, password));
    }

    @PostMapping("/register")
    public ResponseEntity<User> signUp(@RequestParam(name = "login") String login, @RequestParam(name = "password") String password) throws LoginException {
        return ResponseEntity.ok(service.signUp(login, password));
    }
}
