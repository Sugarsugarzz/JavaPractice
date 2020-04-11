package Leetcode.easy;

import java.util.function.IntPredicate;

/**
 * 
 * title: 删除排序链表中的重复元素
 * 
 * description：给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * 
 * limit:
 * 
 * label：链表
 * 
 */

public class _0083_deleteDuplicates {

	public static void main(String[] args) {
		// 测试
		ListNode a1 = new ListNode(1);
		ListNode a2 = new ListNode(1);
		ListNode a3 = new ListNode(2);
		ListNode a4 = new ListNode(3);
		ListNode a5 = new ListNode(3);
		a1.next = a2;
		a2.next = a3;
		a3.next = a4;
		a4.next = a5;
		
		ListNode result = deleteDuplicates(a1);
		while (result != null) {
			System.out.println(result.val);
			result = result.next;
		}
	}
	
	// 
	public static ListNode deleteDuplicates(ListNode head) {

		ListNode currentNode = head;
		while (currentNode != null && currentNode.next != null) {
			if (currentNode.val == currentNode.next.val) {
				ListNode node = currentNode.next;
				currentNode.next = node.next;
				node.next = null;  // 清除野指针
			} else {
				currentNode = currentNode.next;
			}
		}
		return head;
	}
}