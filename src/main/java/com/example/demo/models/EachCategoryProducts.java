package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class EachCategoryProducts {
    private static List<Product> products;

    private static void setProducts(){
        products = new ArrayList<>();
        products.add(new Product(
                "11",
                "Опасные профессии",
                "Специальные условия для представителей опасных профессий",
                "1"));
        products.add(new Product(
                "12",
                "Классический полис",
                "Стандартный набор услуг",
                "1"));
        products.add(new Product(
                "21",
                "ОСАГО",
                "Обязательное страхование. Компенсация при нанесении вреда другим участникам дорожного движения",
                "2"));
        products.add(new Product(
                "22",
                "КАСКО",
                "Добровольное страхование. Компенсация на случай ущерба или угона",
                "2"));
        products.add(new Product(
                "31",
                "Страхование квартиры",
                "Страхование от взлома, кражи, пожара и других рисков",
                "3"));
        products.add(new Product(
                "32",
                "Страхование загородной недвижимости",
                "Страхование не только от типичных рисков, но и от стихийных бедствий",
                "3"));
    }

<<<<<<< HEAD
    public static List<String[]> getProducts(String id) {
        setProducts();
        List<String[]> result = new ArrayList<>();
        for (String[] product : products) {
            if (product[3].equals(id)) {
=======
    public static List<Product> getProducts(String id) {
        setProducts();
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategoryId().equals(id)) {
>>>>>>> parent of 1433cfc... refused from objects
                result.add(product);
            }
        }
        return result;
    }
}
