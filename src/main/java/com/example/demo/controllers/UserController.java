package com.example.demo.controllers;

import com.example.demo.entity.User;
import com.example.demo.entity.userdata.ActivitySphere;
import com.example.demo.entity.userdata.EducationLevel;
import com.example.demo.entity.userdata.Gender;
import com.example.demo.entity.userdata.IncomeLevel;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NoPermissionException;
import javax.security.auth.login.LoginException;
import java.util.List;

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
    public ResponseEntity<User> signUp(@RequestBody User user) throws LoginException {
        return ResponseEntity.ok(service.signUp(user));
    }

    @PatchMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        return ResponseEntity.ok(service.update(user));
    }

    @GetMapping("/genders")
    public ResponseEntity<List<Gender>> getAllGenders() {
        return ResponseEntity.ok(service.getAllGenders());
    }

    @GetMapping("/educations")
    public ResponseEntity<List<EducationLevel>> getAllEducations() {
        return ResponseEntity.ok(service.getAllEducationLevels());
    }

    @GetMapping("/activities")
    public ResponseEntity<List<ActivitySphere>> getAllActivities() {
        return ResponseEntity.ok(service.getAllActivities());
    }

    @GetMapping("/incomes")
    public ResponseEntity<List<IncomeLevel>> getAllIncomes() {
        return ResponseEntity.ok(service.getAllIncomes());
    }
}
