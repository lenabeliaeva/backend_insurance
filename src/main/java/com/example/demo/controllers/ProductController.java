package com.example.demo.controllers;

import com.example.demo.entity.Product;
import com.example.demo.entity.Rating;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Set<Product>> getProductsByContentBased(@RequestParam(name = "id") Long userId) {
        return ResponseEntity.ok(productService.getProductsByContentBased(userId));
    }

    @GetMapping(path = "/userKnn")
    public ResponseEntity<List<Product>> getByUserKnn(@RequestParam(name = "id") Long userId) {
        return ResponseEntity.ok(productService.getByUserKnn(userId));
    }

    @GetMapping(path = "/itemKnn")
    public ResponseEntity<List<Product>> getByItemKnn(@RequestParam(name = "id") Long userId) {
        return ResponseEntity.ok(productService.getByItemKnn(userId));
    }

    @GetMapping(path = "/hybrid")
    public ResponseEntity<List<Product>> getByHybrid(@RequestParam(name = "id") Long userId) {
        return ResponseEntity.ok(productService.getByHybrid(userId));
    }

    @PostMapping("/saveRating")
    public ResponseEntity<Rating> saveRatings(@RequestBody Rating rating) {
        return ResponseEntity.ok(productService.save(rating));
    }

    @GetMapping(path = "/cnt")
    public ResponseEntity<Double> countRMSE() {
        return ResponseEntity.ok(productService.countRMSE());
    }

    @GetMapping(path = "/gen")
    public void gen() {
        productService.generate();
    }
}
