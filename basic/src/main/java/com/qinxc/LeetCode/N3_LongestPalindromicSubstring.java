package com.qinxc.LeetCode;

/**
 * @author qxc
 * @date 2019/1/18.
 */
public class N3_LongestPalindromicSubstring {

    public static void main(String[] args) {
        String str1 = longestPalindrome("abcdef");
        System.out.println(str1);
        String str2 = longestPalindrome("abcdcb");
        System.out.println(str2);
    }

    public static String longestPalindrome(String s) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            sb.append("#");
            sb.append(s.charAt(i));
        }
        sb.append("#");
        int max = 0;
        int maxIndex = 0;
        String newStr = sb.toString();
        int[] p = new int[newStr.length()];
        for (int i = 0; i < newStr.length(); i++) {
            p[i] = 0;
            while (i - p[i] >= 0 && i + p[i] < newStr.length()) {
                if (newStr.charAt(i - p[i]) == newStr.charAt((i + p[i]))) {
                    p[i]++;
                    if (p[i] > max) {
                        max = p[i];
                        maxIndex = i;
                    }
                } else
                    break;
            }
        }

        String result = newStr.substring(maxIndex - (p[maxIndex]-1) , maxIndex + (p[maxIndex]-1) );
        return result.replaceAll("#", "");
    }


}
