package com.bytetube._15_greedy;

import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) {
        //coinChange1();
        coinChange2(new Integer[]{25,5,10,1},41);
    }


    //lamda
    public static void coinChange2(Integer[] faces,int money){
        Arrays.sort(faces,(Integer f1,Integer f2)->f2-f1);////25,10,5,1  41-25 = 16
        int coins = 0;
        int index = 0;
        while (index < faces.length){
            if (money < faces[index]) {
                index++;
                continue;
            }
            System.out.println( faces[index]);
            money-= faces[index];
            coins++;
            //index=0;
        }

        System.out.println(coins);

    }

    public static void coinChange1(){
        int[] faces = {25,5,10,1};//1,5,10,25   41-25 = 16
        Arrays.sort(faces);
        int money = 41;
        int coins = 0;
        for (int i = faces.length-1; i >=0 ; i--) {

            if (money < faces[i]) {
                continue;
            }
            money-= faces[i];
            coins++;
            i = faces.length;//是个坑，因为23行结束马上会进行i--

        }

        System.out.println(coins);

    }
}
