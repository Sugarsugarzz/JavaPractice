package Leetcode.ByTags.LinkedList;

import javax.xml.bind.SchemaOutputResolver;

/**
 * 61. 旋转链表
 * Medium
 */

public class _0061_rotateRight {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode res = rotateRight(head, 2);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static ListNode rotateRight(ListNode head, int k) {

        if (head == null || head.next == null)
            return head;
        // 先将链表连成环，
        ListNode cur = head;
        int len;
        for (len = 1; cur.next != null; len++)
            cur = cur.next;
        cur.next = head;
        // (n-k%n-1) 即为新链表的尾部，(n-k%n) 为新链表的首部
        int pos = len - k % len - 1;
        ListNode new_tail = head;
        for (int i = 0; i < pos; i++) {
            new_tail = new_tail.next;
        }
        ListNode new_head = new_tail.next;
        new_tail.next = null;

        return new_head;
    }
}
