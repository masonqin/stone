package com.qinxc.LeetCode;

/**
 * @author qxc
 * @date 2019/1/22.
 */

/**
 * Example:
 * <p>
 * Input: s = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 */
public class N209_Minimum_Size_Subarray_Sum {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        int s = 7;
        int result = new N209_Minimum_Size_Subarray_Sum().minSubArrayLen(s, nums);
        System.out.println(result);
    }

    public int minSubArrayLen(int s, int[] nums) {

        int headIndex = 0;
        int sum = 0;
        int windownSize = nums.length + 1;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= s) {
                if (i - headIndex + 1 <= windownSize) {
                    windownSize = i - headIndex + 1;
                }
                sum -= nums[headIndex];
                headIndex++;
            }
        }
        if (windownSize > nums.length)
            return 0;
        return windownSize;
    }
}
