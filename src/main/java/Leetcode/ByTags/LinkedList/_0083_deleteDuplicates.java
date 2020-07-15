package Leetcode.ByTags.LinkedList;

/**
 * 83. 删除排序链表中的重复元素
 * Easy
 */

public class _0083_deleteDuplicates {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);

        ListNode res = deleteDuplicates(head);

        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static ListNode deleteDuplicates(ListNode head) {

        // 不相同时，才将node = node.next
        ListNode node = head;

        while (node != null && node.next != null) {
            if (node.val == node.next.val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return head;
    }
}
