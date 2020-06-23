package com.bytetube._17_DP;

public class Knapsack {
    public static void main(String[] args) {
        int[] values = {6,3,5,4,6};
        int[] weights = {2,2,6,5,4};
        int capacity = 10;

        System.out.println(maxValue(values,weights,capacity));
        //假设dp(i, j) 是最大承重为j、有前i 件物品可选时的最大总价值
//        int[] values = {6,3,5};
//        int[] weights = {2,2,6};
//        int capacity = 7;

        //dp(3,7)指的是：最大承重是7，有前3件物品可以选时的最大价值

        /*

        dp(i, 0)、dp(0, j) 初始值均为0
        i = 1
        j = 5
        如果j<weights[i],dp(i,j) = dp(i-1,j)

        如果不选择第i个物品
        dp(i,j) = dp(i-1,j)
        如果选择第i个物品
        dp(i,j) = dp(i-1,j-weights[i])+value[i]

        dp(i,j) = max{dp(i-1,j),dp(i-1,j-weights[i])+value[i]}

         */

    }
    //      int[] values = {6,3,5,4,6};
    //      int[] weights = {2,2,6,5,7};
    //      int capacity = 10;

        public static int maxValue(int[] values,int[] weights,int capacity){
            if (values == null || weights == null) return 0;
            if (values.length == 0 || weights.length == 0) return 0;
            if (values.length != weights.length || capacity <= 0) return 0;

            int[][] dp = new int[values.length+1][capacity+1];
            for (int i = 1; i <= values.length ; i++) {
                for (int j = 1; j <= capacity; j++) {
                    if (j < weights[i-1]) {
                        dp[i][j] = dp[i-1][j];
                    }else {
                        dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-weights[i-1]]+values[i-1]);
                    }
                }
            }

            return dp[values.length][capacity];

        }


}
