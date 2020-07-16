package Leetcode.ByTags.LinkedList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 160. 相交链表
 * Easy
 */

public class _0160_getIntersectionNode {

    public static void main(String[] args) {

        ListNode listA = new ListNode(9);
        ListNode listB = new ListNode(3);
        ListNode section = new ListNode(2);
        listA.next = new ListNode(1);
        listA.next = section;
        listB.next = section;
        section.next = new ListNode(4);

        ListNode res = getIntersectionNode(listA, listB);
        System.out.println(res.val);
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        // 巨暴力法
//        while (headA != null) {
//            ListNode nodeB = headB;
//            while (nodeB != null) {
//                if (nodeB == headA)
//                    return nodeB;
//                nodeB = nodeB.next;
//            }
//            headA = headA.next;
//        }
//        return null;

        // 哈希法
//        Set<ListNode> set = new HashSet<>();
//        while (headA != null) {
//            set.add(headA);
//            headA = headA.next;
//        }
//        while (headB != null) {
//            if (set.contains(headB))
//                return headB;
//            headB = headB.next;
//        }
//        return null;

        // 如果两人速度一致，走过的路程一致，肯定会在同一时间点到达终点
        // A:a+c  B:b+c  则 a+c+b+c = b+c+a+c
        if (headA == null || headB == null)
            return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}
