package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PasswordController {
    Map<String, String> passwords = new HashMap<>();

    @GetMapping("/login")
    public String check_password(String login, String password){
        passwords.put("lena.belia", "123456aA");
        if (passwords.containsKey(login)) {
            if (passwords.get(login).equals(password))
                return "Пароль верный";
            else return "Пароль неверный";
        }
        else return "Нет такого логина";
    }
}
