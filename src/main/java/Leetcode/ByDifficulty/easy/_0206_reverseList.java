package Leetcode.ByDifficulty.easy;

import java.util.Stack;

/**
 * 
 * title: 反转链表
 * 
 * description：反转一个单链表。
 * 
 * limit:
 * 
 * label：链表
 * 
 */

public class _0206_reverseList {

	public static void main(String[] args) {
		// 测试
		ListNode a1 = new ListNode(1);
		ListNode a2 = new ListNode(2);
		ListNode a3 = new ListNode(3);
		ListNode a4 = new ListNode(4);
		ListNode a5 = new ListNode(5);

		a1.next = a2;
		a2.next = a3;
		a3.next = a4;
		a4.next = a5;

		ListNode result = reverseList_2(a1);
		while (result != null) {
			System.out.println(result.val);
			result = result.next;
		}
	}

	// 利用栈实现，需要额外的空间
	public static ListNode reverseList(ListNode head) {

		if (head == null)
			return null;

		Stack<ListNode> stack = new Stack<>();
		while (head != null) {
			stack.add(head);
			head = head.next;
		}

		ListNode newHead = stack.pop();
		ListNode current = newHead;
		while (!stack.isEmpty()) {
			ListNode tmpNode = stack.pop();
			current.next = tmpNode;
			current = current.next;
		}
		current.next = null;
		return newHead;
	}

	// 迭代法
	public static ListNode reverseList_2(ListNode head) {
		// 将next指针指向前一个元素
		ListNode prev = null;
		ListNode curr = head;
		while (curr != null) {
			// 需要一个指针存下一个元素
			ListNode tmpNext = curr.next;
			curr.next = prev;
			prev = curr;
			curr = tmpNext;		
		}
		
		return prev;
	}

}
