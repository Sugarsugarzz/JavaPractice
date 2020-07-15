package Leetcode.ByTags.LinkedList;

/**
 * 21. 合并两个有序链表
 * Easy
 */

public class _0021_mergeTwoLists {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode res = mergeTwoLists(l1, l2);

        while (res != null) {
            System.out.print(res.val + ", ");
            res = res.next;
        }

    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        // 递归
//        if (l1 == null)
//            return l2;
//        if (l2 == null)
//            return l1;
//        if (l1.val < l2.val) {
//            l1.next = mergeTwoLists(l1.next, l2);
//            return l1;
//        } else {
//            l2.next = mergeTwoLists(l1, l2.next);
//            return l2;
//        }

        // 构建新链表
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }
}
