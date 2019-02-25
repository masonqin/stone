package com.qinxc.LeetCode;

/**
 * @author qxc
 * @date 2019/2/20.
 */
public class N26_Remove_Duplicates_from_Sorted_Array {

    public int removeDuplicates(int[] nums) {

        if (nums.length <= 1)
            return nums.length;

        int firstIndex = 0, secondIndex = 1, current = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[secondIndex] == current) {
                secondIndex++;
                continue;
            }
            nums[++firstIndex] = nums[secondIndex++];
            current = nums[firstIndex];
        }
        return ++firstIndex;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2};
        int end = new N26_Remove_Duplicates_from_Sorted_Array().removeDuplicates(nums);
        for (int i = 0; i < end; i++) {
            System.out.println(nums[i]);
        }
    }
}
