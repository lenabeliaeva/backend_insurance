package com.example.demo.controllers;

import com.example.demo.entity.Car;
import com.example.demo.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

    @Autowired
    CarServiceImpl service;

    @GetMapping("/calculate")
    public ResponseEntity<Car> calculateCost(@RequestBody Car car) {
        return ResponseEntity.ok(service.calculateInsuranceCost(car));
    }

    @GetMapping("/getCar")
    public ResponseEntity<Car> getCarById(@RequestParam(name = "carId") Long id) {
        return ResponseEntity.ok(service.getCar(id));
    }

//    @RequestMapping("/prolongCar")
//    public String prolongInsurance(String carId) {
//        int id = new Gson().fromJson(carId, int.class);
//        String car = service.getCar(id);
//        Car c = new Gson().fromJson(car, new TypeToken<Car>() {
//        }.getType());
////        c.setKbm(1);
//        c.setInsurCaseCount(1);
//        return service.prolong(c);
//    }
}
