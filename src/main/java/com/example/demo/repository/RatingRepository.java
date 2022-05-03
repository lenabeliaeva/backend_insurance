package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Product, Long> {

    @Query("select avg(r.price) from Rating r")
    double getAveragePriceRatingOfAllDb();

    @Query("select count (r.price) from Rating r")
    double getPriceRatingWeightOfAllDb();

    @Query("select avg(r.convenience) from Rating r")
    double getAverageConvenienceRatingOfAllDb();

    @Query("select count (r.convenience) from Rating r")
    double getConvenienceRatingWeightOfAllDb();

    @Query("select avg(r.impression) from Rating r")
    double getAverageImpressionRatingOfAllDb();

    @Query("select count (r.impression) from Rating r")
    double getImpressionRatingWeightOfAllDb();

    @Query("select avg(r.price) from Rating r where r.product.id = ?1")
    double getAveragePriceRatingOfProduct(Long productId);

    @Query("select count (r.price) from Rating r where r.product.id = ?1")
    double getPriceRatingWeightOfProduct(Long productId);

    @Query("select avg(r.convenience) from Rating r where r.product.id = ?1")
    double getAverageConvenienceRatingOfProduct(Long productId);

    @Query("select count (r.convenience) from Rating r where r.product.id = ?1")
    double getConvenienceRatingWeightOfProduct(Long productId);

    @Query("select avg(r.impression) from Rating r where r.product.id = ?1")
    double getAverageImpressionRatingOfProduct(Long productId);

    @Query("select count (r.impression) from Rating r where r.product.id = ?1")
    double getImpressionRatingWeightOfProduct(Long productId);
}
