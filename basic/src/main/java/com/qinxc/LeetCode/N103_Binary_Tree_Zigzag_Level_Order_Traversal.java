package com.qinxc.LeetCode;

/**
 * @author qxc
 * @date 2019/2/27.
 */

import java.util.*;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its zigzag level order traversal as:
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * <p>
 * <p>
 * 解法1 两个栈 单层栈 双层栈 上一层一个方向遍历的时候同时将孩子压栈 避免了公用一个栈的时候 压入的孩子立即被弹出
 * <p>
 * 解法2 dfs 深搜时传入level 通过map保存每一层的list 深搜对于任意一层来说都是从左到右 判断层次选择尾插还是头插即可
 * <p>
 * 解法3 双端队列
 */

public class N103_Binary_Tree_Zigzag_Level_Order_Traversal {

    public List<List<Integer>> zigzagLevelOrder3(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        Deque<TreeNode> deque = new LinkedList<>();
        if (root != null)
            deque.push(root);
        int rightLeft = 1;
        int initSize = deque.size();
        List<Integer> subResult = new ArrayList<>();
        while (deque.size() != 0) {

            TreeNode temp;
            if (rightLeft == 0) {
                temp = deque.pollFirst();
                if (temp.left != null)
                    deque.addLast(temp.left);
                if (temp.right != null)
                    deque.addLast(temp.right);
            } else {
                temp = deque.pollLast();
                if (temp.right != null)
                    deque.addFirst(temp.right);
                if (temp.left != null)
                    deque.addFirst(temp.left);
            }
            subResult.add(temp.val);
            initSize--;
            if (initSize == 0) {
                rightLeft = (rightLeft + 1) % 2;
                initSize = deque.size();
                result.add(subResult);
                subResult = new ArrayList<>();
            }
        }

        return result;
    }

    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        int level = 0;
        if (root != null)
            stack1.push(root);
        List<Integer> list = new ArrayList<>();
        while (stack1.size() > 0 || stack2.size() > 0) {
            if (level % 2 == 0) {
                TreeNode temp = stack1.pop();
                if (temp.left != null)
                    stack2.push(temp.left);
                if (temp.right != null)
                    stack2.push(temp.right);
                list.add(temp.val);
                if (stack1.size() == 0) {
                    level++;
                    result.add(list);
                    list = new ArrayList<>();
                }
            } else {
                TreeNode temp = stack2.pop();
                if (temp.right != null)
                    stack1.push(temp.right);
                if (temp.left != null)
                    stack1.push(temp.left);
                list.add(temp.val);
                if (stack2.size() == 0) {
                    level++;
                    result.add(list);
                    list = new ArrayList<>();

                }
            }
        }
        return result;
    }
}
