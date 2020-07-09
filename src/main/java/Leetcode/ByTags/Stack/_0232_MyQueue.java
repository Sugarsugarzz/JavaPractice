package Leetcode.ByTags.Stack;

import java.util.Stack;

public class _0232_MyQueue {

    private static Stack<Integer> stack_in;
    private static Stack<Integer> stack_out;

    public static void main(String[] args) {
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
