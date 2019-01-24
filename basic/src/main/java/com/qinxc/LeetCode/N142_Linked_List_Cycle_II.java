package com.qinxc.LeetCode;

/**
 * @author qxc
 * @date 2019/1/23.
 */


public class N142_Linked_List_Cycle_II {


    public ListNode detectCycle(ListNode head) {

        if (head.next == null || head.next.next == null)
            return null;
        ListNode first = head.next;
        ListNode second = head.next.next;

        while (second != null && second != first) {
            if (second.next == null || second.next.next == null) {
                return null;
            }
            first = first.next;
            second = second.next.next;
        }
        second = head;

        while (second != first) {
            first = first.next;
            second = second.next;
        }
        return first;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}


