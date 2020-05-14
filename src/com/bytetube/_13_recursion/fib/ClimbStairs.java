package com.bytetube._13_recursion.fib;

public class ClimbStairs {
    public static void main(String[] args) {

    }


    public static int climbStairs(int n){
        if (n <=2 ) return n;
        //fib(4)+fib(3)
        return climbStairs(n-1)+climbStairs(n-2);

    }
}
