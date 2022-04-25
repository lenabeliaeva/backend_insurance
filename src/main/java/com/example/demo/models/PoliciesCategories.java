package com.example.demo.models;

import com.example.demo.entity.Category;

import java.util.LinkedList;
import java.util.List;

public class PoliciesCategories {
    private static List<Category> policies;

    private static void setPolicies() {
        policies = new LinkedList<>();
        policies.add(new Category(1, "Страхование жизни"));
        policies.add(new Category(2, "Автострахование"));
        policies.add(new Category(3, "Страхование имущества"));
    }

    public static List<Category> getPolicies() {
        setPolicies();
        return policies;
    }
}
