package com.qinxc.basic;


import java.util.*;

/**
 * @author qxc
 * @date 2019/1/9.
 */
/*
递归型解法
main 函数中for循环发动起始递归，控制递归深度等条件

深搜方法
current     中包含了目前的step深度，也可以单独给出
remain      代表了剩余集合，也可以通过别的条件和总集合配合来实现
stop        表示终止深度，终止条件也可以通过remain的size来判别
step        表示当前深度
result      结果集

dfs(current,remain,int stop,int step,result){
   if(current is valid){
       //过滤、排序等特殊操作
       Collections.sort(current);
       //加入结果集
       result.add(new Collection(current));
   }
   clone = new ArrayList<>(remain);
   //遍历剩余集合
   for(i : clone){
       //走一步
       stepNode = clone.get(i);
       remain.remove(stepNode);
       current.add(stepNode);
       //带着刚走的一步，向下搜索
       dfs(current,remain,stop,step++,result);
       //搜索完了退回一步，下一轮循环走另外一步
       remain.add(stepNode);
       current.remove(stepNode);
   }
}


非递归回溯方法
走一步，将新步压栈，

dfs(int stop,wholeSet){

    Stack<> stack = new Stack<>();
    stack.push([]);
    while(!stack.empty()){
        temp = stack.pop();
        if(temp is valid){
            //排序、过滤等特殊操作
            Collections.sort(current);
            //加入结果集
            result.add(new Collection(current));
        }
        //去除temp中已有的元素，防止重复
        Collection remain = new Collection<>(wholeSet);
        for(i=0; i<temp.size(); i++){
            remain.remove(temp.get(i))
        }
        //走下一步，压栈
        for(i=0;i<remain.size();i++){
            //走下一步
            temp.add(remain.get(i));
            //压栈
            stack.push(new Collection(temp));
            //清除刚走的一步
            temp.remove(remain.get(i));
        }
    }
}


 */

public class StringSub {

    public static ArrayList<String> target = new ArrayList(Arrays.asList("a", "b", "c", "c"));
    public static Set<List<String>> resultSet = new HashSet<>();

    public static void main(String[] args) {
        List<String> remainSet = new LinkedList<>(target);
        for (int i = 0; i <= target.size(); i++) {
            List<String> current = new ArrayList<>();
            getSubStr(i, current, remainSet);
//            getSubStr2(i);
        }

        //只要长度为2的
//        List<String> current = new ArrayList<>();
//        getSubStr(2, current, remainSet);
        //只要长度为2的
//        getSubStr2(2);

        System.out.println(resultSet);
    }

    public static void getSubStr(int length, List<String> current, List<String> remainSet) {

        if (current.size() == length) {
            List<String> temp = new ArrayList<>(current);
            //组合就排序去重 排列不排序
            Collections.sort(temp);
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
            List<String> temp = stack.pop();
            if (temp.size() <= 3) {
                if (temp.size() == length) {
                    //组合就排序去重 排列不排序
                    Collections.sort(temp);
                    resultSet.add(temp);
                }
                //去除temp中已有的元素
                List<String> remain = new ArrayList<>(target);
                for (int i = 0; i < temp.size(); i++) {
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


    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            dfs(nums, i, result);
        }
        return result;
    }

    public void dfs(int[] nums, int length, List<List<Integer>> result) {

        Stack<List<Integer>> stack = new Stack<>();
        stack.push(new ArrayList<>());

        while (!stack.isEmpty()) {
            List<Integer> temp = stack.pop();
            if (temp.size() == length) {
                result.add(new ArrayList<>(temp));
            }

            Set<Integer> remainSet = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                remainSet.add(nums[i]);
            }
            for (int i = 0; i < temp.size(); i++) {
                remainSet.remove(temp.get(i));
            }

            for (Integer i : remainSet) {
                temp.add(i);
                stack.push(new ArrayList<>(temp));
                temp.remove(temp.size() - 1);
            }

        }
    }
}

