package com.bytetube._16_divide;

public class MaxSubArray {
    public static void main(String[] args) {

        System.out.println(maxSubArray3(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
    }

    public static int maxSubArray3(int[] nums) {//O(n^2)
        if (nums == null || nums.length==0) return 0;


        return maxSubArray3(nums,0,nums.length);

    }

    private static int maxSubArray3(int[] nums, int begin, int end) {
        if (end-begin <2) {
            return nums[begin];
        }
        int mid = (begin+end)>>1;
        int leftMax = Integer.MIN_VALUE;
        int leftSum = 0;
        for (int i = mid-1; i >= begin ; i--) {
            leftSum += nums[i];
            leftMax = Math.max(leftMax,leftSum);
        }

        int rightMax = Integer.MIN_VALUE;
        int rightSum = 0;
        for (int i = mid; i < end ; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightMax,rightSum);
        }

        int yellowSum = leftMax + rightMax;
        int purpleMax = maxSubArray3(nums,begin,mid);
        int blueMax = maxSubArray3(nums,mid,end);
        return Math.max(yellowSum,
                Math.max(purpleMax,blueMax));

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
