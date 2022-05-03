package com.example.demo.models;

public class ProductMock {
    private long id;
    private String name;
    private String description;
    private final String CATEGORY_ID;

    public ProductMock(long id, String name, String description, String categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.CATEGORY_ID = categoryId;
    }

    public String getCategoryId() {
        return CATEGORY_ID;
    }
}
