package com.example.demo.models;

public class Product {
    private String id;
    private String name;
    private String description;
    private final String CATEGORY_ID;

    public Product(String id, String name, String description, String categoryId){
        this.id = id;
        this.name = name;
        this.description = description;
        this.CATEGORY_ID = categoryId;
    }

    public String getCategoryId() {
        return CATEGORY_ID;
    }
}
