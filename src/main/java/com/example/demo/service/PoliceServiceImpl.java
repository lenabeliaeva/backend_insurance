package com.example.demo.service;

import com.example.demo.entity.Police;
import com.example.demo.repository.PoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PoliceServiceImpl {

    @Autowired
    PoliceRepository repository;

    public Police add(Police police) {
        return repository.save(police);
    }

    public List<Police> getPoliceListForUser(long userId) {
        return (List<Police>) repository.findAllById(Collections.singleton(userId));
    }

    public Police prolong(Police police) {
        //TODO
        return new Police();
    }
}
