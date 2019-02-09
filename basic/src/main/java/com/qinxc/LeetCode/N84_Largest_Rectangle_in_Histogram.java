package com.qinxc.LeetCode;

/**
 * @author qxc
 * @date 2019/1/29.
 */

/**
 * Example:
 * Input: [2,1,5,6,2,3]
 * Output: 10
 */

public class N84_Largest_Rectangle_in_Histogram {

    public static void main(String[] args) {

        int[] input = new int[]{2, 1, 5, 6, 2, 3};
        int maxArea = largestRectangleArea(input);
        System.out.println(maxArea);

    }

    public static int largestRectangleArea(int[] heights) {

        if (heights.length == 0) {
            return 0;
        }

        int maxArea = 0;
        for (int start = 0; start < heights.length; start++) {
            int minHeight = heights[start];
            for (int end = start; end < heights.length; end++) {
                if (heights[end] < minHeight)
                    minHeight = heights[end];

                int tempArea = (end - start + 1) * minHeight;
                if (tempArea > maxArea) {
                    maxArea = tempArea;
                }
            }
        }
        return maxArea;
    }
}
