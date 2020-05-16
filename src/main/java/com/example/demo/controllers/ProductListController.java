package com.example.demo.controllers;

import com.example.demo.models.EachCategoryProducts;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductListController {
    @PostMapping("/products")
    public String getProductsList(String id){
        return new Gson().toJson(EachCategoryProducts.getProducts(id));
    }
}
