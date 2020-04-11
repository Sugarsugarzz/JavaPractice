package Leetcode.ByDifficulty.middle;

/**
 * 
 * title：删除链表的倒数第N个节点
 * 
 * description：  给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 
 * limit：
 * 
 * label：链表、双指针
 * 
 */

public class _0019_removeNthFromEnd {

	public static void main(String[] args) {
		// 测试
		ListNode a1 = new ListNode(1);
		ListNode a2 = new ListNode(2);
		ListNode a3 = new ListNode(3);
		ListNode a4 = new ListNode(4);
		ListNode a5 = new ListNode(5);
		a1.next = a2;
//		a2.next = a3;
//		a3.next = a4;
//		a4.next = a5;

		ListNode ans = removeNthFromEnd_3(a1, 2);
		System.out.println();
		while (ans != null) {
			System.out.print(ans.val + " ");
			ans = ans.next;
		}
	}

	// 两次链表反转（自己的方法）
	public static ListNode removeNthFromEnd(ListNode head, int n) {

		if (head.next == null && n == 1)
			return null;
		
		ListNode curr = head, prev = null;
		// 1、将链表反转 
		while (curr.next != null) {
			ListNode tmpv = curr.next;
			curr.next = prev;
			prev = curr;
			curr = tmpv;
		}
		curr.next = prev;
		// 2、去除第n个节点
		int count = 1;
		if (n == 1) {
			curr = curr.next;
			head = curr;
		} else {
			head = curr;
			while (count != n) {
				prev = curr;
				curr = curr.next;
				count++;
			}
			prev.next = curr.next;
			curr = null;
		}
		// 3、再次反转链表
		curr = head; prev = null;
		while (curr.next != null) {
			ListNode tmpv = curr.next;
			curr.next = prev;
			prev = curr;
			curr = tmpv;
		}
		curr.next = prev;
		
		return curr;
	}// 12345  length=4  123
	
	// L-n+1即为要删除节点的位置
	public static ListNode removeNthFromEnd_2(ListNode head, int n) {
		ListNode dummyV = new ListNode(0);
		dummyV.next = head;
		ListNode curr = head;
		// 一遍遍历获取链表长度
		int length = 0;
		while (curr != null) {
			length++;
			curr = curr.next;
		}
		// 删除第l-n+1个节点
		length = length - n;
		System.out.println(length);
		curr = dummyV;
		while (length > 0) {
			curr = curr.next;
			length--;
		}
		curr.next = curr.next.next;

		return dummyV.next;
	}
	
	// 用快慢双指针，保持n的距离，当快指针到达null时，慢指针.next = 慢指针.next.next。 
	public static ListNode removeNthFromEnd_3(ListNode head, int n) {
		// 定义哑指针，快慢指针
		ListNode dummyV = new ListNode(0);
		dummyV.next = head;
		ListNode fast = dummyV, slow = dummyV;
		// fast先后移n位
		int count = 0;
		while (count <= n) {
			fast = fast.next;
			count++;
		}
		// 快慢指针同步推进，fast到结尾，则slow到n的前一位
		while (fast != null) {
			fast = fast.next;
			slow = slow.next;
		}
		slow.next = slow.next.next;
		
		return dummyV.next;
	}
}
