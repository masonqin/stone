package com.qinxc.LeetCode;

/**
 * @author qxc
 * @date 2019/2/21.
 */

import java.util.List;

/**
 * Reverse a linked list from position m to n. Do it in one-pass.
 * <p>
 * Note: 1 ≤ m ≤ n ≤ length of list.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 */

public class N92_Reverse_Linked_List_II {

    public ListNode reverseBetween(ListNode head, int m, int n) {

        ListNode preHead = new ListNode(0);
        preHead.next = head;
        ListNode pre = preHead;

        ListNode index1 = head;
        int mcnt = 1;
        int ncnt = 0;
        while (index1 != null) {

            if (mcnt < m) {
                pre = index1;
                index1 = index1.next;
                mcnt++;
                continue;
            }
            while (mcnt < n) {
                ListNode temp = index1.next;
                index1.next = temp.next;
                temp.next = pre.next;
                pre.next = temp;
                mcnt++;
            }
            break;
        }

        return preHead.next;
    }

}
