package com.bytetube._15_greedy.bag;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class SelectBags {

    public static void main(String[] args) {
        select("weight led",(Article f1,Article f2)->f1.getWeight()-f2.getWeight());
        select("value led",(Article f1,Article f2)->f2.getValue()-f1.getValue());
        select("valueDensity led",(Article f1,Article f2)-> {
            return  Double.compare(f2.getValueDensity(),f1.getValueDensity());
        });
    }


    public static void select(String title, Comparator<Article> cmp) {
        Article[] articles = new Article[]{
                new Article(35, 10, 0.29),
                new Article(30, 40, 1.33),
                new Article(60, 30, 0.5),
                new Article(50, 50, 1),
                new Article(40, 35, 0.88),
                new Article(10, 40, 4),
                new Article(25, 30, 1.2)
        };

        Arrays.sort(articles, cmp);
        int capacity = 150;
        int weight = 0;
        int value = 0;

        List<Article> selectedArtices = new LinkedList<>();
        for (int i = 0; i < articles.length && weight < capacity; i++) {
            int newWeight = articles[i].getWeight() + weight;
            if (newWeight <= capacity) {
                weight = newWeight;
                value = articles[i].getValue() + value;
                selectedArtices.add(articles[i]);
            }

        }
        System.out.println("==========" + title + "==========");
        System.out.println("total value:" + value);
        for (int i = 0; i < selectedArtices.size(); i++) {
            System.out.println(selectedArtices.get(i));
        }
        System.out.println();

    }
}