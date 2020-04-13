package com.bytetube._01_timeComplexity;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        //System.out.println(fib3(7));
        //printArray(randArray);
        //System.out.println();

//        int[] randArray = getRandArray(10, 100);
        int[] arr = {4,7,9,3};
        int max = getMax(arr,0,arr.length-1);
//        System.out.println(max);
    }

    public static int getMax(int[] arr,int left,int right){
        if (left == right) {
            return arr[left];
        }
        int mid = left+((right-left)>>1);
        int maxLeft = getMax(arr,left,mid);
        int maxRight = getMax(arr,mid+1,right);
        return Math.max(maxLeft,maxRight);
    }
    // System.out.println(fib1(45));//1134903170
    // System.out.println(fib2(64));//1134903170
//        int n = 45;
//        Times.test("fib1", new Times.Task() {
//            @Override
//            public void execute() {
//                System.out.println(fib1(n));
//            }
//        });
//
//        Times.test("fib2", new Times.Task() {
//            @Override
//            public void execute() {
//                System.out.println(fib2(n));
//            }
//        });
    public static void printArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            if (i < arr.length-1) {
                System.out.print(arr[i]+",");
            }else {
                System.out.print(arr[i]);
            }

        }
    }

    public static int[] getRandArray(int length,int bound){
        Random random = new Random();
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = random.nextInt(bound);
        }

        return arr;
    }

    //从一个array中找到最大值 int[] arr


    /**
     * fib num
     * 0 1 2 3 4 5 6 7
     * 0 1 1 2 3 5 8 13...
     *
     */
    public static int fib1(int n){
        if (n <= 1) {
            return  n;
        }
        return fib1(n-1)+fib1(n-2);
    }

    public static int fib2(int n){
        if (n <= 1) {
            return  n;
        }
        int first = 0;
        int second = 1;
        //2 + 1+2（n-1）+(n-1)*3 = 5n-3  //（n）
        for (int i = 0; i < n-1 ; i++) {
            int sum = first+second;
            first = second;
            second = sum;
        }
        return second;

    }

    public static int fib3(int n){
        double c= Math.sqrt(5);

         return (int)((Math.pow((c+1)/2,n)- Math.pow((1-c)/2,n))/c);//O(1)
    }


    public static void test1(int n){
        //1
        if (n >10) {
            System.out.println("n>10");
        }else  if (n >5) {
            System.out.println("n>5");
        }else {
            System.out.println("n<=5");
        }

        //1+4+4+4
        for (int i = 0; i < 4; i++) {
            System.out.println("test1");
        }

        //14 O(1)
    }

    public static void test2(int n){
        //1 +n+n +n(1+3n)=3n^2+3n+1  O(n^2)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("test2");
            }
        }
    }


    public static void test3(int n){
        /**
         * n = 8  ---3
         * n = 4 test3
         * n = 2 test3
         * n = 1 test3
         *
         * 16---4
         * 8 = 2^3
         * 16 = 2^4
         * exe time = logn
         *
         */
        while((n = n/2)>0){
            System.out.println("test3");
        }
    }

    public static void test4(int n){
        // 1 + 2log2(n)+log2(n)(1+3n)=3nlog2(n)+3log2(n)+1  //O(n*logn)
        for (int i = 1; i < n; i=i*2) {
            for (int j = 0; j < n; j++) {
                System.out.println("test4");
            }
        }
    }




}
