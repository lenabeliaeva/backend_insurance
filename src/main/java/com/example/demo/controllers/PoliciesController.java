package com.example.demo.controllers;

import com.example.demo.entity.Police;
import com.example.demo.service.PoliceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PoliciesController {

    @Autowired
    PoliceServiceImpl service;

    @GetMapping("/addPolice")
    public ResponseEntity<Police> addPolice(Police police) {
        return ResponseEntity.ok(service.add(police));
    }

    @GetMapping("/getPoliciesList")
    public ResponseEntity<List<Police>> getPoliciesList(@RequestParam(name = "userId") final int userId) {
        return ResponseEntity.ok(service.getPoliceListForUser(userId));
    }

    @PatchMapping("/prolong")
    public ResponseEntity<Police> prolong(Police police) {
        return ResponseEntity.ok(service.prolong(police));
    }
}
