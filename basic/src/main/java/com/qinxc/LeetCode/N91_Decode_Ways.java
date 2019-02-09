package com.qinxc.LeetCode;

/**
 * @author qxc
 * @date 2019/2/7.
 */


/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 * <p>
 * Example 1:
 * <p>
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 * <p>
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */

public class N91_Decode_Ways {


    public static void main(String[] args) {
        int result = new N91_Decode_Ways().numDecodings("101");
        System.out.println(result);
    }

    public int numDecodings(String s) {

        if (s.length() == 0)
            return 0;
        int dp[] = new int[s.length()];
        if (s.charAt(0) == '0') return 0;
        dp[0] = 1;

        for (int i = 1; i < s.length(); i++) {

            int ten = s.charAt(i - 1) - '0';
            int one = s.charAt(i) - '0';
            int num = ten * 10 + one;

            if (num == 0) return 0;

            if (ten == 0) {
                dp[i] = dp[i - 1];
                continue;
            }

            if (one == 0) {
                if (ten >= 3)
                    return 0;
                else {
                    if (i >= 2)
                        dp[i] = dp[i - 2];
                    else
                        dp[i] = dp[0];
                    continue;
                }
            }

            if (num <= 26) {
                if (i > 2) {
                    dp[i] = dp[i - 1] + dp[i - 2];
                } else {
                    dp[i] = dp[i - 1] + 1;
                }
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[s.length() - 1];
    }


}
