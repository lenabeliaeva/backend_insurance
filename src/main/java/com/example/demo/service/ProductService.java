package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.recommendations.ContentBased;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final double THRESHOLD = 5.0;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PoliceRepository policeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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

    public double getRMSE() {
        List<Rating> allRatings = (List<Rating>) ratingRepository.findAll();
        double count = 0;
        double sum = 0;
        for (Rating allRating : allRatings) {
            count = count + 3;
            sum += allRating.getConvenience();
            sum += allRating.getImpression();
            sum += allRating.getPrice();
        }
        double avgRating = sum / count;

        double newSum = 0;
        for (int i = 0; i < count / 3; ++i) {
            newSum += (avgRating - allRatings.get(i).getConvenience()) * (avgRating - allRatings.get(i).getConvenience());
            newSum += (avgRating - allRatings.get(i).getImpression()) * (avgRating - allRatings.get(i).getImpression());
            newSum += (avgRating - allRatings.get(i).getPrice()) * (avgRating - allRatings.get(i).getPrice());
        }
        return Math.sqrt(newSum/count);
    }

    public double getCertainRMSE() {
        List<Rating> allRatings = (List<Rating>) ratingRepository.findAll();
        List<Rating> certainRatings = ratingRepository.findAllByProductId(1L);

        double count = 0;
        double sum = 0;
        for (Rating allRating : allRatings) {
            count = count + 3;
            sum += allRating.getConvenience();
            sum += allRating.getImpression();
            sum += allRating.getPrice();
        }
        double avgRating = sum / count;

        double newSum = 0;
        for (int i = 0; i < certainRatings.size(); ++i) {
            newSum += (avgRating - certainRatings.get(i).getConvenience()) * (avgRating - certainRatings.get(i).getConvenience());
            newSum += (avgRating - certainRatings.get(i).getImpression()) * (avgRating - certainRatings.get(i).getImpression());
            newSum += (avgRating - certainRatings.get(i).getPrice()) * (avgRating - certainRatings.get(i).getPrice());
        }
        return Math.sqrt(newSum/count);
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

    public Set<Product> getProductsByContentBased() {
        User someUser = userRepository.findById(39L).orElse(new User());
        List<Product> userProducts = someUser.getPolicies().stream().map(Police::getProduct).collect(Collectors.toList());
        return ContentBased.getByContentBased(userProducts, (List<Product>) productRepository.findAll(),
                (List<Category>) categoryRepository.findAll());
    }

    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

//    public List<Product> getProductsBySlopeOne() {
//        List<Product> allProducts = (List<Product>) productRepository.findAll();
//        User someUser = userRepository.findById(34L).orElse(new User());
//        SlopeOne.slopeOne(someUser, 3.6, allProducts);
//    }
}
