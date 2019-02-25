package com.qinxc.LeetCode;

/**
 * @author qxc
 * @date 2019/2/13.
 */

/**
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 * <p>
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 * <p>
 * Example 1:
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * Output: 5
 * Explanation:
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 * Note:
 * The length of the given array is positive and will not exceed 20.
 * The sum of elements in the given array will not exceed 1000.
 * Your output answer is guaranteed to be fitted in a 32-bit integer.
 */

public class N494_Target_Sum {

    //todo
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 1, 1};
        int result = new N494_Target_Sum().findTargetSumWays(nums, 3);
        System.out.println(result);
    }

    public int findTargetSumWays(int[] nums, int S) {
        int[] dp = new int[2*S + 1];
        dp[0] = 1;
        for (int i = 1; i <= S; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i - nums[j] >= 0) {
                    dp[i] += dp[i - nums[j]] + dp[i + nums[j]];
                }
            }
        }
        return dp[S];
    }
}
