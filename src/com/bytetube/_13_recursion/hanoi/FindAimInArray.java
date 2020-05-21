package com.bytetube._13_recursion.hanoi;

public class FindAimInArray {
    public static void main(String[] args) {
            int[] arr = {3,2,7,13};
        System.out.println(findAimInArray(arr,6));
    }



    public static boolean findAimInArray(int[] arr,int aim){
        return process(arr,0,0,aim);
    }

    private static boolean process(int[] arr, int i, int sum, int aim) {
        if (sum == aim) {
            return true;
        }

        if (i == arr.length) {
            return false;
        }

        return  process(arr,i+1,sum+arr[i],aim)|| process(arr,i+1,sum,aim);

    }


}
