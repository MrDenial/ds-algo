package com.bytetube._05_queue;

public class Main {
    public static void main(String[] args) {
//        Queue<Integer> queue = new Queue<>();
//        for (int i = 0; i < 10; i++) {
//            queue.enQueue(i);
//        }
//
//        while (!queue.isEmpty()){
//            Integer deQueue = queue.deQueue();
//            System.out.print(deQueue+" ");
//        }

        DeQueue<Integer> deQueue = new DeQueue<>();
        for (int i = 0; i < 10; i++) {
            deQueue.enQueueFront(i);
        }

        while (!deQueue.isEmpty()){
            System.out.print(deQueue.deQueueFront()+" ");
        }
    }
}
