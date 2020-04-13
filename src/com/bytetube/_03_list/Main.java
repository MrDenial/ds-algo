package com.bytetube._03_list;


import com.bytetube._03_list.circle.CircleLinkedList;

public class Main {
    public static void main(String[] args) {
        josephus();
//      List<Integer> list = new LinkedList<>();
//      int n = 10;
//        for (int i = 1; i <= n; i++) {
//            list.add(i);
//        }
//        System.out.println(list);
////        list.remove(3);
////        list.remove(4);
////
////        System.out.println(list);
//        System.out.println(list.indexOf(1));


    }

    public static void josephus(){
        CircleLinkedList<Integer> list = new CircleLinkedList<>();
        for (int i = 1; i < 9; i++) {
            list.add(i);
        }
        list.reset();
        int count = list.size;
        while (count>0){
            for (int i = 0; i < 2; i++) {
                list.next();
            }
            Integer removed = list.remove();
            System.out.println(removed);
            count--;
        }
    }
}
