package com.bytetube._13_recursion.hanoi;

public class printSubString {
    public static void main(String[] args) {
        printSubString("abc",0,"");
    }


    public static void printSubString(String s,int i,String res){
        if (i == s.length()) {
            System.out.println(res);
            return;
        }

        printSubString(s,i+1,res);
        printSubString(s,i+1,res+s.charAt(i));

    }
}
