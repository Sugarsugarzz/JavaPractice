package Leetcode.ByTags.LinkedList;

/**
 * 237. 删除链表中的节点
 * Easy
 */

public class _0237_deleteNode {

    public static void main(String[] args) {

        ListNode node = new ListNode(4);
        node.next = new ListNode(5);
        node.next.next = new ListNode(1);
        node.next.next.next = new ListNode(9);

        deleteNode(node.next);

        while (node != null) {
            System.out.print(node.val + ", ");
            node = node.next;
        }
    }

    public static void deleteNode(ListNode node) {

        // node实际上是要被删除的节点
        // 把下一个节点的值覆盖到当前节点node上，然后干掉下一个节点
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
