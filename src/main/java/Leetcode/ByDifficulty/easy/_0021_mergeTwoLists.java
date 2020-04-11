package Leetcode.ByDifficulty.easy;

/**
 * 
 * title:给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 
 * limit:有效字符串需满足：
	左括号必须用相同类型的右括号闭合。
	左括号必须以正确的顺序闭合。
	注意空字符串可被认为是有效字符串。
	
 * label：字符串
 */

public class _0021_mergeTwoLists {

	public static void main(String[] args) {

		// 测试
		ListNode a1 = new ListNode(1);
		ListNode a2 = new ListNode(2);
		ListNode a3 = new ListNode(4);
		ListNode b1 = new ListNode(1);
		ListNode b2 = new ListNode(3);
		ListNode b3 = new ListNode(4);
		
		a1.next = a2;
		a2.next = a3;
		b1.next = b2;
		b2.next = b3;
		
		ListNode result = mergeTwoLists(a1, b1);
		System.out.println(result.toString());
	}
	
	// 自己的方法
	// 迭代
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		
		ListNode prehead = new ListNode(-1);
		ListNode prev = prehead;
		
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				prev.next = l1;
				l1 = l1.next;
			} else {
				prev.next = l2;
				l2 = l2.next;
			}
			prev = prev.next;
		}
		
		prev.next = l1==null? l2 : l1;
		
		
		return prehead.next;
	}
}

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val= x; next=null;}
}