package com.bytetube._17_DP;

/*
    ① 暴力递归（自顶向下，出现了重叠子问题）
    ② 记忆化搜索（自顶向下）
    ③ 递推（自底向上）

 */
public class ChangeCoins {

    public static void main(String[] args) {
        System.out.println(coins2(19));//25,20 ,5,1
    }

    //② 记忆化搜索（自顶向下）
    public static int coins2(int n){
        if (n < 1) {
            return -1;
        }
        int[] dp = new int[n+1];//凑够n分需要的硬币数 coins[16] = 4 ===>dp[16] = 4
        int[] faces = {1,5,20,25};
        for (int face : faces) {
            if (n < face) {
                break;
            }
            dp[face] = 1;
        }
        //dp[25] = dp[20] = dp[5] = dp[1] = 1;
        return coin2(n,dp);
    }

    private static int coin2(int n, int[] dp) {
        if (n < 1)  return Integer.MAX_VALUE;

        if (dp[n] == 0) {
            int min1 = Math.min( coins1(n-25),coins1(n-20));
            int min2 = Math.min( coins1(n-5),coins1(n-1));
            dp[n] = Math.min(min1,min2)+1;
        }

        return dp[n];
    }

    //暴力递归
    public static int coins1(int n){//n表示要找的零钱
        //一般情况，base case的写法:参数
        if (n < 1)  return Integer.MAX_VALUE;
        /**
         * n = 4
         * min2 = Math.min( coins1(-1),coins1(3));
         *
         */
        if (n == 25 || n == 20 || n == 5 || n == 1) return 1 ;

        int min1 = Math.min( coins1(n-25),coins1(n-20));
        int min2 = Math.min( coins1(n-5),coins1(n-1));

        return Math.min(min1,min2)+1;

    }

}
