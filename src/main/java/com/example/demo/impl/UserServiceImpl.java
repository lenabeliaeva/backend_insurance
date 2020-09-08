package com.example.demo.impl;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.google.gson.Gson;
import org.springframework.util.DigestUtils;

import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {
    UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public String login(User user) {
        User foundedUser = userDao.findByLogin(user.getEmail());
        if (foundedUser != null) {
            if (DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).equals(foundedUser.getPassword())) {
                return new Gson().toJson(foundedUser);
            } else return "falsePassword";
        }
        return "falseLogin";
    }

    @Override
    public String signUp(User user) {
        Pattern email = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
        );
        if (email.matcher(user.getEmail()).matches()) {
            User foundedUser = userDao.findByLogin(user.getEmail());
            if (foundedUser == null) {
                user = userDao.save(user);
                return new Gson().toJson(user);
            }
        }
        return "false";
    }

    @Override
    public String instantSignUp(User user) {
        User foundedUser = userDao.findByLogin(user.getEmail());
        if (foundedUser != null) {
            if (DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).equals(foundedUser.getPassword())) {
                return new Gson().toJson(foundedUser);
            } else return "falsePassword";
        } else {
            user = userDao.save(user);
            return new Gson().toJson(user);
        }
    }

    @Override
    public String getUserById(int id) {
        User foundedUser = userDao.findById(id);
        return new Gson().toJson(foundedUser);
    }
}
