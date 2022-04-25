package com.example.demo.controllers;

import com.example.demo.entity.User;
import com.example.demo.impl.UserServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class SignInController {
    UserServiceImpl service = new UserServiceImpl();

    @PostMapping("/login")
    public String login(@RequestParam String login, @RequestParam String password) {
        User user = new User();
        user.setEmail(login);
        user.setPassword(password);
        return service.login(user);
    }

    @PostMapping("/registerInstantly")
    public String signUpInstantly(@RequestParam String user) {
        User u = new Gson().fromJson(user, new TypeToken<User>() {
        }.getType());
        return service.instantSignUp(u);
    }
}
