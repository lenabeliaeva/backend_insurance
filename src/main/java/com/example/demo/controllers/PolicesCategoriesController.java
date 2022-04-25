package com.example.demo.controllers;

import com.example.demo.models.PoliciesCategories;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PolicesCategoriesController {

    @RequestMapping("/policies")
    public String getPoliciesCategories() {
        return new Gson().toJson(PoliciesCategories.getPolicies());
    }
}
