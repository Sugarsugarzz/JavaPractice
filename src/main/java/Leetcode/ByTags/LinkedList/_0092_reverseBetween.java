package Leetcode.ByTags.LinkedList;

import lombok.NoArgsConstructor;

/**
 * 92. 反转链表 II
 * Medium
 */

public class _0092_reverseBetween {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode res = reverseBetween(head, 2, 4);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {

        // 一、自己的方法（未通过测试用例，还需要调试）
        // 先找到m和n对应的结点，对这段范围内的结点实行链表反转
//        ListNode pre = new ListNode(-1);
//        pre.next = head;
//
//        ListNode m_node = null, n_node = null, cur = head;
//        for (int i = 1; cur != null; cur = cur.next, i++) {
//            if (i == m)
//                m_node = pre;
//            if (i == n)
//                n_node = cur.next;
//            pre = pre.next;
//        }
//        pre = n_node;
//        cur = m_node.next;
//        while (cur != n_node) {
//            ListNode tmp = cur.next;
//            cur.next = pre;
//            pre = cur;
//            cur = tmp;
//        }
//        m_node.next = pre;
//        return head;

        // 二、头插法
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy, cur = head;
        for (int i = 1; i < m; i++) {
            pre = pre.next;
            cur = cur.next;
        }
        for (int i = 0; i < n - m; i++) {
            // pre和cur指向的结点固定，将cur.next移出链表，插入pre.next即可。
            ListNode removed_node = cur.next;
            cur.next = cur.next.next;
            removed_node.next = pre.next;
            pre.next = removed_node;
        }

        return dummy.next;
    }
}
