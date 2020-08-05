package Leetcode.ByTags.HashTable;

import java.beans.Visibility;
import java.util.HashMap;
import java.util.Map;

/**
 * 138. 复制带随机指针的链表
 * Medium
 */

public class _0138_copyRandomList {

    static Map<Node, Node> visitedMap = new HashMap<>();

    public static void main(String[] args) {

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        node1.next = node2;
        node1.random = node2;
        node2.next = null;
        node2.random = node2;

        Node res = copyRandomList(node1);
        System.out.println(res.val);
    }

    public static Node copyRandomList(Node head) {

        // 一、回溯法
//        if (head == null)
//            return null;
//        if (visitedMap.containsKey(head))
//            return visitedMap.get(head);
//        Node node = new Node(head.val);
//        visitedMap.put(head, node);
//        node.next = copyRandomList(head.next);
//        node.random = copyRandomList(head.random);
//
//        return visitedMap.get(head);

        // 二、迭代法
        // 遍历生成节点，然后根据random和next生成节点
        if (head == null)
            return null;
        Node oldNode = head;
        Node newNode = new Node(oldNode.val);
        visitedMap.put(oldNode, newNode);

        while (oldNode != null) {
            newNode.next = getClonedNode(oldNode.next);
            newNode.random = getClonedNode(oldNode.random);

            oldNode = oldNode.next;
            newNode = newNode.next;
        }
        return visitedMap.get(head);

        // 三、空间（1）的方法
    }

    public static Node getClonedNode(Node node) {

        if (node != null) {
            if (visitedMap.containsKey(node)) {
                return visitedMap.get(node);
            } else {
                visitedMap.put(node, new Node(node.val));
                return visitedMap.get(node);
            }
        }
        return null;
    }
}
