package com.qinxc.LeetCode;

/**
 * @author qxc
 * @date 2019/1/21.
 */
public class N8_String_to_Integer {

    public int myAtoi(String str) {

        int flag = 1;
        int result = 0;
        int start = 0;
        if (str.length() == 0)
            return 0;
        while (start < str.length() && str.charAt(start) == ' ') {
            start++;
        }

        if (start < str.length() && str.charAt(start) == '+') {
            start += 1;
        } else if (start < str.length() && str.charAt(start) == '-') {
            start += 1;
            flag = -1;
        }

        for (int i = start; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                int temp = c - '0';
                System.out.println(temp);
                if (result > Integer.MAX_VALUE / 10) {
                    if (flag == -1)
                        return Integer.MIN_VALUE;
                    else {
                        return Integer.MAX_VALUE;
                    }
                }

                if (result == Integer.MAX_VALUE / 10) {
                    if (flag == -1) {
                        if (temp > Integer.MAX_VALUE % 10 + 1) {
                            return Integer.MIN_VALUE;
                        }
                    } else {
                        if (temp > Integer.MAX_VALUE % 10) {
                            return Integer.MAX_VALUE;
                        }
                    }
                }
                result = result * 10;
                result += temp;

            } else {
                return result * flag;
            }
        }
        return result * flag;
    }
}
