package com.example.demo.controllers;

import com.example.demo.entity.Police;
import com.example.demo.service.impl.PoliceServiceImpl;
import com.example.demo.service.api.PoliceService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PoliciesController {
    PoliceService service = new PoliceServiceImpl();

    @RequestMapping("/addPolice")
    public String addPolice(String police) {
        Police p = new Gson().fromJson(police, new TypeToken<Police>() {
        }.getType());
        return service.add(p);
    }

    @RequestMapping("/getPoliciesList")
    public String getPoliciesList(String userId) {
        int uId = new Gson().fromJson(userId, int.class);
        return service.getPoliceListForUser(uId);
    }

    public String prolong(String police) {
        Police p = new Gson().fromJson(police, new TypeToken<Police>() {
        }.getType());
        return service.prolong(p);
    }
}
