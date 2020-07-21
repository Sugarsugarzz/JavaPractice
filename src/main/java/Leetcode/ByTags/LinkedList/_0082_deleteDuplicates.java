package Leetcode.ByTags.LinkedList;

/**
 * 82. 删除排序链表中的重复元素 II
 * Medium
 */

public class _0082_deleteDuplicates {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);

        ListNode res = deleteDuplicates(head);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static ListNode deleteDuplicates(ListNode head) {

        ListNode pre = new ListNode(-1);
        pre.next = head;
        ListNode p1 = pre, p2 = head;
        boolean flag = false;
        while (p1.next != null) {
            while (p2.next != null && p2.next.val == p1.next.val) {
                p2 = p2.next;
                flag = true;  // 有重复节点为true
            }
            if (flag) {
                p1.next = p2.next; // 删除重复节点
                p2 = p2.next;
                flag = false;
            } else {
                p1 = p2;
                if (p2.next != null)
                    p2 = p2.next;
            }
        }

        return pre.next;
    }
}
