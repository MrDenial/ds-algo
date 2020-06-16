package com.bytetube._17_DP;

/*
    ① 暴力递归（自顶向下，出现了重叠子问题）
    ② 记忆化搜索（自顶向下）
    ③ 递推（自底向上）

 */
public class ChangeCoins {

    public static void main(String[] args) {
        System.out.println(coins4(19, new int[]{25, 20, 5, 1}));//25,20 ,5,1

    }


    public static int coins4(int n,int[] faces){
        if (n < 1|| faces == null || faces.length == 0)  return -1;
        int[] dp = new int[n+1];//凑够n分需要的硬币数

        for (int i = 1; i <= n; i++) {
            //dp[i]=min { dp(i – 25), dp(i – 20), dp(i – 5), dp(i – 1) } + 1
            int min = Integer.MAX_VALUE;
            for (int face : faces) {
                if (i < face) continue;
                min = Math.min(dp[i-face],min);
            }
            dp[i] = min + 1;
        }
        return dp[n];
    }

    /**
     * 递推（自底向上）
     * 先计算出较小的值，再依次算出较大值
     * @param n
     * @return
     */
    public static int coins3(int n){
        if (n < 1)  return -1;
        int[] dp = new int[n+1];//凑够n分需要的硬币数

        for (int i = 1; i <= n; i++) {
            //dp[i]=min { dp(i – 25), dp(i – 20), dp(i – 5), dp(i – 1) } + 1
            int min = dp[i-1];

           // if (i >= 1)  min = Math.min(dp[i-1],min);
            if (i >= 5)  min = Math.min(dp[i-5],min);
            if (i >= 20)  min = Math.min(dp[i-20],min);
            if (i >= 25)  min = Math.min(dp[i-25],min);
            dp[i] = min + 1;
        }

        return dp[n];
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
            int min1 = Math.min( coin2(n-25,dp),coin2(n-20,dp));
            int min2 = Math.min( coin2(n-5,dp),coin2(n-1,dp));
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
