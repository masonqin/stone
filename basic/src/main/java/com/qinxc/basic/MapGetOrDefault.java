package com.qinxc.basic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author qxc
 * @date 2018/11/29.
 */
public class MapGetOrDefault {
    public static void main(String[] args) {

        long bossId = 123l;
        long jobId = 456l;

        Map<Long, Set<Long>> map = new HashMap<>();
        Set<Long> jobSet = new HashSet<>();
        jobSet.add(jobId);
        map.put(bossId, jobSet);

        int bossIdInt = 123;
        Set<Long> temp = map.getOrDefault(bossIdInt, new HashSet<>());

        temp.add(2l);

        map.put((long) bossIdInt, temp);
        Set<Long> temp2 = map.getOrDefault(bossIdInt, new HashSet<>());

        System.out.println("final");

    }
}
