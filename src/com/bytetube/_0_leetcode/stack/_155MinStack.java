package com.bytetube._0_leetcode.stack;

import java.util.Stack;

public class _155MinStack {
    private Stack<Integer>  stackData;
    private Stack<Integer>  stackMin;

    public _155MinStack() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(int x) {
        this.stackData.push(x);
        if (this.stackMin.isEmpty()) {
            this.stackMin.push(x);
        }else if (x<= this.stackMin.peek()) {
            this.stackMin.push(x);
        }
    }

    public void pop() {
        if (stackData.isEmpty()) {
            throw new RuntimeException("stackData is empty!");
        }

        int value = stackData.pop();
        if (value ==stackMin.peek() ) {
                stackMin.pop();
        }

    }

    public int top() {
        return stackData.peek();
    }

    public int getMin() {
        if (stackMin.isEmpty()) {
            throw new RuntimeException("stackMin is empty!");
        }
        return stackMin.peek();
    }
}
