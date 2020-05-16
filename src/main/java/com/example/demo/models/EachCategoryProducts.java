package com.example.demo.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EachCategoryProducts {
    private static List<String[]> products;

    private static void setProducts(){
        products = new ArrayList<>();
        products.add( new String[]{"11",
                        "Опасные профессии",
                        "Специальные условия для представителей опасных профессий",
                        "1"});
        products.add(new String[]{
                "12",
                "Классический полис",
                "Стандартный набор услуг",
                "1"});
        products.add(new String[]{
                "21",
                "ОСАГО",
                "Обязательное страхование. Компенсация при нанесении вреда другим участникам дорожного движения",
                "2"});
        products.add(new String[]{
                "22",
                "КАСКО",
                "Добровольное страхование. Компенсация на случай ущерба или угона",
                "2"});
        products.add(new String[]{
                "31",
                "Страхование квартиры",
                "Страхование от взлома, кражи, пожара и других рисков",
                "3"});
        products.add(new String[]{
                "32",
                "Страхование загородной недвижимости",
                "Страхование не только от типичных рисков, но и от стихийных бедствий",
                "3"});
    }

    public static Map<String, String> getProducts(String id) {
        setProducts();
        Map<String, String> result = new HashMap<>();
        for (String[] product : products) {
            if (product[3].equals(id)) {
                result.put(product[0], product[1] + '\n' + product[2]);
            }
        }
        return result;
    }
}
