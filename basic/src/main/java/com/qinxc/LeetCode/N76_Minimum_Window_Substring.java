package com.qinxc.LeetCode;

/**
 * @author qxc
 * @date 2019/1/22.
 */

import java.util.*;

/**
 * Example:
 * <p>
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 */


public class N76_Minimum_Window_Substring {

    public static void main(String[] args) {
        String result = new N76_Minimum_Window_Substring().minWindow("bbaa", "aba");
        System.out.println(result);
    }

    public String minWindow(String s, String t) {

        if (s == null || t == null || s.length() == 0 || t.length() == 0 || t.length() > s.length())
            return "";

        List<Integer> list = new ArrayList<>();
        int lastIndex = -1;
        int head = 0;
        String result = "";
        Map<Character, Integer> targetMap = new HashMap<>();
        Map<Character, Integer> currentMap = new HashMap<>();
        int currentCnt = 0;
        for (char c : t.toCharArray()) {
            int cnt = targetMap.getOrDefault(c, 0);
            cnt++;
            targetMap.put(c, cnt);
        }
        for (int i = 0; i < s.length(); i++) {
            if (targetMap.keySet().contains(s.charAt(i))) {
                if (lastIndex < 0) {
                    head = i;
                    lastIndex = 0;
                }
                list.add(i);
                int cnt = currentMap.getOrDefault(s.charAt(i), 0);
                cnt++;
                currentMap.put(s.charAt(i), cnt);
                if (cnt <= targetMap.get(s.charAt(i))) {
                    currentCnt++;
                }
            }

            while (currentCnt == t.length()) {
                if (result.length() == 0) {
                    result = s.substring(head, i + 1);
                } else if (result.length() > 0 && i + 1 - head <= result.length()) {
                    result = s.substring(head, i + 1);
                }
                int cnt = currentMap.get(s.charAt(head));
                if (cnt > targetMap.get(s.charAt(head))) {
                    cnt--;
                    currentMap.put(s.charAt(head), cnt);
                } else {
                    break;
                }
                head = list.get(++lastIndex);
            }

        }
        return result;
    }

}
