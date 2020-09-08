package com.example.demo.impl;

import com.example.demo.entity.DictItem;
import com.example.demo.service.DictItemService;
import com.google.gson.Gson;

import java.util.ArrayList;

public class DictItemServiceImpl implements DictItemService {
    DictItemDaoImpl dao = new DictItemDaoImpl();

    public String getStringById(String query){
        ArrayList<DictItem> list = dao.getStringById(query);
        return new Gson().toJson(list);
    }

    public String getIntById(String query){
        ArrayList<DictItem> list = dao.getIntById(query);
        return new Gson().toJson(list);
    }
}
