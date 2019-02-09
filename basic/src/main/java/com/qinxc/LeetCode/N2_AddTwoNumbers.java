package com.qinxc.LeetCode;


/**
 * @author qxc
 * @date 2019/1/18.
 */
public class N2_AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode head = new ListNode(-1);
        ListNode node = head;

        int carry = 0;
        while (l1 != null && l2 != null) {
            int temp = l1.val + l2.val + carry;
            if (temp > 9) {
                temp = temp % 10;
                carry = 1;
            } else {
                carry = 0;
            }
            node.next = new ListNode(temp);
            node = node.next;
            l1 = l1.next;
            l2 = l2.next;

        }
        while (l1 != null) {
            int temp = l1.val + carry;
            if (temp > 9) {
                temp = temp % 10;
                carry = 1;
            } else {
                carry = 0;
            }
            node.next = new ListNode(temp);
            node = node.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            int temp = l2.val + carry;
            if (temp > 9) {
                temp = temp % 10;
                carry = 1;
            } else {
                carry = 0;
            }
            node.next = new ListNode(temp);
            node = node.next;
            l2 = l2.next;
        }
        if (carry == 1) {
            node.next = new ListNode(carry);
        }
        return head.next;
    }

}


