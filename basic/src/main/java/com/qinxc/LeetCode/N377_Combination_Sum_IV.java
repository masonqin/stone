package com.qinxc.LeetCode;

/**
 * @author qxc
 * @date 2019/1/31.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Example:
 * <p>
 * nums = [1, 2, 3]
 * target = 4
 * <p>
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * <p>
 * Note that different sequences are counted as different combinations.
 * <p>
 * Therefore the output is 7.
 */

public class N377_Combination_Sum_IV {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        int target = 35;
        long start = System.currentTimeMillis();
        int result = new N377_Combination_Sum_IV().combinationSum4(nums, target);
        long end = System.currentTimeMillis();

        System.out.println(result);
        System.out.println(("Time:" + (end - start) / 1000));

    }

    public int combinationSum4(int[] nums, int target) {

        int result = dfs(nums, target, new ArrayList<>(), 0);
//        int result = dp(nums, target);
        return result;
    }


    public int dp(int[] nums, int target) {

        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }

    public int dfs(int[] nums, int target, List<Integer> current, int currentSum) {

        if (currentSum == target) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (currentSum < target) {
                current.add(nums[i]);
                res += dfs(nums, target, current, currentSum + nums[i]);
                current.remove(current.size() - 1);
            }
        }
        return res;
    }
}
