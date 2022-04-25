package com.example.demo.dao;

import com.example.demo.entity.Car;

public interface CarDao {
    Car save(Car car);

    Car getCarById(int id);
}
