package com.bytetube._16_divide.Sort;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {10,3,6,4,8,1};
        insertSort(arr);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j]  = temp;
    }

    public static void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            for (int j = i-1; j >= 0 && arr[j] > arr[j+1] ; j--) {
                swap(arr,j,j+1);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
