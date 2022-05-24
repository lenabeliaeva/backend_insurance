package com.example.demo.service.impl;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final double THRESHOLD = 5.0;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Transactional
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    public List<Product> getProductsByCategory(long categoryId) {
        return productRepository.getProductsByCategoryId(categoryId);
    }

    @Transactional
    public List<Product> getProductsByBayesAverage() {
        List<Product> allProducts = (List<Product>) productRepository.findAll();

        allProducts.forEach(p -> p.setBayesAverageRating(calculateBayesAverage(p.getId())));

        allProducts.sort(Comparator.comparing(Product::getBayesAverageRating).reversed());

        return allProducts.size() < 4 ? allProducts : allProducts.stream().limit(3).collect(Collectors.toList());
    }

    private double calculateBayesAverage(Long productId) {
        double aPriceAll = ratingRepository.getAveragePriceRatingOfAllDb();
        double aConvAll = ratingRepository.getAverageConvenienceRatingOfAllDb();
        double aImprAll = ratingRepository.getAverageImpressionRatingOfAllDb();

        double wPrice = ratingRepository.getPriceRatingWeightOfProduct(productId);
        Float rPriceFloat = ratingRepository.getAveragePriceRatingOfProduct(productId);
        double rPrice = rPriceFloat == null ? 0 : rPriceFloat;

        double wConv = ratingRepository.getConvenienceRatingWeightOfProduct(productId);
        Float rConvFloat = ratingRepository.getAverageConvenienceRatingOfProduct(productId);
        double rConv = rConvFloat == null ? 0 : rConvFloat;

        double wImpr = ratingRepository.getImpressionRatingWeightOfProduct(productId);
        Float rImprFloat = ratingRepository.getAverageImpressionRatingOfProduct(productId);
        double rImpr = rImprFloat == null ? 0 : rImprFloat;

        double denominator = THRESHOLD * 3 + wPrice + wConv + wImpr;
        if (denominator != 0) {
            double numerator = THRESHOLD * (aPriceAll + aConvAll + aImprAll) + wPrice * rPrice + wConv *  rConv + wImpr + rImpr;
            return numerator / denominator;
        } else {
            return 0;
        }
    }
}
