package Leetcode.ByTags.LinkedList;

/**
 * 86. 分隔链表
 * Medium
 */

public class _0086_partition {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);

        ListNode res = partition(head, 3);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static ListNode partition(ListNode head, int x) {

        // 一、四指针（自己写的，不需要额外空间）
        // 先遍历找到比x大的数，然后停止。
        // 继续遍历该数后面的数，遇到比这个数小的，都插到这个数前。
//        ListNode pre = new ListNode(-1);
//        pre.next = head;
//        ListNode p1 = pre, p2 = head;
//
//        while (p2 != null) {
//            if (p2.val >= x) {
//                ListNode prep3 = p2;
//                ListNode p3 = p2.next;
//                while (p3 != null) {
//                    if (p3.val < x) {
//                        prep3.next = p3.next;
//                        ListNode node = p3;
//                        p3 = p3.next;
//                        p1.next = node;
//                        node.next = p2;
//                        p1 = p1.next;
//                    } else {
//                        p3 = p3.next;
//                        prep3 = prep3.next;
//                    }
//                }
//                break;
//            }
//            p2 = p2.next;
//            p1 = p1.next;
//        }
//        return pre.next;

        // 二、双指针法，创建两个链表，然后将两个链表连接即可获得所需的链表

        // 创建两链表的哑结点
        ListNode before_head = new ListNode(-1);
        ListNode before = before_head;
        ListNode after_head = new ListNode(-1);
        ListNode after = after_head;

        while (head != null) {
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }

        after.next = null;
        before.next = after_head.next;
        return before_head.next;
    }
}
