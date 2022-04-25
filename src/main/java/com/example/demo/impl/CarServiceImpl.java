package com.example.demo.impl;

import com.example.demo.entity.Car;
import com.example.demo.service.CarService;
import com.google.gson.Gson;

public class CarServiceImpl implements CarService {
    CarDaoImpl dao = new CarDaoImpl();

    @Override
    public String calculateInsuranceCost(Car car) {
        if (car.getId() == 0) {
            car.setKbm(1);
            car.setInsuranceCost(0);
            car = dao.save(car);
        }
        int tsTypeCoeff = dao.getTsTypeCoefficient(car.getTsType());
        double powerCoeff = dao.getPowerCoefficient(car.getPower());
        double cost = tsTypeCoeff * powerCoeff;
        car.setInsuranceCost(cost);
        return new Gson().toJson(car);
    }

    @Override
    public String saveCar(Car car) {
        car.setKbm(1);
        car = dao.save(car);
        return new Gson().toJson(car);
    }

    @Override
    public String getCar(int id) {
        Car car = dao.getCarById(id);
        return new Gson().toJson(car);
    }

    public String prolong(Car car) {
        double cost = car.getInsuranceCost();
        double kbm = dao.getNewKbmCoefficient(car.getInsurCaseCount(), car.getKbm());
        cost *= kbm;
        car.setInsuranceCost(cost);
        return new Gson().toJson(car);
    }
}
