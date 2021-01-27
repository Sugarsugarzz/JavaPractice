package Leetcode.AimOffer;

import Leetcode.ByTags.LinkedList.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *  06. 从尾到头打印链表
 *  Easy
 */

public class _06_reversePrint {

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(3);
        head.next.next = new ListNode(1);

        System.out.println(Arrays.toString(reversePrint(head)));
    }

    public static int[] reversePrint(ListNode head) {

        // 一、借助List，头插法
//        List<Integer> list = new ArrayList<>();
//        while (head != null) {
//            list.add(0, head.val);
//            head = head.next;
//        }
//        int[] ans = new int[list.size()];
//        for (int i = 0; i < list.size(); i++) {
//            ans[i] = list.get(i);
//        }
//        return ans;

        // 二、借助Stack
//        Stack<Integer> stack = new Stack<>();
//        while (head != null) {
//            stack.push(head.val);
//            head = head.next;
//        }
//        int[] ans = new int[stack.size()];
//        int len = stack.size();
//        for (int i = 0; i < len; i++) {
//            ans[i] = stack.pop();
//        }
//        return ans;

        // 三、先获取长度，然后倒序入数组
        ListNode node = head;
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        int[] ans = new int[count];
        while (head != null) {
            ans[count-- -1] = head.val;
            head = head.next;
        }
        return ans;
    }
}
