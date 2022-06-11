package com.example.demo.recommendations;

import com.example.demo.entity.Police;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;

import java.util.*;

public class UserKnn {

    public static List<Product> getProductsByUserBasedKnn(User current, int k, List<User> allUsers) {
        SortedMap<Double, User> usersDistance = new TreeMap<>(Comparator.reverseOrder());
        allUsers.forEach(u -> {
            if (!u.getId().equals(current.getId())) {
                usersDistance.put(euclideanDistance(current, u), u);
            }
        });
        List<Police> similarPolicies = new ArrayList<>();
        for (int i = 0; i < k; ++i) {
            similarPolicies.addAll(usersDistance.get(usersDistance.firstKey()).getPolicies());
            usersDistance.remove(usersDistance.firstKey());
        }
        List<Product> result = new ArrayList<>();
        similarPolicies.forEach(p -> result.add(p.getProduct()));
        return result;
    }

    private static double euclideanDistance(User user, User otherUser) {
        double sum = 0;
        sum += (user.getAge() - otherUser.getAge()) * (user.getAge() - otherUser.getAge());
        sum += (user.getIncome().getId() - otherUser.getIncome().getId()) * (user.getIncome().getId() - otherUser.getIncome().getId());
        sum += (user.getGender().getId() - otherUser.getGender().getId()) * (user.getGender().getId() - otherUser.getGender().getId());
        sum += (user.getEducation().getId() - otherUser.getGender().getId()) * (user.getEducation().getId() - otherUser.getGender().getId());
        sum += (user.getActivitySphere().getId() - otherUser.getActivitySphere().getId()) * (user.getActivitySphere().getId() - otherUser.getActivitySphere().getId());
        return Math.sqrt(sum);
    }
}
