package Leetcode.ByDifficulty.easy;

/**
 * 
 * title：移除链表元素
 * 
 * description：  删除链表中等于给定值 val 的所有节点。
 * 
 * limit：
 * 
 * label：链表
 * 
 */

public class _0203_removeElements {

	public static void main(String[] args) {
		// 测试
		ListNode a1 = new ListNode(6);
		ListNode a2 = new ListNode(2);
		ListNode a3 = new ListNode(6);
		ListNode a4 = new ListNode(3);
		ListNode a5 = new ListNode(4);
		ListNode a6 = new ListNode(5);
		ListNode a7 = new ListNode(6);
		a1.next = a2;
		a2.next = a3;
		a3.next = a4;
		a4.next = a5;
		a5.next = a6;
		a6.next = a7;

		ListNode ans = removeElements(a1, 6);
		while (ans != null) {
			System.err.println(ans.val);
			ans = ans.next;
		}
	}

	public static ListNode removeElements(ListNode head, int val) {

		if (head == null)
			return null;
		// 如果head的val等于val，抛去当前head节点
		while (head.val == val) {
			head = head.next;
			if (head == null)
				return null;
		}
			
		// 遇到val与定值相等的，则将该节点从链表剔除
		ListNode cur = head, pre = head;
		while (cur != null) {
			if (cur.val == val) {
				cur = cur.next;
				pre.next = cur;
			} else {
				pre = cur;
				cur = cur.next;
			}

		}
		return head;
	}

}
