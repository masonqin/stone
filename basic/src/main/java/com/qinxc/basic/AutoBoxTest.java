package com.qinxc.basic;

import scala.Int;

import java.util.*;

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


        Map<Integer, List<Integer>> testMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            List<Integer> list = testMap.computeIfAbsent(1, v -> new ArrayList<>());
            list.add(i + 10);
        }
        System.out.println(testMap.get(9));


        List<Integer> fromList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            fromList.add(i);
        }
        Integer[] array = fromList.toArray(new Integer[fromList.size()]);
        List<Integer> toList = Arrays.asList(array);
        toList.add(1);
    }
}
