package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PasswordController {
    private String response = null;

    @GetMapping("/login")
    public void get_info(String login, String password) {
        String login1 = "lena";
        String password1 = "123456aA";
        if (login.equals(login1))
            if (password.equals(password1))
                response = "Пароль верный";
            else response = "Пароль неверный";
        else response = "Нет такого логина";
    }

    @PostMapping("/login")
    public String check_password() {
        return response;
    }
}
