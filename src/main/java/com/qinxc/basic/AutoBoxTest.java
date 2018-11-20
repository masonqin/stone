package com.qinxc.basic;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qxc
 * @date 2018/10/18.
 */
public class AutoBoxTest {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            map.put(i, i);
        }
        long id = 1l;
        System.out.println(map.get(id));
    }
}
