package com.example.demo.controllers;

import com.example.demo.entity.User;
import com.example.demo.impl.UserServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {
    UserServiceImpl service = new UserServiceImpl();

    @PostMapping("/register")
    public String signUp(@RequestParam String user){
        User u = new Gson().fromJson(user, new TypeToken<User>(){}.getType());
        return service.signUp(u);
    }
}
