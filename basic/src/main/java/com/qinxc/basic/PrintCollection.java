package com.qinxc.basic;

import org.w3c.dom.stylesheets.LinkStyle;

import java.util.*;

/**
 * @author qxc
 * @date 2018/11/23.
 */
public class PrintCollection {

    public static void main(String[] args) {

        Set<String> set = new HashSet<String>() {{
            add("1");
            add("2");
            add("3");
            add("4");
        }};

        System.out.println(set);

        Map<String, Integer> map = new HashMap<String, Integer>() {{
            put("1", 1);
            put("2", 2);
            put("3", 3);
            put("4", 4);
        }};

        System.out.println(map);

        List<String> list = new ArrayList<>(Arrays.asList("1", "2"));

        System.out.println(list);

        int[] src = new int[]{1, 2, 3, 4, 5};
        int b1[] = Arrays.copyOfRange(src, 1, 5);
        System.out.println(String.valueOf(b1[0])+String.valueOf(b1[1]));
        System.out.println(b1.length);
    }

}
