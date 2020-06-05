package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@RestController
public class SignInController {
    final static String url = "jdbc:postgresql://localhost:5432/postgres";
    final static String user = "postgres";
    final static String pass = "vogula";

    @RequestMapping("/login")
    public String login(@RequestParam String login, @RequestParam String password) {
        String response = "false";
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement preparedStatement = connection.prepareStatement("select * from login where email = ? and password = ?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                response = "true";
            }
            connection.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
}
