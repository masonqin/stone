package com.qinxc.basic;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by qxc on 2018/6/29.
 */
public class MapIteratorTest {

    private static Map<String, String> map = new HashMap<String, String>();

    ArrayList<String> testList = new ArrayList<>();


    public static void main(String[] args) throws Exception {

        Stream.of("a", "b", "c").forEach(str -> {
            throw new RuntimeException("");
        });

        //ini
        for (int i = 0; i < 10; i++) {
            map.put(new Integer(i).toString(), "value" + i);
        }

        Set<String> keyset = new HashSet<>(map.keySet());

        for (String key : keyset) {
            System.out.println(key);
            int keynum = Integer.parseInt(key);
            if (keynum % 2 == 0) {
                System.out.println("To delete key " + key);
                map.remove(key);
                System.out.println("The key " + key + " was deleted");
            }
        }

        System.out.println("map size = " + map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
