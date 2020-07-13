package Leetcode.ByTags.Stack;

import javax.sound.midi.Soundbank;
import javax.xml.bind.SchemaOutputResolver;
import java.util.Stack;

/**
 * 150. 逆波兰表达式求值
 * Medium
 */

public class _0150_evalRPN {

    public static void main(String[] args) {

        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        int res = evalRPN(tokens);
        System.out.println(res);
    }

    public static int evalRPN(String[] tokens) {

        // 栈实现
//        Stack<Integer> stack = new Stack<>();
//        int a, b;
//        for (String token: tokens) {
//
//            switch (token) {
//                case "+":
//                    b = stack.pop();
//                    a = stack.pop();
//                    stack.push(a + b);
//                    break;
//                case "-":
//                    b = stack.pop();
//                    a = stack.pop();
//                    stack.push(a - b);
//                    break;
//                case "*":
//                    b = stack.pop();
//                    a = stack.pop();
//                    stack.push(a * b);
//                    break;
//                case "/":
//                    b = stack.pop();
//                    a = stack.pop();
//                    stack.push(a / b);
//                    break;
//                default:
//                    stack.push(Integer.parseInt(token));
//                    break;
//            }
//        }
//        return stack.pop();

        // 数组模拟栈
        int[] stack = new int[tokens.length / 2 + 1];
        int index = 0;
        for (String token: tokens) {
            switch (token) {
                case "+":
                    stack[index - 2] += stack[--index];
                    break;
                case "-":
                    stack[index -2] -= stack[--index];
                    break;
                case "*":
                    stack[index - 2] *= stack[--index];
                    break;
                case "/":
                    stack[index - 2] /= stack[--index];
                    break;
                default:
                    stack[index++] = Integer.parseInt(token);
                    break;
            }
        }
        return stack[0];
    }
}
