package com.bytetube._15_greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class CostProfit {
    public static void main(String[] args) {
        int money = findBestProject(10, 350, new int[]{100, 200, 300, 400}, new int[]{1, 5, 3, 2});
        System.out.println(money);
    }


    private static class Element{
        private int c;
        private int p;

        public Element(int c, int p) {
            this.c = c;
            this.p = p;
        }


        public int getC() {
            return c;
        }

        public void setC(int c) {
            this.c = c;
        }

        public int getP() {
            return p;
        }

        public void setP(int p) {
            this.p = p;
        }
    }

    public static int findBestProject(int k,int m,int[] costs,int[] profits) {
        Element[] elements = new Element[profits.length];
        for (int i = 0; i < elements.length; i++) {
            elements[i] = new Element(costs[i],profits[i]);
        }
        //costs--->minheap
        PriorityQueue<Element> minCostQ = new PriorityQueue<>(new minCostComparator());
        //profits-->maxheap
        PriorityQueue<Element> maxProfitQ = new PriorityQueue<>(new maxProfitComparator());

        for (int i = 0; i < elements.length; i++) {
            minCostQ.add(elements[i]);
        }

        for (int i = 0; i < k; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().getC()<=m){
                maxProfitQ.add(minCostQ.poll());
            }

            if (maxProfitQ.isEmpty()) {
                return m;
            }

            m+=maxProfitQ.poll().getP();
        }

        return m;
    }

    private static class minCostComparator implements Comparator<Element> {

        @Override
        public int compare(Element e1, Element e2) {
            return e1.getC()-e2.getC();
        }
    }

    private static class maxProfitComparator implements Comparator<Element> {
        @Override
        public int compare(Element e1, Element e2) {
            return e2.getP()-e1.getP();
        }
    }
}
