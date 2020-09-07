package Leetcode.ByTags.LinkedList;

import java.util.*;

/**
 * 23. 合并K个升序链表
 * Difficult
 */

public class _0023_mergeKLists {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode[] lists = {l1, l2, l3};
        System.out.println(mergeKLists(lists).toString());

    }

    public static ListNode mergeKLists(ListNode[] lists) {

        // 一、暴力法
        // 先遍历链表存入一个列表中，排序后，构建新链表
//        List<Integer> nodes = new ArrayList<>();
//        for (ListNode head : lists) {
//            while (head != null) {
//                nodes.add(head.val);
//                head = head.next;
//            }
//        }
//        Collections.sort(nodes);
//
//        ListNode prev = new ListNode(-1);
//        ListNode head = prev;
//        for (Integer integer : nodes) {
//            ListNode node = new ListNode(integer);
//            head.next = node;
//            head = head.next;
//        }
//
//        return prev.next;

        // 二、顺序合并
        // TODO

        // 三、分治法
//        return merge(lists, 0, lists.length-1);

        // 四、优先队列
        if (lists == null || lists.length == 0)
            return null;

        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val < o2.val)
                    return -1;
                else if (o1.val == o2.val)
                    return 0;
                else
                    return 1;
            }
        });

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        for (ListNode node : lists) {
            if (node != null)
                queue.add(node);
        }

        while (!queue.isEmpty()) {
            prev.next = queue.poll();
            prev = prev.next;
            if (prev.next != null)
                queue.add(prev.next);
        }

        return dummy.next;
    }

    // 分治
    public static ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r)
            return lists[l];
        if (l > r)
            return null;
        int mid = (l + r) / 2;
        return mergeTwoList(merge(lists, l, mid), merge(lists, mid+1, r));
    }

    // 合并两个链表
    public static ListNode mergeTwoList(ListNode l1, ListNode l2) {

        ListNode preHead = new ListNode(-1);
        ListNode prev = preHead;
        while (l1 != null && l2 != null) {
            ListNode node = new ListNode();
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
        return preHead.next;
    }
}
