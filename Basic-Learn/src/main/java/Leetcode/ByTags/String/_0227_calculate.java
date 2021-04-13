package Leetcode.ByTags.String;

import java.util.Stack;

/**
 * 227. 基本计算器 II
 * Medium
 */

public class _0227_calculate {
    public static void main(String[] args) {
        calculate("1+2*3");
    }

    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char preOpt = '+';

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == s.length() - 1) {
                switch(preOpt) {
                    case '+':  stack.push(num);  break;
                    case '-':  stack.push(-num);  break;
                    case '*':  stack.push(stack.pop() * num);  break;
                    case '/':  stack.push(stack.pop() / num);  break;
                }

                preOpt = s.charAt(i);
                num = 0;
            }
        }

        while (!stack.isEmpty()) {
            num += stack.pop();
        }
        return num;
    }
}
