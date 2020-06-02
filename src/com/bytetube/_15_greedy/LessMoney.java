package com.bytetube._15_greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LessMoney {
    private static class MinheapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1-o2;
        }
    }

    public static int lessMoney(int[] arr){
        PriorityQueue<Integer> pQ = new PriorityQueue<>(new MinheapComparator());
        //创建小根堆
        for (int i = 0; i < arr.length; i++) {
            pQ.add(arr[i]);
        }

        int sum=0;
        while (pQ.size()>1){
            int current = pQ.poll() + pQ.poll();
            sum+=current;
            pQ.add(current);
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {10,20,30};
        int money = lessMoney(arr);
        System.out.println(money);
    }


}
