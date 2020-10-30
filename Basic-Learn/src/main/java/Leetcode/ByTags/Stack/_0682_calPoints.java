package Leetcode.ByTags.Stack;

import java.util.Stack;

/**
 * 682. 棒球比赛
 * Easy
 */

public class _0682_calPoints {

    public static void main(String[] args) {

        String[] ops = {"5","-2","4","C","D","9","+","+"};
        int res = calPoints(ops);
        System.out.println(res);
    }

    public static int calPoints(String[] ops) {

        // 利用栈实现
        Stack<Integer> stack = new Stack<>();
        // 不同元素执行不同栈操作
        for (String op: ops) {
            switch (op) {
                case "C":
                    stack.pop();
                    break;
                case "D":
                    stack.push(stack.peek() * 2);
                    break;
                case "+":
                    int tmp = stack.pop();
                    int tmp2 = stack.peek() + tmp;
                    stack.push(tmp);
                    stack.push(tmp2);
                    break;
                default:
                    stack.push(Integer.parseInt(op));
                    break;
            }
        }
        // 将栈全部弹出，计算总和
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }

        return res;
    }
}
