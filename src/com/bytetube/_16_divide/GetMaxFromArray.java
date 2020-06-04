package com.bytetube._16_divide;

public class GetMaxFromArray {

    public static void main(String[] args) {
        int[] arr = {8,5,7,2,9,3};
        System.out.println(getMax(arr));
    }


    public static int getMax(int[] arr){

         return getMax(arr,0,arr.length-1);
    }

    private static int getMax(int[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }

        int mid = (left+right)>>1;
        int leftMax = getMax(arr, left, mid);
        int rightMax = getMax(arr, mid+1, right);

        return Math.max(leftMax,rightMax);
    }

}
