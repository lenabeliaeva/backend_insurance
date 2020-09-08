package com.example.demo.dao;

import com.example.demo.entity.User;

public interface UserDao {
    User findByLogin(String login);
    User save(User user);
    User findById(int id);
}
