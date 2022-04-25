package com.example.demo.dao;

import com.example.demo.entity.DictItem;

import java.util.ArrayList;

public interface PropertyDao {
    ArrayList<DictItem> getStringById(String query);

    ArrayList<DictItem> getIntById(String query);
}
