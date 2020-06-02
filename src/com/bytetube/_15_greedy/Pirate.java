package com.bytetube._15_greedy;

import java.util.Arrays;

//3、5、4、10、7、14、2、11
public class Pirate {
    public static void main(String[] args) {
        int[] elements = {3,5,4,10,7,14,2,11};
        int capacity = 30;
        int weight = 0;
        int count=0;
        Arrays.sort(elements);
        for (int i = 0; i < elements.length && weight<capacity; i++) {
            int newWeight = elements[i] + weight;
            if (newWeight <= capacity) {
                weight = newWeight;
                count++;

            }
        }

        System.out.println("there are "+ count+" selected");
    }
}
