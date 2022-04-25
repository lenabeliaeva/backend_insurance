package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    String login(User user);

    String signUp(User user);

    String instantSignUp(User user);

    String getUserById(int id);
}
