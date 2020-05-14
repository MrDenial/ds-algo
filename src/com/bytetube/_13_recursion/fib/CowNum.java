package com.bytetube._13_recursion.fib;

public class CowNum {
    public static void main(String[] args) {

    }

    public static int cowNum(int n){
        if (n <=4) {
            return n;
        }
        return cowNum(n-1)+cowNum(n-3);

    }
}
