package com.example.demo.service.api;

import com.example.demo.entity.Car;

public interface CarService {
    String calculateInsuranceCost(Car car);

    String saveCar(Car car);

    String getCar(int id);
}
