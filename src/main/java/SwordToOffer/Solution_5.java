package SwordToOffer;

import java.util.Stack;

public class Solution_5 {

    /**
     * 题目：用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
     *
     * 解析：只能stack2为空时，才将stack1压入stack2中
     *
     */


    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);

    }

    public int pop() {

        if (stack1.empty() && stack2.empty())
            new RuntimeException("Queue is Empty!");

        if (stack2.empty())
            while (!stack1.empty())
                stack2.push(stack1.pop());

        return stack2.pop();

    }


    public static void main(String[] args) {


    }
}
