package com.example.demo.recommendations;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;

import java.util.*;

public class ItemKnn {

    private ItemKnn() throws IllegalAccessException {
        throw new IllegalAccessException("Instances are not needed");
    }

    public static List<Product> getProductsByItemBasedKnn(User currentUser, int k, List<Product> allProducts) {
        List<Product> currentUserProducts = new ArrayList<>();
        currentUser.getPolicies().forEach(p -> currentUserProducts.add(p.getProduct()));
        Product current = currentUserProducts.get(0);

        SortedMap<Double, Product> itemsDistance = new TreeMap<>(Comparator.reverseOrder());
        allProducts.forEach(p -> {
            if (!p.getId().equals(current.getId())) {
                double d = euclideanDistance(current, p);
                itemsDistance.put(d, p);
            }
        });

        List<Product> similarProducts = new ArrayList<>();
        for (int i = 0; i < k; ++i) {
            Product p = itemsDistance.get(itemsDistance.firstKey());
            if (!currentUserProducts.contains(p)) {
                similarProducts.add(itemsDistance.get(itemsDistance.firstKey()));
            }
            itemsDistance.remove(itemsDistance.firstKey());
        }
        return similarProducts;
    }

    private static double euclideanDistance(Product product, Product otherProduct) {
        double sum = 0;
        sum += (product.getCategory().getId() - otherProduct.getCategory().getId()) * (product.getCategory().getId() - otherProduct.getCategory().getId());
        for (int i = 0; i < product.getRatings().size(); ++i) {
            for (int j = 0; j < otherProduct.getRatings().size(); ++j) {
                sum += (product.getRatings().get(i).getPrice() - otherProduct.getRatings().get(j).getPrice()) * (product.getRatings().get(i).getPrice() - otherProduct.getRatings().get(j).getPrice());
            }
        }
        for (int i = 0; i < product.getRatings().size(); ++i) {
            for (int j = 0; j < otherProduct.getRatings().size(); ++j) {
                sum += (product.getRatings().get(i).getConvenience() - otherProduct.getRatings().get(j).getConvenience()) * (product.getRatings().get(i).getConvenience() - otherProduct.getRatings().get(j).getConvenience());
            }
        }
        for (int i = 0; i < product.getRatings().size(); ++i) {
            for (int j = 0; j < otherProduct.getRatings().size(); ++j) {
                sum += (product.getRatings().get(i).getImpression() - otherProduct.getRatings().get(j).getImpression()) * (product.getRatings().get(i).getImpression() - otherProduct.getRatings().get(j).getImpression());
            }
        }
        return Math.sqrt(sum);
    }
}
