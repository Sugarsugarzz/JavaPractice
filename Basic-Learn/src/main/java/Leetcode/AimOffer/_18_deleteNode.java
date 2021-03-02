package Leetcode.AimOffer;

import Leetcode.ByTags.LinkedList.ListNode;

/**
 * 18. 删除链表的节点
 * Easy
 */
public class _18_deleteNode {
    public static void main(String[] args) {
        ListNode head = new ListNode(5);
        head.next = new ListNode(1);
        head.next = new ListNode(3);
        deleteNode(head, 5);
    }

    public static ListNode deleteNode(ListNode head, int val) {
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        ListNode curr = head, pre = preHead;
        while (curr != null) {
            if (curr.val == val) {
                pre.next = curr.next;
                curr.next = null;
                return preHead.next;
            }
            curr = curr.next;
            pre = pre.next;
        }
        return preHead.next;
    }
}
