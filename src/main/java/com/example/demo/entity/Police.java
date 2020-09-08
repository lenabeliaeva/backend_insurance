package com.example.demo.entity;

import java.util.Date;

public class Police {
    private int id, userId, carId, typeOfObject, number;
    private double cost;
    private Date start, end;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return userId;
    }

    public void setUser_id(int user_id) {
        this.userId = user_id;
    }

    public int getCar_id() {
        return carId;
    }

    public void setCar_id(int car_id) {
        this.carId = car_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setTypeOfObject(int typeOfObject) {
        this.typeOfObject = typeOfObject;
    }

    public int getTypeOfObject() {
        return typeOfObject;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public Date getEnd() {
        return end;
    }

    public Date getStart() {
        return start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setStart(Date start) {
        this.start = start;
    }
}
