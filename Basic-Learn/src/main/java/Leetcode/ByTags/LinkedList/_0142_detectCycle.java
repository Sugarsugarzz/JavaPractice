package Leetcode.ByTags.LinkedList;

import java.util.HashSet;
import java.util.Set;

/**
 * 142. 环形链表 II
 * Medium
 */

public class _0142_detectCycle {
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode node = new ListNode(2);
        head.next = node;
        node.next = new ListNode(0);
        node.next.next = new ListNode(-4);
        node.next.next.next = node;

        System.out.println(detectCycle(head).val);
    }

    public static ListNode detectCycle(ListNode head) {

        // 一、利用额外空间
//        Set<ListNode> set = new HashSet<>();
//        while (head != null) {
//            if (set.contains(head))  return head;
//            set.add(head);
//            head = head.next;
//        }
//        return null;

        // 二、不利用额外空间，快慢指针
        ListNode fast = head, slow = head;
        while (true) {
            if (fast == null || fast.next == null)  return null;
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)  break;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
