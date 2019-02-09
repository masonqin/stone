package com.qinxc.LeetCode;

/**
 * @author qxc
 * @date 2019/1/30.
 */
public class N86_Partition_List {

    public ListNode partition(ListNode head, int x) {

        ListNode less = new ListNode(0);
        ListNode bigger = new ListNode(0);

        ListNode newHead = less;
        ListNode biggerHead = bigger;

        ListNode index = head;
        while (index != null) {
            if (index.val < x) {
                less.next = index;
                less = less.next;
            } else {
                bigger.next = index;
                bigger = bigger.next;
            }
            index = index.next;
        }
        bigger.next = null;
        //join
        less.next = biggerHead.next;

        return newHead.next;
    }

}
