package com.bytetube._15_greedy;

import java.util.Arrays;
import java.util.Comparator;

public class lowLexicography {
    private static class lexicographyCompatator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return (s1+s2).compareTo(s2+s1);
        }
    }

    public static String lowestString(String[] strs){
        if (strs == null || strs.length==0) {
            return "";
        }
        Arrays.sort(strs,new lexicographyCompatator());
        String result = "";
        for (int i = 0; i < strs.length; i++) {
            result+= strs[i];
        }

        return result;
    }

    public static void main(String[] args) {
        String[] strs = {"ba","b"};
        String lowestString = lowestString(strs);
        System.out.println(lowestString);
    }


}
