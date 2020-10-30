package Leetcode.ByTags.LinkedList;

/**
 * 2. 两数相加
 * Medium
 */

public class _0002_addTwoNumbers {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode res = addTwoNumbers(l1, l2);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // 一、看做相同长度的链表进行遍历，在短链表前面补0。
        // 需要考虑上一位的进位问题，当前位计算结束后需要更新进位值。
        // 设一个头结点，返回头结点的next即可。
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;

            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;

            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }

        if (carry == 1) {
            cur.next = new ListNode(1);
        }

        return head.next;
    }
}
