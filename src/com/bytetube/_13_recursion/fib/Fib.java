package com.bytetube._13_recursion.fib;

import com.bytetube._13_recursion.Times;

public class Fib {
    public static void main(String[] args) {
        Times.test("fib1", new Times.Task() {
            @Override
            public void execute() {
                System.out.println(fib1(45));
            }
        });

        Times.test("fib2", new Times.Task() {
            @Override
            public void execute() {
                System.out.println(fib2(45));
            }
        });

        Times.test("fib", new Times.Task() {
            @Override
            public void execute() {
                System.out.println(fib(45));
            }
        });
    }

    public static int fib(int n) {//6 5

        return process(n,1,1);
    }

    private static int process(int n, int first, int second) {
        if (n <=1) {
            return first;
        }

        return process(n-1,second,first+second);
    }


    public static int fib1(int n){//6 5
        if (n <=2 ) return 1;
            //fib(4)+fib(3)
        return fib1(n-1)+fib1(n-2);
    }




    public static int fib2(int n){
        if (n <=2 ) return 1;
        int[] arr = new int[n+1];//保存fib[n]的值  默认值是0
        arr[1] = 1;
        arr[2] = 1;
        return  fib2(n,arr);
    }

    private  static int fib2(int n, int[] arr){
        if (arr[n] == 0) {
            arr[n] = fib2(n-1,arr)+fib2(n-2,arr);
        }
        return arr[n];

    }
}
