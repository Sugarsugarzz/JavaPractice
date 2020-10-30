package Leetcode.ByTags.LinkedList;

import java.util.HashSet;
import java.util.Set;

/**
 * 141. 环形链表
 * Easy
 */

public class _0141_hasCycle {

    public static void main(String[] args) {

        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);

        boolean res = hasCycle(head);
        System.out.println(res);
    }

    public static boolean hasCycle(ListNode head) {

        // 哈希解法，空间复杂度非O(1)
//        Set<ListNode> nodeSet = new HashSet<>();
//        while (head != null) {
//            if (nodeSet.contains(head))
//                return true;
//            else
//                nodeSet.add(head);
//            head = head.next;
//        }

        // 快慢指针 空间O(1)
        if (head == null)
            return false;
        ListNode fastNode = head;
        ListNode slowNode = head;
        while (fastNode != null) {
            if (fastNode.next != null && fastNode.next.next != null) {
                fastNode = fastNode.next.next;
                slowNode = slowNode.next;
            } else {
                return false;
            }
            if (fastNode == slowNode)
                return true;
        }

        return false;
    }
}
