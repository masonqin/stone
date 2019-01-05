package com.qinxc.LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author qxc
 * @date 2018/12/20.
 */
public class Redundant_Connection_684 {

    public static int[] Solotion(int[][] edges) {

        Map<Integer, Set<Integer>> nearMap = new HashMap<>();

        for (int[] edge : edges) {
            if (findCycle(edge[0], edge[1], nearMap, -1))
                return edge;
            Set<Integer> childs = nearMap.getOrDefault(edge[0], new HashSet<>());
            childs.add(edge[1]);
            nearMap.put(edge[0], childs);
            childs = nearMap.getOrDefault(edge[1], new HashSet<>());
            childs.add(edge[0]);
            nearMap.put(edge[1], childs);
        }
        return null;
    }

    public static boolean findCycle(int a, int b, Map<Integer, Set<Integer>> nearMap, int pre) {

        Set<Integer> childs = nearMap.get(a);
        if (childs != null) {
            if (childs.contains(b)) {
                return true;
            }
            for (int child : childs) {
                if (child != pre && findCycle(child, b, nearMap, a)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{{1, 2}, {1, 3}, {2, 3}};
        int[] ret = Solotion(edges);
        System.out.println(ret[0] + "  " + ret[1]);
    }

}
