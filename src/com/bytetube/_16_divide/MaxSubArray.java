package com.bytetube._16_divide;

public class MaxSubArray {
    public static void main(String[] args) {

        System.out.println(maxSubArray2(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
    }



    public static int maxSubArray2(int[] nums) {//O(n^2)
        if (nums == null || nums.length==0) return 0;
        int max = Integer.MIN_VALUE;

        for (int begin = 0; begin < nums.length; begin++) {
            int sum = 0;//sum的结果是上一次[begin，end]的和
            for (int end = begin; end <nums.length ; end++) {
                sum = sum + nums[end];
                max = Math.max(max,sum);
            }

        }
        return max;

    }

    public static int maxSubArray1(int[] nums) {//O(n^3)
        if (nums == null || nums.length==0) return 0;
        int max = Integer.MIN_VALUE;
        for (int begin = 0; begin < nums.length; begin++) {
            for (int end = begin; end <nums.length ; end++) {
                int sum = 0;
                for (int i = begin; i <=end ; i++) {
                    sum += nums[i];
                }
                max = Math.max(max,sum);
            }
            
        }
        return max;

    }

}
