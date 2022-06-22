package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.recommendations.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private CategoryRepository categoryRepository;

    @Autowired
    private PoliceRepository policeRepository;

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

    @Transactional
    public Set<Product> getProductsByContentBased(Long userId) {
        User someUser = userRepository.findById(userId).orElse(new User());
        List<Product> userProducts = someUser.getPolicies().stream().map(Police::getProduct).collect(Collectors.toList());
        return ContentBased.getByContentBased(userProducts, (List<Product>) productRepository.findAll(),
                (List<Category>) categoryRepository.findAll());
    }

    @Transactional
    public List<Product> getByUserKnn(Long userId) {
        User someUser = userRepository.findById(userId).orElse(new User());
        List<User> allUsers = (List<User>) userRepository.findAll();
        allUsers.remove(someUser);
        return UserKnn.getProductsByUserBasedKnn(someUser, 3, allUsers);
    }

    public void generate() {
        Police police;
        for (int i = 68; i < 88; ++i) {
            police = policeRepository.findById((long) i).orElse(new Police());
            List<Rating> p = ratingRepository.findAllByUserId(police.getUser().getId());
            if (p.size() == 1) {
                police.setProduct(p.get(0).getProduct());
                policeRepository.save(police);
            }
        }
    }

    @Transactional
    public List<Product> getByItemKnn(Long userId) {
        User someUser = userRepository.findById(userId).orElse(new User());
        List<Product> allProducts = (List<Product>) productRepository.findAll();
        return ItemKnn.getProductsByItemBasedKnn(someUser, 3, allProducts);
    }

    @Transactional
    public List<Product> getByHybrid(Long userId) {
        User someUser = userRepository.findById(userId).orElse(new User());
        List<Rating> allRatings = (List<Rating>) ratingRepository.findAll();
        return Hybrid.getProductsByHybrid(someUser, 3.9, allRatings);
    }

    @Transactional
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Transactional
    public double countRMSE() {
        List<Rating> allRatings = (List<Rating>) ratingRepository.findAll();

        List<Product> actualResult = getByHybrid(39L);
        List<Rating> certainRatings = new ArrayList<>();
        for (Product p: actualResult) {
            certainRatings.addAll(ratingRepository.findAllByProductId(p.getId()));
        }

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
}
