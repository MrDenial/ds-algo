package com.bytetube._16_divide;

public class Classify {
    public static void main(String[] args) {
        int[] arr = {3,6,2,1,5,7,4,8,0};
        process(arr,0,arr.length-1,5);
    }
    public static void process(int[] arr,int l,int r,int num){
        int less = -1;
        int more = r+1;
        while (l < more){
            if (arr[l] < num ) {
                swap(arr,++less,l++);
            }else if (arr[l] > num){
                swap(arr,--more,l);
            }else {
                l++;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }

    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j]  = temp;
    }
}
