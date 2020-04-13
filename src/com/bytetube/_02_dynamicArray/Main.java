package com.bytetube._02_dynamicArray;

public class Main {
    public static void main(String[] args) {
//        ArrayList<Object> arrayList = new ArrayList<>(20);
//        arrayList.add(0,10);
//        arrayList.add(1,20);
//        arrayList.add(2,30);
//        System.out.println(arrayList);

        ArrayList<String> list = new ArrayList<>();
        list.add("dal");
        list.add("f*ck");
        list.add("bugs");
        System.out.println(list);
        String remove = list.remove(1);
        System.out.println(remove);
        System.out.println(list);
        int index = list.indexOf("dal1");
        System.out.println(index);
        System.out.println(list.contains("bug"));


    }
}
