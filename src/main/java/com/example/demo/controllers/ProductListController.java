package com.example.demo.controllers;

import com.example.demo.models.EachCategoryProducts;
import com.example.demo.models.Product;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductListController {
    @RequestMapping("/products")
    public String getProductsList(String id) {
        List<Product> products = EachCategoryProducts.getProducts(id);
        return new Gson().toJson(products);
    }
}