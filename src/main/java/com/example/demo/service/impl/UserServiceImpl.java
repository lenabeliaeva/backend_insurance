package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.naming.NoPermissionException;
import javax.security.auth.login.LoginException;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository repository;

    public User login(String login, String password) throws LoginException {
        User foundedUser = repository.findByEmail(login);
        if (foundedUser != null) {
            if (DigestUtils.md5DigestAsHex(password.getBytes()).equals(foundedUser.getPassword())) {
                return foundedUser;
            } else throw new LoginException();
        } else throw new LoginException();
    }

    public User signUp(String login, String password) throws LoginException {
        Pattern email = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
        );
        if (email.matcher(login).matches()) {
            User foundedUser = repository.findByEmail(login);
            if (foundedUser == null) {
                User user = new User();
                user.setEmail(login);
                user.setPassword(password);
                return repository.save(user);
            } else {
                throw new LoginException("User exists");
            }
        } else {
            throw new LoginException();
        }
    }
    
    public User instantSignUp(String login, String password) throws NoPermissionException {
        User foundedUser = repository.findByEmail(login);
        if (foundedUser != null) {
            if (DigestUtils.md5DigestAsHex(password.getBytes()).equals(foundedUser.getPassword())) {
                return foundedUser;
            } else throw new NoPermissionException();
        } else {
            User user = new User();
            user.setEmail(login);
            user.setPassword(password);
            return repository.save(user);
        }
    }

    public User getUserById(Long id) {
        return repository.findById(id).orElse(new User());
    }
}
