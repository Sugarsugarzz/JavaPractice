package Leetcode.ByDifficulty.Easy;

/**
 * 
 * title：回文链表
 * 
 * description：  请判断一个链表是否为回文链表。
 * 
 * limit：
 * 
 * label：链表、双指针
 * 
 */

public class _0234_isPalindrome {

	public static void main(String[] args) {
		// 测试
		ListNode a1 = new ListNode(1);
		ListNode a2 = new ListNode(2);
		ListNode a3 = new ListNode(2);
		ListNode a4 = new ListNode(1);
		a1.next = a2;
		a2.next = a3;
		a3.next = a4;
		
		boolean ans = isPalindrome(a1);
		System.out.println(ans);

	}

	// 快慢指针法找到链表的中点
	// 遍历的同时，将一半链表反转。
	public static boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null)
			return true;
		ListNode slow = head, fast = head;
		ListNode pre = head, prepre = null;
		while (fast != null && fast.next != null) {
			pre = slow;
			slow = slow.next;
			fast = fast.next.next;
			pre.next = prepre;
			prepre = pre;
		}
		// 如果是奇数个，就跳过中间那一位
		if (fast != null)
			slow = slow.next;
		while (pre != null && slow != null) {
			if (pre.val != slow.val)
				return false;
			pre = pre.next;
			slow = slow.next;
		}
		return true;  
	}
}
