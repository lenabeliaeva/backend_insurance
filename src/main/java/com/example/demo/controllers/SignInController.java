package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class PasswordController {
    final String LOGIN = "l";
    final String PASSWORD = "1";

    @RequestMapping("/login")
    public String check(@RequestParam String login, @RequestParam String password) {
        String response;
        if (login.equals(LOGIN))
            if (password.equals(PASSWORD))
                response = "Пароль верный";
            else
                response = "Пароль неверный";
        else response = "Нет такого логина";
        return response;
    }
}
