package com.example.demo.service.api;

import com.example.demo.entity.Police;

import java.util.List;

public interface PoliceService {
    Police add(Police police);

    List<Police> getPoliceListForUser(long userId);

    Police prolong(Police police);
}
