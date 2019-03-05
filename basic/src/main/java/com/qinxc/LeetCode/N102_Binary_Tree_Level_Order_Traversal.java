package com.qinxc.LeetCode;

/**
 * @author qxc
 * @date 2019/2/27.
 */


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its level order traversal as:
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 */

public class N102_Binary_Tree_Level_Order_Traversal {

    public List<List<Integer>> levelOrder(TreeNode root) {


        List<List<Integer>> result = new ArrayList<>();

        LinkedList<TreeNode> list = new LinkedList<>();
        if (root != null)
            list.add(root);

        int initSize = list.size();
        List<Integer> subResult = new ArrayList<>();
        while (list.size() != 0) {

            TreeNode temp = list.pop();
            if (temp.left != null)
                list.add(temp.left);
            if (temp.right != null)
                list.add(temp.right);
            subResult.add(temp.val);
            initSize--;
            if (initSize == 0) {
                initSize = list.size();
                result.add(subResult);
                subResult = new ArrayList<>();
            }
        }

        return result;
    }
}
