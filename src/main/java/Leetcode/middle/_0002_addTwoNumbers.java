package Leetcode.middle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Set;
import java.util.Stack;

/**
 * 
 * title：两数相加
 * 
 * description：  给出两个 非空
 * 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 
 * limit：
 * 
 * label：链表、数学
 * 
 */

public class _0002_addTwoNumbers {

	public static void main(String[] args) {
		// 测试
		ListNode a1 = new ListNode(2);
		ListNode a2 = new ListNode(4);
		ListNode a3 = new ListNode(4);
		ListNode b1 = new ListNode(5);
		ListNode b2 = new ListNode(6);
		ListNode b3 = new ListNode(4);
		
		a1.next = a2;
		a2.next = a3;
		b1.next = b2;
		b2.next = b3;
//		ListNode a1 = new ListNode(0);
//		ListNode b1 = new ListNode(0);
//		
		ListNode ans = addTwoNumbers(a1, b1);
		System.out.println("hello");
		while (ans != null) {
			System.out.print(ans.val + " ");
			ans = ans.next;
		}
	}

	// 相当于竖式计算，计算每一位的值和进位，生成新链表节点
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		// 结果链表头结点
		ListNode ans = new ListNode(0);
		ListNode p = l1, q = l2, curr = ans;
		// 存进位
		int carry = 0;
		while (p != null || q != null) {
			int a = p != null ? p.val : 0;
			int b = q != null ? q.val : 0;
			int sum = a + b + carry;
			carry = sum / 10;
			// 计算每一位结果的同时，生成新节点
			curr.next = new ListNode(sum % 10);
			curr = curr.next;
			if (p != null) p = p.next;
			if (q != null) q = q.next;
		}
		if (carry > 0)
			curr.next = new ListNode(carry);
		return ans.next;
	}

}


class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val= x; next=null;}
}
