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
        double rPrice = ratingRepository.getAveragePriceRatingOfProduct(productId);

        double wConv = ratingRepository.getConvenienceRatingWeightOfProduct(productId);
        double rConv = ratingRepository.getAverageConvenienceRatingOfProduct(productId);

        double wImpr = ratingRepository.getImpressionRatingWeightOfProduct(productId);
        double rImpr = ratingRepository.getAverageImpressionRatingOfProduct(productId);

        double denominator = THRESHOLD * 3 + wPrice + wConv + wImpr;
        if (denominator != 0) {
            double numerator = THRESHOLD * (aPriceAll + aConvAll + aImprAll) + wPrice * rPrice + wConv *  rConv + wImpr + rImpr;
            return numerator / denominator;
        } else {
            return 0;
        }
    }
}
