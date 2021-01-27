package Leetcode.ByTags.LinkedList;

/**
 * 链表结点
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {

        ListNode temp = next;
        StringBuilder sb = new StringBuilder();
        sb.append(val);
        while (temp != null) {
            sb.append(" -> ").append(temp.val);
            temp = temp.next;
        }
        return sb.toString();
    }
}
