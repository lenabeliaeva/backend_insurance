package com.example.demo.impl;

import com.example.demo.ConnectionManager;
import com.example.demo.dao.PropertyDao;
import com.example.demo.entity.DictItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DictItemDaoImpl implements PropertyDao {
    ConnectionManager cm = new ConnectionManager();
    Connection connection = cm.getConnection();

    @Override
    public ArrayList<DictItem> getStringById(String query) {
        ArrayList<DictItem> list = new ArrayList<>();
        DictItem i = new DictItem();
        i.setId(0); i.setName("Выберите Ваш вариант");
        list.add(i);
        if (connection != null){
            try {
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    DictItem item = new DictItem();
                    item.setId(rs.getInt("id"));
                    item.setName(rs.getString("name"));
                    list.add(item);
                }
                return list;
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public ArrayList<DictItem> getIntById(String query) {
        ArrayList<DictItem> list = new ArrayList<>();
        DictItem i = new DictItem();
        i.setId(0); i.setName("Выберите Ваш вариант");
        list.add(i);
        if (connection != null){
            try {
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                if (query.contains("power")){
                    while (rs.next()){
                        DictItem item = new DictItem();
                        item.setId(rs.getInt("id"));
                        item.setName(rs.getInt("power")+"");
                        list.add(item);
                    }
                } else {
                    while (rs.next()) {
                        DictItem item = new DictItem();
                        item.setId(rs.getInt("id"));
                        item.setName(rs.getInt("year")+"");
                        list.add(item);
                    }
                }
                return list;
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
        return list;
    }
}
