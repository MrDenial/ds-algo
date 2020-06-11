package com.bytetube._16_divide.Sort;

public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {10,3,6,4,8,1};
        selectionSort(arr);
    }


    public static void selectionSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr,i,minIndex);

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
