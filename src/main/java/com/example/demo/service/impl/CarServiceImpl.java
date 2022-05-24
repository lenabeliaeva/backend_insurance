package com.example.demo.service.impl;

import com.example.demo.entity.Car;
import com.example.demo.entity.dictionary.KBM;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.KbmRepository;
import com.example.demo.repository.PowerRepository;
import com.example.demo.service.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarRepository repository;

    @Autowired
    PowerRepository powerRepository;

    @Autowired
    KbmRepository kbmRepository;

    @Override
    public Car calculateInsuranceCost(Car car) {
//        if (car.getId() == 0) {
//            car.setKbm(1);  1 was an id probably
//            car.setInsuranceCost(0);
//            car = dao.save(car);
//        }
        int tsTypeCoeff = car.getTsType().getCoefficient();
        double powerCoeff = car.getPower().getCoefficient();
        double cost = tsTypeCoeff * powerCoeff;
        car.setInsuranceCost(cost);
        return car;
    }

    @Override
    public Car saveCar(Car car) {
        car.setKbm(kbmRepository.findById(1L).orElse(new KBM()));
        return repository.save(car);
    }

    @Override
    public Car getCar(long id) {
        return repository.findById(id).orElse(new Car());
    }

//    public String prolong(Car car) {
//        double cost = car.getInsuranceCost();
//        double kbm = dao.getNewKbmCoefficient(car.getInsurCaseCount(), car.getKbm().getId());
//        cost *= kbm;
//        car.setInsuranceCost(cost);
//        return new Gson().toJson(car);
//    }
}
