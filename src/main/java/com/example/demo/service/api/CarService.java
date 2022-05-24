package com.example.demo.service.api;

import com.example.demo.entity.Car;

public interface CarService {
    Car calculateInsuranceCost(Car car);

    Car saveCar(Car car);

    Car getCar(long id);
}
