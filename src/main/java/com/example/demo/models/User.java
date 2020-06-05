package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String email, name, secondName, surname, birthDate, passport;
    private List<Police> policies = new ArrayList<>();

    public User(String email, String name, String secondName, String surname, String birthDate, String passport){
        this.email = email;
        this.name = name;
        this.secondName = secondName;
        this.surname = surname;
        this.birthDate = birthDate;
        this.passport = passport;
    }
    
    public void addPolice(Police police){
        policies.add(police);
    }

    public List<Police> getPolicies(){
        return policies;
    }
}
