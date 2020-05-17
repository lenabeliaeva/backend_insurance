package com.example.demo.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PoliciesCategories {
    private static Map<String, String> policies;
    private static Map<String, List<Product>> products;

    private static void setPolicies(){
        policies = new HashMap<>();
        policies.put("1", "Страхование жизни");
        policies.put("2", "Автострахование");
        policies.put("3", "Страхование имущества");
    }

    public static Map<String, String> getPolicies(){
        setPolicies();
        return policies;
    }
}
