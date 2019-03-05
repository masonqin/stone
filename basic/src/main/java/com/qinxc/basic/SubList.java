package com.qinxc.basic;

import com.qinxc.LeetCode.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author qxc
 * @date 2018/12/17.
 */
public class SubList {

    public static void main(String[] args) {


        HashMap<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> list = IntStream.range(0, 3000).boxed().collect(Collectors.toList());

        list.stream().filter(integer -> integer % 4 == 0).forEach(integer -> {
            int key = integer % 3;

            List<Integer> list1 = map.getOrDefault(key, new ArrayList<>());
            list1.add(integer);
            map.put(key, list1);
        });

        int start = 0;
        int end = 300;
        while (list.size() > start) {
            if (end >= list.size())
                end = list.size();
            System.out.println("start " + list.subList(start, end).get(0));
            System.out.println("end " + list.subList(start, end).get(list.subList(start, end).size() - 1));
            start = end;
            end = end + 300;
        }

    }

    TreeNode errNode;
    TreeNode swNode;

    private void midFirstSearch(TreeNode node, TreeNode preNode) {

        if (node.left != null) {
            midFirstSearch(node, node.left);
        }
        if (node.val < preNode.val) {
            if (errNode == null) {
                errNode = node;
            } else {
                swNode = node;
            }
        }
        if (node.right != null) {
            midFirstSearch(node.right, node);
        }
    }
}
