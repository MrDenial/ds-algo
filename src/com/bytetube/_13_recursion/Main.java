package com.bytetube._13_recursion;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        log1(4);
        System.out.println("===============");
        log2(4);
    }


    static void log1(int n){
        if (n < 1)  return;



        log1(n-1);
        int v = n+10;
        System.out.println(v);
    }

    static class Frame{
        int n;
        int v;

        public Frame(int n, int v) {
            this.n = n;
            this.v = v;
        }

        public int getV() {
            return v;
        }
    }

    static void log2(int n){
        Stack<Frame> frames = new Stack<>();
        while (n>0){//系统压栈
            frames.push(new Frame(n,n+10));
            n--;
        }

        while (!frames.isEmpty()){
            Frame frame = frames.pop();
            System.out.println(frame.getV());
        }

    }

}
