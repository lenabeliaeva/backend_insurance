package com.example.demo.recommendations;

import com.example.demo.entity.Product;
import com.example.demo.entity.Rating;
import com.example.demo.entity.User;

import java.util.*;

public class Hybrid {
    private Hybrid() throws IllegalAccessException {
        throw new IllegalAccessException("Instances are not needed");
    }

    private static final Map<Product, Map<Product, Double>> difference = new HashMap<>();
    private static final Map<Product, Map<Product, Integer>> frequency = new HashMap<>();
    private static Map<User, HashMap<Product, Double>> inputData;
    private static final Map<User, HashMap<Product, Double>> outputData = new HashMap<>();

    public static List<Product> getProductsByHybrid(User user, Double minR, List<Rating> allRatings) {
        inputData = toHashMap(allRatings);
        buildDifferencesMatrix(inputData);
        return predict(inputData, user, minR, 0.55, 0.45);
    }

    private static Map<User, HashMap<Product, Double>> toHashMap(List<Rating> allRatings) {
        Map<User, HashMap<Product, Double>> allElements = new HashMap<>();
        HashMap<Product, Double> productRatingsMap;
        for (Rating r : allRatings) {
            if (allElements.containsKey(r.getUser())) {
                allElements.get(r.getUser()).put(r.getProduct(), r.getProduct().getBar());
            } else {
                productRatingsMap = new HashMap<>();
                productRatingsMap.put(r.getProduct(), r.getProduct().getBar());
                allElements.put(r.getUser(), productRatingsMap);
            }
        }
        return allElements;
    }

    private static void buildDifferencesMatrix(Map<User, HashMap<Product, Double>> data) {
        for (HashMap<Product, Double> user : data.values()) {
            for (Map.Entry<Product, Double> e : user.entrySet()) {
                if (!difference.containsKey(e.getKey())) {
                    difference.put(e.getKey(), new HashMap<>());
                    frequency.put(e.getKey(), new HashMap<>());
                }
                for (Map.Entry<Product, Double> e2 : user.entrySet()) {
                    int oldCount = 0;
                    if (frequency.get(e.getKey()).containsKey(e2.getKey())) {
                        oldCount = frequency.get(e.getKey()).get(e2.getKey());
                    }
                    double oldDiff = 0.0;
                    if (difference.get(e.getKey()).containsKey(e2.getKey())) {
                        oldDiff = difference.get(e.getKey()).get(e2.getKey());
                    }
                    double observedDiff = e.getValue() - e2.getValue();
                    frequency.get(e.getKey()).put(e2.getKey(), oldCount + 1);
                    difference.get(e.getKey()).put(e2.getKey(), oldDiff + observedDiff);
                }
            }
        }
        for (Product j : difference.keySet()) {
            for (Product i : difference.get(j).keySet()) {
                double oldValue = difference.get(j).get(i);
                int count = frequency.get(j).get(i);
                difference.get(j).put(i, oldValue / count);
            }
        }
    }

    private static ArrayList<Product> predict(Map<User, HashMap<Product, Double>> data, User user, Double minRat, Double w1, Double w2) {
        HashMap<Product, Double> uPred = new HashMap<>();
        HashMap<Product, Integer> uFreq = new HashMap<>();
        ArrayList<Product> recommendation = new ArrayList<>();
        for (Product j : difference.keySet()) {
            uFreq.put(j, 0);
            uPred.put(j, 0.0);
        }
        for (Map.Entry<User, HashMap<Product, Double>> e : data.entrySet()) {
            for (Product j : e.getValue().keySet()) {
                for (Product k : difference.keySet()) {
                    try {
                        double predictedValue = difference.get(k).get(j) + e.getValue().get(j);
                        double finalValue = predictedValue * frequency.get(k).get(j);
                        uPred.put(k, uPred.get(k) + finalValue);
                        uFreq.put(k, uFreq.get(k) + frequency.get(k).get(j));
                    } catch (NullPointerException e1) {
                        e1.fillInStackTrace();
                    }
                }
            }
            HashMap<Product, Double> clean = new HashMap<>();
            for (Product j : uPred.keySet()) {
                if (uFreq.get(j) > 0) {
                    clean.put(j, uPred.get(j) / uFreq.get(j));
                }
            }
            for(Map.Entry<User, HashMap<Product, Double>> e2 :inputData.entrySet()){
                for (Product j : e.getValue().keySet()) {
                    if (e.getValue().containsKey(j)) {
                        double weightRat = e.getValue().get(j) * w1 + j.getBar() * w2;
                        clean.put(j, weightRat);
                    } else if (!clean.containsKey(j)) {
                        clean.put(j, -1.0);
                    }
                }
            }
            outputData.put(e.getKey(), clean);
        }
        List<Product> currentUserProducts = new ArrayList<>();
        user.getPolicies().forEach(p -> currentUserProducts.add(p.getProduct()));
        for(Map.Entry<User, HashMap<Product, Double>> map: outputData.entrySet()) { //create recommendation list
            if (map.getKey() == user) {
                for (Map.Entry<Product, Double> eSet : map.getValue().entrySet()) {
                    if (eSet.getValue() >= minRat && !currentUserProducts.contains(eSet.getKey()))
                        recommendation.add(eSet.getKey());
                }
            }
        }

        recommendation.sort(Comparator.comparing(Product::getBar).reversed());
        return recommendation;
    }
}