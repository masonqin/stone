package com.qinxc.LeetCode;

import java.util.Stack;

/**
 * @author qxc
 * @date 2019/2/27.
 */
public class N232_Implement_Queue_using_Stacks {

}

class MyQueue {

    Stack<Integer> input = new Stack<>();
    Stack<Integer> output = new Stack<>();

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {

    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        input.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        peek();
        return output.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (output.size() > 0) {
            return output.peek();
        } else {
            if (input.size() > 0) {
                while (input.size() > 0) {
                    output.push(input.pop());
                }
            }
        }
        return output.peek();
    }


    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return input.empty() && output.empty();
    }
}
