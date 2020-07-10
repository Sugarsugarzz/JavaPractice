package Leetcode.ByTags.Stack;

import java.util.Stack;

/**
 * 232. 用栈实现队列
 * Easy
 */

public class _0232_MyQueue {

    private static Stack<Integer> stack_in;
    private static Stack<Integer> stack_out;

    public static void main(String[] args) {

        // 底层方法：用两个Stack实现。
        // push时，将stack_out中的全部pop到stack_in中，然后push到stack_in。
        // pop时，将stack_in中的全部pop到stack_out中，然后从stack_out中pop，即为队首。
    }

    public _0232_MyQueue() {
        stack_in = new Stack<>();
        stack_out = new Stack<>();
    }

    public static void push(int x) {
        while (!stack_out.isEmpty()) {
            stack_in.push(stack_out.pop());
        }
        stack_in.push(x);
    }

    public static int pop() {
        while (!stack_in.isEmpty()) {
            stack_out.push(stack_in.pop());
        }
        return stack_out.pop();
    }

    public static int peek() {
        while (!stack_in.isEmpty()) {
            stack_out.push(stack_in.pop());
        }
        return stack_out.peek();
    }

    public boolean empty() {
        return stack_in.isEmpty() && stack_out.isEmpty();
    }
}
