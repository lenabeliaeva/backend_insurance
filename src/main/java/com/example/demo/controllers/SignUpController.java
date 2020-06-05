package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@RestController
public class SignUpController {
    final static String url = "jdbc:postgresql://localhost:5432/postgres";
    final static String user = "postgres";
    final static String pass = "vogula";

    @RequestMapping("/register")
    public String signUp(@RequestParam String login, @RequestParam String password){
        String response = "false";
        int x = 1;
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement ps = connection.prepareStatement("insert into login(email, password) values (?, ?)");
            ps.setString(1, login);
            ps.setString(2, password);
            x = ps.executeUpdate();
            if (x == 1){
                response = "true";
            }
            connection.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
}
