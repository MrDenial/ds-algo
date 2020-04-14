package com.bytetube._0_leetcode.Set;

import java.util.Arrays;
import java.util.HashSet;

public class _349_IntersectionTwoArrays {
    public static void main(String[] args) {
        int[] num1 = {4,9,5};
        int[] num2 = {9,4,9,8,4};
        int[] intersection = intersection(num1, num2);
        printArray(intersection);
    }

    public static void printArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }


    public static int[] intersection(int[] nums1, int[] nums2) {
        //1.array---set
        HashSet<Integer> set1 = new HashSet<>();
        for (Integer num : nums1) {
            set1.add(num);
        }
        HashSet<Integer> set2 = new HashSet<>();
        for (Integer num : nums2) {
            set2.add(num);
        }
        int size = set1.size()<set2.size()?set1.size():set2.size();
        int[] res = new int[size];
        int index = 0;

        if (set1.size()<set2.size()) {
            for (Integer num: set1) {
                if (set2.contains(num)) {
                    res[index++] = num;
                }
            }
            return res;
        }else {

            for (Integer num: set2) {
                if (set1.contains(num)) {
                    res[index++] = num;
                }
            }
            return Arrays.copyOf(res,index);

        }



    }
}
