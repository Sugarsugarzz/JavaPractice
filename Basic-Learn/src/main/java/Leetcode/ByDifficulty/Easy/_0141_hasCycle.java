package Leetcode.ByDifficulty.Easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * title: 环形链表
 * 
 * description：给定一个链表，判断链表中是否有环。
 * 
 * limit:为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是
 * -1，则在该链表中没有环。
 * 
 * label：位运算、哈希表
 * 
 */

public class _0141_hasCycle {

	public static void main(String[] args) {
		// 测试
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		ListNode d = new ListNode(4);

		a.next = b;
		b.next = c;
		c.next = d;
//		d.next = b;
		
		boolean result = hasCycle(a);
		System.err.println(result);
	}

	public static boolean hasCycle(ListNode head) {
		
		Set<ListNode> set = new HashSet<>();
		while (head != null) {
			if (set.contains(head))
				return true;
			else 
				set.add(head);
			head = head.next;
		}	
		return false;
	}
}
