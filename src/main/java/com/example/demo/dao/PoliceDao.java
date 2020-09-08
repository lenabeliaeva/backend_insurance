package com.example.demo.dao;

import com.example.demo.entity.Police;

import java.util.List;

public interface PoliceDao {
    String save(Police police);
    List<Police> getPolicies(int user_id);
    int getPoliceByNumber(int number);
}
