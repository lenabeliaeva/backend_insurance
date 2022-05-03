package com.example.demo.controllers;

import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.service.api.UserService;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    UserService service = new UserServiceImpl();

    @RequestMapping("/getUser")
    public String getUser(String userId) {
        int id = new Gson().fromJson(userId, int.class);
        return service.getUserById(id);
    }
}
