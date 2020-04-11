package SwordToOffer;

import java.util.ArrayList;
import java.util.Stack;

public class Solution_3 {

    /**
     * 题目：输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
     *
     * 解析：借助堆栈的“后进先出”实现
     */


    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        Stack<Integer> stack = new Stack<>();

        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }

        ArrayList<Integer> list = new ArrayList<>();

        while (!stack.empty()) {
            list.add(stack.pop());
        }

        return list;

    }

    public static void main(String[] args) {

        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);

        System.out.println(printListFromTailToHead(node));
    }
}

/**
 * 题给出的类
 */
class ListNode {

    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

