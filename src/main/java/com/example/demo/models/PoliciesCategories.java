package com.example.demo.models;

import com.example.demo.entity.CategoryMock;

import java.util.LinkedList;
import java.util.List;

public class PoliciesCategories {
    private static List<CategoryMock> policies;

    private static void setPolicies() {
        policies = new LinkedList<>();
        policies.add(new CategoryMock(1, "Страхование жизни"));
        policies.add(new CategoryMock(2, "Автострахование"));
        policies.add(new CategoryMock(3, "Страхование имущества"));
    }

    public static List<CategoryMock> getPolicies() {
        setPolicies();
        return policies;
    }
}
