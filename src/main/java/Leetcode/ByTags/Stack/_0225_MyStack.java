package Leetcode.ByTags.Stack;

import java.util.LinkedList;

/**
 * 225. 用队列实现栈
 * Easy
 */

public class _0225_MyStack {

    private static LinkedList<Integer> queue;

    public static void main(String[] args) {

        // LinkedList内置函数能快速解决。

        // 最底层方法：用两个队列q1和q2.
        // push时正常push到q1.
        // pop时将q1弹出到q2，只留下一个元素，再将其弹出即可，最后将q1 = q2.
        // 用全局变量top保存栈顶元素。
    }

    public _0225_MyStack() {
        queue = new LinkedList<>();
    }

    public static void push(int x) {
        queue.addFirst(x);
    }

    public static int pop() {
        return queue.removeFirst();
    }

    public static int top() {
        return queue.getFirst();
    }

    public static boolean empty() {
        return queue.isEmpty();
    }
}
