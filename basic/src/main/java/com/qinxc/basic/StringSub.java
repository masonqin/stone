package com.qinxc.basic;


import java.util.*;

/**
 * @author qxc
 * @date 2019/1/9.
 */

public class StringSub {

    public static ArrayList<String> target = new ArrayList(Arrays.asList("a", "b", "c"));
    //要组合，不管顺序
//    public static Set<Set<String>> resultSet = new HashSet<>();
    //要排列，需要顺序
    public static Set<List<String>> resultSet = new HashSet<>();

    public static void main(String[] args) {
        Set<String> remainSet = new HashSet<>(target);
        for (int i = 0; i <= target.size(); i++) {
//            List<String> current = new ArrayList<>();
//            getSubStr(i, current, remainSet);
            getSubStr2(i);
        }

        //只要长度为2的
//        List<String> current = new ArrayList<>();
//        getSubStr(2, current, remainSet);
        //只要长度为2的
//        getSubStr2(2);

        System.out.println(resultSet);
    }

    public static void getSubStr(int length, List<String> current, Set<String> remainSet) {

        if (current.size() == length) {
            //1、要组合，不管顺序
//            Set<String> temp = new HashSet<>(current);
            //2、要排列，要顺序
            List<String> temp = new ArrayList<>(current);
            resultSet.add(temp);
            return;
        }

        List<String> remainList = new ArrayList<>(remainSet);

        for (int i = 0; i < remainList.size(); i++) {
            String removed = remainList.get(i);
            current.add(removed);
            remainSet.remove(removed);
            getSubStr(length, current, remainSet);
            remainSet.add(removed);
            current.remove(removed);
        }
    }

    public static void getSubStr2(int length) {

        //排列用list 组合用set去重
        Stack<List<String>> stack = new Stack<>();
        stack.push(new ArrayList<>());
        while (!stack.empty()) {
            //排列用list 组合用set去重
            List<String> temp = stack.pop();
            if (temp.size() <= 3) {
                if (temp.size() == length) {
                    resultSet.add(temp);
                }
                //去除temp中已有的元素
                List<String> remain = new ArrayList<>(target);
                for(int i=0;i<temp.size();i++) {
                    remain.remove(temp.get(i));
                }
                for (int i = 0; i < remain.size(); i++) {
                    temp.add(remain.get(i));
                    stack.push(new ArrayList<>(temp));
                    temp.remove(temp.size() - 1);
                }
            }
        }
    }


}

