package com.example.demo.recommendations;

import com.example.demo.entity.Police;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;

import java.util.*;

public class SlopeOne {
    private static final Map<Product, Map<Product, Double>> difference = new HashMap<>();
    private static final Map<Product, Map<Product, Integer>> frequency = new HashMap<>();
    private static Map<User, HashMap<Product, Double>> inputData;
    private static final Map<User, HashMap<Product, Double>> outputData = new HashMap<>();

    public static List<Product> slopeOne(User user, Double minRating, List<Police> rec) {
//        inputData = toHashMap(rec);
//        printData(inputData);
        long start = System.currentTimeMillis();
        buildDifferencesMatrix(inputData);
        List<Product> result = predict(inputData, user, minRating, 0.55, 0.45);
        long finish = System.currentTimeMillis();
        long resultCalc = finish - start;
        System.out.println("result time = " + resultCalc);
        System.out.println("Prediction: " + result);
        return result;
    }

//    private static Map<User, HashMap<Product, Double>> toHashMap(List<Product> rec){
//        Map<User, HashMap<Product, Double>> allElements = new HashMap<>();
//        HashMap<Product, Double> map2;
//        for (int i = 0; i < rec.size(); i++){
//            map2 = new HashMap<>();
//            map2.put(rec.get(i), (double) rec.get(i).getRatings().get(0).getConvenience());
//            if(i+1 < rec.size() && rec.get(i + 1).getUser().equals(rec.get(i).getUser()))
//                for(Police police: rec) {
//                    if (police.getUser().equals(rec.get(i).getUser()))
//                        map2.put(police.getProduct(), (double)police.getProduct().getRatings().get(0).getConvenience());
//                }
//            if(!allElements.containsKey(rec.get(i).getUser()))
//                allElements.put(rec.get(i).getUser(), map2);
//        }
//        return allElements;
//    }

    /**
     * Нахождение матрицы отклонений
     * @param data существующая матрица оценок
     */
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

    /**
     * На основании имеющихся данных прогнозируют все недостающие рейтинги.
     * Если предсказание не возможно, значение будет равно -1
     * @param data существующая матрица оценок
     */
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
                        double weightRat = e.getValue().get(j) * w1 + j.getRatings().get(0).getConvenience() * w2;
                        clean.put(j, weightRat);
                    } else if (!clean.containsKey(j)) {
                        clean.put(j, -1.0);
                    }
                }
            }
            outputData.put(e.getKey(), clean);
        }
        for(Map.Entry<User, HashMap<Product, Double>> map: outputData.entrySet()) { //create recommendation list
            if (map.getKey() == user) {
                for (Map.Entry<Product, Double> eSet : map.getValue().entrySet()) {
                    if (eSet.getValue() >= minRat)
                        recommendation.add(eSet.getKey());
                }
                System.out.println("res = " + user.getPolicies());
                for (Police res : user.getPolicies()) { //delete own policies from recommendation list
                    recommendation.remove(res.getProduct());
//                    if(res.getRating() > 0)
//                        recommendation.remove(res.getProduct());
                }
            }
        }
        recommendation.sort(Comparator.comparingInt(o -> o.getRatings().get(0).getConvenience()));
        return recommendation;
    }

    static double calculateRmse(double[] pred, double[] real) {
        double sum = 0;
        double average;
        double rmse;

        int n = pred.length;
        for (int i = 0; i < n; i++) {
            sum += Math.pow((pred[i] - real[i]), 2);
        }
        average = sum / n;
        rmse = Math.sqrt(average);
        return rmse;
    }
}
