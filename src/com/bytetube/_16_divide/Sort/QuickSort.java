package com.bytetube._16_divide.Sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {3,2,1,8,5,7,2};
        quickSort(arr);

    }

    public static void quickSort(int[] arr){
        quickSort(arr,0,arr.length-1);
    }

    public static void quickSort(int[] arr,int l,int r){//n*logn
        if (l < r) {
            swap(arr,l+(int) Math.random()*(r-l+1),r);//随机快排
            int[] partition = partition(arr,l,r);
            quickSort(arr,l,partition[0]-1);
            quickSort(arr,partition[1]+1,r);
        }
    }
    /**
     * @return 相同区间的左右边界（2个值）
     */
    public static int[] partition(int[] arr,int l,int r){
        int less = l-1;
        int more = r;
        while (l < more){
            if (arr[l] < arr[r]) {//arr[r] 是每次划分的基准
                swap(arr,++less,l++);
            }else if (arr[l] >  arr[r]){
                swap(arr,--more,l);
            }else {
                l++;
            }
        }
        swap(arr,more,r);//让大于区域的第一个元素和本次划分的基准交换，这样做，就把等于区域连在一起（让等于区域多了一个值）
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println("=======================");
        return new int[]{less+1,more};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j]  = temp;
    }

}
