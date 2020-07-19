package Leetcode.ByTags.LinkedList;

import java.util.ArrayList;
import java.util.List;

/**
 * 234. 回文链表
 * Easy
 */

public class _0234_isPalindrome {

    public static void main(String[] args) {

//        ListNode head = new ListNode(1);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(2);
//        head.next.next.next = new ListNode(3);
//        head.next.next.next.next = new ListNode(1);

        ListNode head = new ListNode(-129);
        head.next = new ListNode(-129);
        boolean res = isPalindrome(head);
        System.out.println(res);
    }

    public static boolean isPalindrome(ListNode head) {

        // 一、利用外部空间
//        List<Integer> list = new ArrayList<>();
//        while (head != null) {
//            list.add(head.val);
//            head = head.next;
//        }
//        int left = 0, right = list.size() - 1;
//        while (left <= right) {
//            if (list.get(left).equals(list.get(right))) {
//                left++;
//                right--;
//            } else {
//                return false;
//            }
//        }
//
//        return true;

        // 二、快慢指针(破坏了原链表结构，一般需要再翻转回来)
        // 快指针走到末尾，慢指针刚好到中间。慢指针将前半部分翻转，然后比较。
        if (head == null || head.next == null)
            return true;
        ListNode slow = head, fast = head;
        ListNode pre = head, prepre = null;
        // 慢指针边走边翻转
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
            pre.next = prepre;
            prepre = pre;
        }
        // 奇数个的情况，可以忽略中间那个数
        if (fast != null)
            slow = slow.next;
        // 比较
        while (pre != null && slow != null) {
            if (pre.val != slow.val)
                return false;
            pre = pre.next;
            slow = slow.next;
        }
        return true;
    }
}
