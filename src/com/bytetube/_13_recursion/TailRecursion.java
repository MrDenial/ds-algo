package com.bytetube._13_recursion;

public class TailRecursion {
    public static void main(String[] args) {
        System.out.println(factorial1(4));
        System.out.println(factorial2(4));
    }

    void test1(){//
        int a = 10;
        int b = 10+a;
        test2(b);
        int c = a+b;

    }

    private void test2(int b) {
        int x1 = 20;
        int x2 = 30;
    }


   static int factorial1(int n){
        if (n <=1) return n;
        return n*factorial1(n-1);

    }

    static int  factorial2(int n){

       return process(n,1);
    }

    private static int process(int n, int res) {
        if (n <=1) return res;
       return process(n-1,res*n);
                //递归调用次数   累积结果
    }


}
