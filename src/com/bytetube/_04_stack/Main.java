package com.bytetube._04_stack;

public class Main {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 4; i++) {
            stack.push(i);
        }

        //System.out.println(stack.size());
        System.out.println("================");
//        for (int i = 0; i < stack.size(); i++) {
//            System.out.println(stack.pop());
//        }
            while (!stack.isEmpty()){
                System.out.println(stack.pop());
            }
    }

}
