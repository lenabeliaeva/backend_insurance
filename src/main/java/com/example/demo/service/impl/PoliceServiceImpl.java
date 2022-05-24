package com.example.demo.service.impl;

import com.example.demo.entity.Police;
import com.example.demo.repository.PoliceRepository;
import com.example.demo.service.api.PoliceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PoliceServiceImpl implements PoliceService {

    @Autowired
    PoliceRepository repository;

    @Override
    public Police add(Police police) {
        return repository.save(police);
    }

    @Override
    public List<Police> getPoliceListForUser(long userId) {
        return repository.findById(userId).stream().collect(Collectors.toList());
    }

    public Police prolong(Police police) {
        //TODO
        return new Police();
    }
}
