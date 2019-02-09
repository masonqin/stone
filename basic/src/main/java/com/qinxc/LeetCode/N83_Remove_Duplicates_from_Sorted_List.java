package com.qinxc.LeetCode;

/**
 * @author qxc
 * @date 2019/1/30.
 */
public class N83_Remove_Duplicates_from_Sorted_List {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return null;
        ListNode pre = head;
        ListNode index = head.next;
        while (index != null) {
            while (index != null && pre.val == index.val) {
                index = index.next;
            }
            pre.next = index;
            pre = pre.next;
            if (index != null)
                index = index.next;
        }
        return head;
    }
}
