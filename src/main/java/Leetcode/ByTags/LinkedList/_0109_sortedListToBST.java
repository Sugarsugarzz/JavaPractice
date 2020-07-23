package Leetcode.ByTags.LinkedList;

import Leetcode.ByTags.Tree.TreeNode;

/**
 * 109. 有序链表转换二叉搜索树
 * Medium
 */

public class _0109_sortedListToBST {

    public static void main(String[] args) {

        ListNode head = new ListNode(-10);
        head.next = new ListNode(-3);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(9);

        TreeNode root = sortedListToBST(head);
        System.out.println(root.val);
    }

    public static TreeNode sortedListToBST(ListNode head) {
        // 快慢指针，思路同108题
        return helper(head, null);
    }

    private static TreeNode helper(ListNode head, ListNode tail) {
        if (head == tail)
            return null;
        ListNode slow = head, fast = head;
        // 慢指针指向中点，作为根节点
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
            System.out.println(slow.val + " - ");
        }
        // 递归，构造左右节点
        TreeNode root = new TreeNode(slow.val);
        root.left = helper(head, slow);
        root.right = helper(slow.next, tail);
        return root;
    }
}
