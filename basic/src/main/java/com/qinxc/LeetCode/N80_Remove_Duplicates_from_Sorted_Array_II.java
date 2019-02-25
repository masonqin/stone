package com.qinxc.LeetCode;


/**
 * @author qxc
 * @date 2019/1/15.
 */
public class N80_Remove_Duplicates_from_Sorted_Array_II {

    public int removeDuplicates(int[] nums) {

        if (nums.length <= 2)
            return nums.length;

        int firstIndex = 0, secondIndex = 1;
        int currentCnt = 0;
        int current = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[secondIndex] == current) {
                secondIndex++;
                currentCnt++;
                continue;
            }
            if (currentCnt > 0) {
                nums[++firstIndex] = current;
            }
            nums[++firstIndex] = nums[secondIndex++];
            current = nums[firstIndex];
            currentCnt = 0;
        }

        if (currentCnt > 0) {
            nums[++firstIndex] = nums[secondIndex - 1];
        }

        return firstIndex + 1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 1, 1, 1, 1, 1, 2, 2, 3};
        int end = new N80_Remove_Duplicates_from_Sorted_Array_II().removeDuplicates(nums);
        for (int i = 0; i < end; i++) {
            System.out.println(nums[i]);
        }
    }
}
