package com.bytetube._17_DP;

public class LIS {

    public static void main(String[] args) {
        int[] arr = {10, 2, 2, 5, 1, 7, 101, 18};
        System.out.println(LIS2(arr));
    }


    public static int LIS2(int[] nums){
        if (nums == null || nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] <= nums[j]) continue;
                dp[i] = Math.max(dp[i],dp[j]+1);
            }
            max = Math.max(dp[i],max);
        }
        return max;
    }

    public static int LIS1(int [] arr) {
        int [] dp = new int[arr.length+1];
        dp[0] = 1;
        int res = Integer.MIN_VALUE;
        for (int i= 1; i<arr.length; i++) {
            int maxInThisLoop = 0;
            for (int j = i-1; j>=0; j--) {
                if (arr[j] < arr[i]) {
                    maxInThisLoop = Math.max(dp[j], maxInThisLoop);
                }
            }
            dp[i] = maxInThisLoop + 1;
            res = Math.max(dp[i], res);
        }

        return res;

    }
}
