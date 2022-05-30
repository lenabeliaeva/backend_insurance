package com.example.demo.recommendations;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

import java.util.*;

public class ContentBased {

    private ContentBased() throws IllegalAccessException {
        throw new IllegalAccessException("Instances are not needed");
    }

    public static Set<Product> getByContentBased(List<Product> userProducts, List<Product> allProducts,
                                                  List<Category> allCategories) {
        Map<Product, List<Integer>> vectors = getAllProductsVectors(allProducts, allCategories);
        Map<Product, List<Double>> similarityMatrix = buildSimilarityMatrix(vectors);
        Set<Product> result = new HashSet<>();
        similarityMatrix.forEach((product, similarities) -> {
            if (userProducts.contains(product)) {
                for (int i = 0; i < similarities.size(); ++i) {
                    if (similarities.get(i) > 0) {
                        Product current = allProducts.get(i);
                        if (!userProducts.contains(current)) {
                            result.add(allProducts.get(i));
                        }
                    }
                }
            }
        });
        return result;
    }

    private static Map<Product, List<Integer>> getAllProductsVectors(List<Product> allProducts, List<Category> allCategories) {
        Map<Product, List<Integer>> result = new HashMap<>();
        for (Product product : allProducts) {
            List<Integer> categoriesVector = new ArrayList<>();
            result.put(product, categoriesVector);
            for (Category category : allCategories) {
                if (product.getCategory().equals(category)) {
                    categoriesVector.add(1);
                } else {
                    categoriesVector.add(0);
                }
            }
        }
        return result;
    }

    private static Map<Product, List<Double>> buildSimilarityMatrix(Map<Product, List<Integer>> vectors) {
        Map<Product, List<Double>> result = new HashMap<>();
        for (Map.Entry<Product, List<Integer>> entryA: vectors.entrySet()) {
            List<Double> similarities = new ArrayList<>();
            result.put(entryA.getKey(), similarities);
            for (Map.Entry<Product, List<Integer>> entryB: vectors.entrySet()) {
                similarities.add(cosineSimilarity(entryA.getValue(), entryB.getValue()));
            }
        }
        return result;
    }

    private static double cosineSimilarity(List<Integer> vectorA, List<Integer> vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vectorA.size(); i++) {
            dotProduct += vectorA.get(i) * vectorB.get(i);
            normA += vectorA.get(i) * vectorA.get(i);
            normB += vectorB.get(i) * vectorB.get(i);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}
