package com.example.demo.impl;

import com.example.demo.ConnectionManager;
import com.example.demo.dao.CarDao;
import com.example.demo.entity.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarDaoImpl implements CarDao {
    ConnectionManager cm = new ConnectionManager();
    Connection connection = cm.getConnection();

    @Override
    public Car save(Car car) {
        if (connection != null){
            try {
                PreparedStatement ps = connection.prepareStatement(
                        "insert into car(ts_type, marka, model, power, year, reg_number, kbm) values (?, ?, ?, ?, ?, ?, 1)");
                ps.setInt(1, car.getTsType());
                ps.setInt(2, car.getMarka());
                ps.setInt(3, car.getModel());
                ps.setInt(4, car.getPower());
                ps.setInt(5, car.getYear());
                ps.setString(6, car.getRegNumber());
                ps.executeUpdate();
                ps = connection.prepareStatement(
                        "select id from car where reg_number = ?");
                ps.setString(1, car.getRegNumber());
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    car.setId(rs.getInt("id"));
                }
                return car;
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
        return car;
    }

    @Override
    public Car getCarById(int id) {
        Car car = null;
        if (connection != null){
            try{
                PreparedStatement ps = connection.prepareStatement(
                        "select * from car where id = ?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    car = new Car();
                    car.setId(rs.getInt("id"));
                    car.setMarka(rs.getInt("marka"));
                    car.setModel(rs.getInt("model"));
                    car.setPower(rs.getInt("power"));
                    car.setYear(rs.getInt("year"));
                    car.setRegNumber(rs.getString("reg_number"));
                    car.setInsuranceCost(rs.getDouble("insurance_cost"));
                }
                return car;
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
        return car;
    }

    public int getTsTypeCoefficient(int id){
        int coeff = 1;
        if (connection != null){
            try{
                PreparedStatement ps = connection.prepareStatement(
                        "select coefficient from ts_type where id = ?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    coeff = rs.getInt("coefficient");
                }
                return coeff;
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
        return coeff;
    }

    public double getPowerCoefficient(int id){
        double coeff = 1;
        if (connection != null){
            try {
                PreparedStatement ps = connection.prepareStatement(
                        "select coefficient from power where id = ?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    coeff = rs.getDouble("coefficient");
                }
                return coeff;
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
        return coeff;
    }

    public double getNewKbmCoefficient(int count, int id){
        double coeff = 1;
        int newClass = 3;
        if (connection != null){
            try {
                PreparedStatement ps = connection.prepareStatement(
                        "select new_kbm_class from kbm_count where case_count = ? and kbm_id = ?");
                ps.setInt(1, count);
                ps.setInt(2, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    newClass = rs.getInt("new_kbm_class");
                }
                ps = connection.prepareStatement(
                        "select coeff from kbm where class = ?");
                ps.setInt(1, newClass);
                rs = ps.executeQuery();
                if (rs.next()){
                    coeff = rs.getDouble("coeff");
                }
                return coeff;
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
        return coeff;
    }
}
