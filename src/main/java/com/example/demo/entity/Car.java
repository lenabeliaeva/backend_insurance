package com.example.demo.entity;

public class Car {
    private int id, tsType, marka, model, year, power, kbm, insurCaseCount;
    private double insuranceCost;
    private String regNumber;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTsType(int tsType) {
        this.tsType = tsType;
    }

    public int getTsType() {
        return tsType;
    }

    public void setMarka(int marka) {
        this.marka = marka;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setInsuranceCost(double insuranceCost) {
        this.insuranceCost = insuranceCost;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMarka() {
        return marka;
    }

    public int getModel() {
        return model;
    }

    public int getPower() {
        return power;
    }

    public int getYear() {
        return year;
    }

    public double getInsuranceCost() {
        return insuranceCost;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public int getKbm() {
        return kbm;
    }

    public void setKbm(int kbm) {
        this.kbm = kbm;
    }

    public int getInsurCaseCount() {
        return insurCaseCount;
    }

    public void setInsurCaseCount(int insurCaseCount) {
        this.insurCaseCount = insurCaseCount;
    }
}
