package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@RestController
public class ProductController {
    final static String url = "jdbc:postgresql://localhost:5432/postgres";
    final static String user = "postgres";
    final static String pass = "vogula";

    @RequestMapping("/buy_police")
    public String buyPolice(int policeNumber, int userId){
        String response = "false";
        int x = 1;
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement ps = connection.prepareStatement("insert into police(number, user_id) values (?, ?)");
            ps.setInt(1, policeNumber);
            ps.setInt(2, userId);
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
