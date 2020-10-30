package Leetcode.ByTags.LinkedList;

/**
 * 19. 删除链表中的倒数第N个节点
 * Medium
 */

public class _0019_removeNthFromEnd {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode res = removeNthFromEnd(head, 2);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {

        // 一、两趟扫描
        // 第一遍获取链表长度
//        ListNode cur = head;
//        int len = 0;
//        while (cur != null) {
//            len += 1;
//            cur = cur.next;
//        }
//        if (len == n)
//            return head.next;
          // 第二遍剔除第N个元素
//        int pos = len - n + 1;
//        ListNode pre = head;
//        cur = head;
//        for (int i = 1; i < pos; i++) {
//            pre = cur;
//            cur = cur.next;
//        }
//        pre.next = cur.next;
//        return head;

        // 二、一趟遍历
        // 两个指针，距离n。
        // 当后指针到达链表尾部时，前指针到达倒数第n个结点。
        ListNode pre = new ListNode(-1);
        pre.next = head;
        ListNode fast = pre, slow = pre;
        while (n != 0) {
            fast = fast.next;
            n--;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return pre.next;
    }
}
