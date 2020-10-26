package Leetcode.ByTags.LinkedList;

/**
 * 148. 排序链表
 * Medium
 */

public class _0148_sortList {
    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        ListNode node = sortList(head);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }

    }

    public static ListNode sortList(ListNode head) {

        // 一、获取链表值存在额外空间，重新构建链表
//        List<Integer> values = new ArrayList<>();
//        while (head != null) {
//            values.add(head.val);
//            head = head.next;
//        }
//        List<Integer> sorted = values.stream().sorted().collect(Collectors.toList());
//        ListNode newHead = new ListNode();
//        ListNode pre = newHead;
//        for (Integer value : sorted) {
//            ListNode node = new ListNode(value);
//            newHead.next = node;
//            newHead = newHead.next;
//        }
//        return pre.next;

        // 二、归并排序（递归法，空间复杂Ologn)
        if (head == null || head.next == null)
            return head;

        ListNode middleNode = getMiddleNode(head);
        ListNode rightHead = middleNode.next;
        middleNode.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);

        return mergeLinkedList(left, right);

        // 三、归并排序（迭代法）
    }

    // 递归法，快慢指针获取中间结点
    private static ListNode getMiddleNode(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 递归法，有序合并两链表
    private static ListNode mergeLinkedList(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        curr.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}
