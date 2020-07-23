package Leetcode.ByTags.Stack;

import java.util.Stack;

/**
 * 155. 最小栈
 * Easy
 */

public class _0155_MinStack {

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> min_stack = new Stack<>();

    public _0155_MinStack() {

    }

    public void push(int x) {
        stack.push(x);
        // 只在辅助栈为空，或者新加入的数小于辅助栈的栈顶时，才push新数
        // 因为之前的小数都在栈底呢，只要还存在最小数就是它，即使新加入数也不影响
        if (min_stack.isEmpty() || x <= min_stack.peek())
            min_stack.push(x);
    }

    public void pop() {
        System.out.println("peek:" + stack.peek() + " - " + min_stack.peek());
         if (stack.pop().equals(min_stack.peek()))
            min_stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min_stack.peek();
    }
}

class Test2 {
    public static void main(String[] args) {

        _0155_MinStack obj = new _0155_MinStack();
        obj.push(0);
        obj.push(1);
        obj.push(0);
        System.out.println(obj.getMin());
        System.out.println(obj.top());
        obj.pop();
        System.out.println(obj.getMin());

    }
}
