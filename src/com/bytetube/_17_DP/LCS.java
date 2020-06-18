package com.bytetube._17_DP;
//[1, 3, 5, 9, 10] 和[1, 4, 9, 10]
public class LCS {
    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5, 9, 10};
        int[] nums2 = {1, 4, 9, 10};
        System.out.println(LCS4(nums1,nums2));
    }

    public static int LCS4(int[] nums1, int[] nums2){
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;

        int[] dp = new int[nums2.length+1];
        for (int i = 1; i <= nums1.length ; i++) {
           int cur = 0;
            for (int j = 1; j <= nums2.length; j++) {
                    int leftTop = cur;
                    cur = dp[j];
                if (nums1[i-1] == nums2[j-1]) {
                    dp[j] = leftTop+1;
                }else {
                   dp[j] = Math.max(dp[j],dp[j-1]);
                }
            }
        }
        return dp[nums2.length];
    }

    public static int LCS3(int[] nums1, int[] nums2){
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;

        int[][] dp = new int[2][nums2.length+1];
        for (int i = 1; i <= nums1.length ; i++) {
            int row = i%2;
            int prevRow = (i-1)%2;
            for (int j = 1; j <= nums2.length; j++) {

                if (nums1[i-1] == nums2[j-1]) {
                    dp[row][j] = dp[prevRow][j-1]+1;
                }else {
                    dp[row][j] = Math.max(dp[prevRow][j],dp[row][j-1]);
                }
            }
        }
        return dp[nums1.length%2][nums2.length];
    }

    public static int LCS2(int[] nums1, int[] nums2){
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;
        //dp[i][j] 是[nums1的前i个元素][nums2的前j个元素]
        int[][] dp = new int[nums1.length+1][nums2.length+1];
        for (int i = 1; i <= nums1.length ; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i-1] == nums2[j-1]) {
                    dp[i][j] = dp[i-1][j-1]+1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[nums1.length][nums2.length];
    }

    public static int LCS1(int[] nums1, int[] nums2){
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;

        return LCS(nums1,nums1.length,nums2,nums2.length);
    }

    private static int LCS(int[] nums1, int i, int[] nums2, int j) {
        if (i == 0 || j == 0) return 0;

        if (nums1[i-1] == nums2[j-1]) {
            return LCS(nums1,i-1,nums2,j-1)+1;
        }

       return Math.max(LCS(nums1,i-1,nums2,j),LCS(nums1,i,nums2,j-1));


    }
}
