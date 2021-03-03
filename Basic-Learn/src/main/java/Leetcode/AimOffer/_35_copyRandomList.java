package Leetcode.AimOffer;

import Leetcode.ByTags.HashTable.Node;

import java.util.*;

/**
 * 35. 复杂链表的赋值
 * Medium
 */
public class _35_copyRandomList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

    }

    static Map<Node, Node> visitedMap = new HashMap<>();

    public Node copyRandomList(Node head) {
        // 一、哈希，借助额外空间
//        if (head == null)  return null;
//        if (visitedMap.containsKey(head))  return visitedMap.get(head);
//        Node node = new Node(head.val);
//        visitedMap.put(head, node);
//        node.next = copyRandomList(head.next);
//        node.random = copyRandomList(head.random);
//        return node;

        // 二、不使用额外空间，不能修改原链表结构
        // 穿插新节点，
        // cur为旧节点，cur.next.random = cur.random.next
        // 拆分旧节点


        return null;
    }


}