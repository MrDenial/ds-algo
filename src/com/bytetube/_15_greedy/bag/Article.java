package com.bytetube._15_greedy.bag;

public class Article {
    private int weight;
    private int value;
    private double valueDensity;


    public Article(int weight, int value, double valueDensity) {
        this.weight = weight;
        this.value = value;
        this.valueDensity = valueDensity;
    }


    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public double getValueDensity() {
        return valueDensity;
    }

    public void setValueDensity(double valueDensity) {
        this.valueDensity = valueDensity;
    }

    @Override
    public String toString() {
        return "Article{" +
                "weight=" + weight +
                ", value=" + value +
                '}';
    }
}
