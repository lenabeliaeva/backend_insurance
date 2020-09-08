package com.example.demo.impl;

import com.example.demo.ConnectionManager;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.util.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    ConnectionManager cm = new ConnectionManager();
    Connection connection = cm.getConnection();

    @Override
    public User findByLogin(String login) {
        User user = null;
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "select * from \"user\" where login = ?");
                preparedStatement.setString(1, login);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setSecondName(rs.getString("second_name"));
                    user.setSurname(rs.getString("surname"));
                    user.setPassport(rs.getString("passport"));
                    user.setCity(rs.getString("city"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("login"));
                    return user;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public User save(User user) {
        if (connection != null){
            try {
                PreparedStatement ps = connection.prepareStatement(
                        "insert into \"user\"(name, second_name, surname, passport, city, login, password) values (?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, user.getName());
                ps.setString(2, user.getSecondName());
                ps.setString(3, user.getSurname());
                ps.setString(4, user.getPassport());
                ps.setString(5, user.getCity());
                ps.setString(6, user.getEmail());
                ps.setString(7, DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
                ps.executeUpdate();
                ps = connection.prepareStatement(
                        "select id from \"user\" where login = ?");
                ps.setString(1, user.getEmail());
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    user.setId(rs.getInt("id"));
                }
                return user;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public User findById(int id) {
        User user = null;
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "select * from \"user\" where id = ?");
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setSecondName(rs.getString("second_name"));
                    user.setSurname(rs.getString("surname"));
                    user.setPassport(rs.getString("passport"));
                    user.setCity(rs.getString("city"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("login"));
                    return user;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return user;
    }
}
