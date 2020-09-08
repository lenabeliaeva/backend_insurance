package com.example.demo.impl;

import com.example.demo.ConnectionManager;
import com.example.demo.dao.PoliceDao;
import com.example.demo.entity.Police;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PoliceDaoImpl implements PoliceDao {
    ConnectionManager cm = new ConnectionManager();
    Connection connection = cm.getConnection();

    @Override
    public String save(Police police) {
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement(
                        "insert into police(user_id, car_id, cost, object_type, start_date, end_date)  values (?, ?, ?, ?, ?, ?)");
                ps.setInt(1, police.getUser_id());
                ps.setInt(2, police.getCar_id());
                ps.setDouble(3, police.getCost());
                ps.setInt(4, police.getTypeOfObject());
                ps.setDate(5, (Date) police.getStart());
                ps.setDate(6, (Date) police.getEnd());
                ps.executeUpdate();
                return "true";
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return "false";
    }

    @Override
    public List<Police> getPolicies(int user_id) {
        List<Police> policies = null;
        Police police;
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement(
                        "select * from police where user_id = ?");
                ps.setInt(1, user_id);
                ResultSet rs = ps.executeQuery();
                policies = new ArrayList<>();
                while (rs.next()) {
                    police = new Police();
                    police.setId(rs.getInt("id"));
                    police.setCar_id(rs.getInt("car_id"));
                    police.setUser_id(rs.getInt("user_id"));
                    police.setNumber(rs.getInt("number"));
                    police.setTypeOfObject(rs.getInt("object_type"));
                    police.setCost(rs.getInt("cost"));
//                    police.setStart(rs.getDate("start_date"));
//                    police.setEnd(rs.getDate("end_date"));
                    policies.add(police);
                }
                return policies;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return policies;
    }

    public int getPoliceByNumber(int number){
        int userId = 0;
        if (connection != null){
            try {
                PreparedStatement ps = connection.prepareStatement(
                        "select user_id from police where number = ?");
                ps.setInt(1, number);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    userId = rs.getInt("user_id");
                }
                return userId;
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
        return userId;
    }
}
