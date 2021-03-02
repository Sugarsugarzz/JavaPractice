package Leetcode.AimOffer;

import Leetcode.ByTags.LinkedList.ListNode;

/**
 * 24. 反转链表
 * Easy
 */
public class _24_reverseList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        System.out.println(reverseList(head).val);
    }

    public static ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }
}
