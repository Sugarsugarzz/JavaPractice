package Leetcode.ByTags.LinkedList;

/**
 * 206. 反转链表
 * Easy
 */

public class _206_reverseList {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode res = reverseList(head);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static ListNode reverseList(ListNode head) {

        // 使用外部空间的方法
//        ListNode newHead = new ListNode(-1);
//        // 头部插入新结点
//        while (head != null) {
//            ListNode node = new ListNode(head.val);
//            node.next = newHead.next;
//            newHead.next = node;
//            head = head.next;
//        }
//
//        return newHead.next;

        // 双指针迭代
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
