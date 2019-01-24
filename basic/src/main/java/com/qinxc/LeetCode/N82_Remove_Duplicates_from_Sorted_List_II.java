package com.qinxc.LeetCode;


/**
 * @author qxc
 * @date 2019/1/24.
 */
public class N82_Remove_Duplicates_from_Sorted_List_II {

    public static void main(String[] args) {
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode first = head;
        ListNode pre = null;

        while (first != null) {
            ListNode temp = first;
            while (temp.next != null && first.val == temp.next.val) {
                temp = temp.next;
            }
            if (first != temp) {
                if (temp.next != null) {
                    first.val = temp.next.val;
                    first.next = temp.next.next;
                } else {
                    if (pre != null) {
                        pre.next = null;
                        return head;
                    } else {
                        return null;
                    }
                }
            } else {
                pre = first;
                first = first.next;
            }
        }
        return head;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
