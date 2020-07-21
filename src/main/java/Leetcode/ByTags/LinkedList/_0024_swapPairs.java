package Leetcode.ByTags.LinkedList;

/**
 * 24. 两两交换链表中的节点
 * Medium
 */

public class _0024_swapPairs {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        ListNode res = swapPairs(head);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static ListNode swapPairs(ListNode head) {

        // 递归写法
        if (head == null || head.next == null)
            return head;
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;

        // 非递归写法
        // 存在后两个元素就换，否则就不换
//        ListNode pre = new ListNode(-1);
//        pre.next = head;
//        ListNode temp = pre;
//        while (temp.next != null && temp.next.next != null) {
//            ListNode node_left = temp.next;
//            ListNode node_right = temp.next.next;
//            temp.next = node_right;
//            node_left.next = node_right.next;
//            node_right.next = node_left;
//            temp = node_left;
//        }
//        return pre.next;
    }
}
