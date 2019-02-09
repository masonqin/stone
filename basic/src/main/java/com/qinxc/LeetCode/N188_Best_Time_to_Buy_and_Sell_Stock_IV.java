package com.qinxc.LeetCode;

/**
 * @author qxc
 * @date 2019/2/1.
 */

/**
 * Example 1:
 * <p>
 * Input: [2,4,1], k = 2
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 * Example 2:
 * <p>
 * Input: [3,2,6,5,0,3], k = 2
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 * Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 */

/**
 * dp[i][j] 到j天 i次交易的最大收益
 * dp[i][j] =
 *          dp[i][j-1]
 *          for(jj = 0~j)
 *              dp[i-1][jj] + price[j]-price[jj]
 *
 *
 */


public class N188_Best_Time_to_Buy_and_Sell_Stock_IV {

    //todo
    public static void main(String[] args) {
        int num[] = new int[]{3, 2, 6, 5, 0, 3};
        int k = 2;





    }


}
