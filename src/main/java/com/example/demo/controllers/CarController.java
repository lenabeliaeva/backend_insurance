package com.example.demo.controllers;

import com.example.demo.entity.Car;
import com.example.demo.service.impl.CarServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
    CarServiceImpl service = new CarServiceImpl();

    @RequestMapping("/calculate")
    public String calculateCost(String car) {
        Car c = new Gson().fromJson(car, new TypeToken<Car>() {
        }.getType());
        return service.calculateInsuranceCost(c);
    }

    @RequestMapping("/getCar")
    public String getCarById(String id) {
        int carId = new Gson().fromJson(id, int.class);
        return service.getCar(carId);
    }

    @RequestMapping("/prolongCar")
    public String prolongInsurance(String carId) {
        int id = new Gson().fromJson(carId, int.class);
        String car = service.getCar(id);
        Car c = new Gson().fromJson(car, new TypeToken<Car>() {
        }.getType());
        c.setKbm(1);
        c.setInsurCaseCount(1);
        return service.prolong(c);
    }
}
