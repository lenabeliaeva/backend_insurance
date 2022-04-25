package com.example.demo.controllers;

import com.example.demo.impl.DictItemServiceImpl;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DictionaryController {
    private DictItemServiceImpl service = new DictItemServiceImpl();

    @GetMapping("/getTsType")
    public String getTsType() {
        return service.getStringById("select id, name from ts_type");
    }

    @RequestMapping("/getMarks")
    public String getMarks(String id) {
        int thisId = new Gson().fromJson(id, int.class);
        return service.getStringById("select id, name from marks where ts_type_id = " + thisId);
    }

    @RequestMapping("/getModels")
    public String getModels(String id) {
        int thisId = new Gson().fromJson(id, int.class);
        return service.getStringById("select id, name from model where marks_id = " + thisId);
    }

    @RequestMapping("/getYears")
    public String getYears(String id) {
        int thisId = new Gson().fromJson(id, int.class);
        return service.getIntById("select id, year from year where model_id = " + thisId);
    }

    @RequestMapping("/getPower")
    public String getPowers(String id) {
        int thisId = new Gson().fromJson(id, int.class);
        return service.getIntById("select id, power from power where year_id = " + thisId);
    }
}
