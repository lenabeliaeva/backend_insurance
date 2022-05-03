package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class EachCategoryProducts {
    private static List<ProductMock> products;

    private static void setProducts() {
        products = new ArrayList<>();
        products.add(new ProductMock(
                11,
                "Опасные профессии",
                "Специальные условия для представителей опасных профессий",
                "1"));
        products.add(new ProductMock(
                12,
                "Классический полис",
                "Стандартный набор услуг",
                "1"));
        products.add(new ProductMock(
                21,
                "ОСАГО",
                "Обязательное страхование. Компенсация при нанесении вреда другим участникам дорожного движения",
                "2"));
        products.add(new ProductMock(
                22,
                "КАСКО",
                "Добровольное страхование. Компенсация на случай ущерба или угона",
                "2"));
        products.add(new ProductMock(
                31,
                "Страхование квартиры",
                "Страхование от взлома, кражи, пожара и других рисков",
                "3"));
        products.add(new ProductMock(
                32,
                "Страхование загородной недвижимости",
                "Страхование не только от типичных рисков, но и от стихийных бедствий",
                "3"));
    }

    public static List<ProductMock> getProducts(String id) {
        setProducts();
        List<ProductMock> result = new ArrayList<>();
        for (ProductMock product : products) {
            if (product.getCategoryId().equals(id)) {
                result.add(product);
            }
        }
        return result;
    }
}
