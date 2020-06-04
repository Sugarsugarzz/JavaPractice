package Leetcode.ByDifficulty.Easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * title: 相交链表
 * 
 * description：编写一个程序，找到两个单链表相交的起始节点。
 * 
 * limit:如果两个链表没有交点，返回 null.
		在返回结果后，两个链表仍须保持原有的结构。
		可假定整个链表结构中没有循环。
		程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 * 
 * label：链表
 * 
 */

public class _0160_getIntersectionNode {

	public static void main(String[] args) {
		// 测试
		ListNode a1 = new ListNode(0);
		ListNode a2 = new ListNode(9);
		ListNode a3 = new ListNode(1);

		ListNode b1 = new ListNode(3);

		ListNode c1 = new ListNode(2);
		ListNode c2 = new ListNode(4);

		a1.next = a2;
		a2.next = a3;
		a3.next = c1;
		b1.next = c1;
		c1.next = c2;

		ListNode result = getIntersectionNode_2(a1, b1);
		System.out.println(result.val);

	}

	// 暴力法
	public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

		while (headA != null) {
			ListNode nodeB = headB;
			while (nodeB != null) {
				if (nodeB == headA)
					return nodeB;
				nodeB = nodeB.next;
			}
			headA = headA.next;
		}
		return null;
	}

	// 哈希表法
	public static ListNode getIntersectionNode_2(ListNode headA, ListNode headB) {
		// 将链表A所有节点存入哈希表中
		Set<ListNode> aNodes = new HashSet<>();
		while (headA != null) {
			aNodes.add(headA);
			headA = headA.next;
		}
		// 检查链表B中节点是否在哈希表中
		while (headB != null) {
			if (aNodes.contains(headB))
				return headB;
			headB = headB.next;
		}

		return null;
	}

	// 巧解
	// 起点不一样，但是路程一样，速度一样，必定同时到达终点  A+B = B+A
	public static ListNode getIntersectionNode_3(ListNode headA, ListNode headB) {
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
