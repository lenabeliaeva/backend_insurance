package com.example.demo.controllers;

import com.example.demo.entity.Product;
import com.example.demo.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(path = "/bayesAvgProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getTheBestProducts() {
        return ResponseEntity.ok(productService.getProductsByBayesAverage());
    }

    @GetMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping(path = "/products/category")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam(name = "id") long categoryId) {
        return ResponseEntity.ok(productService.getProductsByCategory(categoryId));
    }

    @GetMapping(path = "/contentBased")
    public ResponseEntity<Set<Product>> getProductsByContentBased() {
        return ResponseEntity.ok(productService.getProductsByContentBased());
    }
}
