package Leetcode.AimOffer;

import Leetcode.ByTags.LinkedList.ListNode;

/**
 * 22. 链表中倒数第k个节点
 * Easy
 */
public class _22_getKthFromEnd {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(5);
        head.next.next = new ListNode(3);
        System.out.println(getKthFromEnd(head, 2).val);
    }

    public static ListNode getKthFromEnd(ListNode head, int k) {
        ListNode pre = head, cur = head;
        while (k != 0) {
            cur = cur.next;
            k--;
        }
        while (cur != null) {
            pre = pre.next;
            cur = cur.next;
        }
        return pre;
    }
}
