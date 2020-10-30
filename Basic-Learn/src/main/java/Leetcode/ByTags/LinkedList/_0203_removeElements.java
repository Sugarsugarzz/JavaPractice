package Leetcode.ByTags.LinkedList;

/**
 * 203. 移除链表元素
 * Easy
 */

public class _0203_removeElements {

    public static void main(String[] args) {

        ListNode head = new ListNode(6);
        head.next = new ListNode(6);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(6);

        ListNode res = removeElements(head, 6);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static ListNode removeElements(ListNode head, int val) {

        // 新建一个头结点的方法
//        ListNode p = head;
//        ListNode preHead = new ListNode(-1);
//        ListNode pre = preHead;
//        pre.next = head;
//
//        while (p != null) {
//            if (p.val == val) {
//                pre.next = p.next;
//                p = p.next;
//            } else {
//                p = p.next;
//                pre = pre.next;
//            }
//        }
//
//        return preHead.next;

        // 无需新建头结点
//        if (head == null)
//            return null;
//        // 先处理头部与val相同的结点
//        while (head.val == val) {
//            head = head.next;
//            if (head == null)
//                return null;
//        }
//        // 剔除与val相同的结点
//        ListNode cur = head, pre = head;
//        while (cur != null) {
//            if (cur.val == val) {
//                cur = cur.next;
//                pre.next = cur;
//            } else {
//                pre = cur;
//                cur = cur.next;
//            }
//        }
//        return head;

        // 不需要pre结点的写法
        // 先不管head，处理后面的
        if (head == null)
            return null;
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head.val == val ? head.next : head;
    }
}
