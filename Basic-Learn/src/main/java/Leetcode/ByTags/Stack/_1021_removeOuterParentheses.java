package Leetcode.ByTags.Stack;

import java.lang.annotation.Target;
import java.util.Stack;

/**
 * 1021. 删除最外层的括号
 * Easy
 */

public class _1021_removeOuterParentheses {

    public static void main(String[] args) {

        String s = "(()())(())";
        String res = removeOuterParentheses(s);
        System.out.println(res);
    }

    public static String removeOuterParentheses(String S) {

        // 栈为空，则说明找到了一个原语，记下起始和结束位置，删除外层括号即可
//        StringBuffer sb = new StringBuffer();
//        Stack<Character> stack = new Stack<>();
//        boolean flag = false;
//        int start = 0, end = 0;
//        char[] chars = S.toCharArray();
//
//        for (int i = 0; i < chars.length; i++) {
//            if (chars[i] == '(') {
//                stack.push(chars[i]);
//                if (!flag) {
//                    start = i;
//                    flag = true;
//                }
//            } else if (chars[i] == ')') {
//                stack.pop();
//                if (stack.isEmpty()) {
//                    end = i;
//                    sb.append(S.substring(start + 1, end));
//                    flag = false;
//                    start = end;
//                }
//            }
//        }
//
//        return sb.toString();

        // 简洁解法
        StringBuffer sb = new StringBuffer();
        int level = 0;
        for (Character c: S.toCharArray()) {
            if (c == ')') --level;
            if (level >= 1) sb.append(c);
            if (c == '(') ++level;
        }
        return sb.toString();
    }
}
