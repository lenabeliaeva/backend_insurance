package com.example.demo.impl;

import com.example.demo.entity.Car;
import com.example.demo.entity.Police;
import com.example.demo.service.PoliceService;
import com.google.gson.Gson;

import java.util.List;

public class PoliceServiceImpl implements PoliceService {
    PoliceDaoImpl policeDao = new PoliceDaoImpl();
    CarDaoImpl carDao = new CarDaoImpl();

    @Override
    public String add(Police police) {
        return policeDao.save(police);
    }

    @Override
    public String getPoliceListForUser(int user_id) {
        List<Police> usersPolicies = policeDao.getPolicies(user_id);
        if (usersPolicies != null) {
            return new Gson().toJson(usersPolicies);
        } else return "false";
    }

    public String prolong(Police police) {
        String result = "";
        Car car = carDao.getCarById(police.getCar_id());

        return result;
    }
}
