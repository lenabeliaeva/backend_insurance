package com.example.demo.service.api;

import com.example.demo.entity.Police;

public interface PoliceService {
    String add(Police police);

    String getPoliceListForUser(int user_id);

    String prolong(Police police);
}
