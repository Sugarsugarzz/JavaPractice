package Leetcode.ByTags.Stack;

import java.util.Stack;

/**
 * 844. 比较含退格的字符串
 * Easy
 */

public class _0844_backspaceCompare {

    public static void main(String[] args) {

        String S = "a##c";
        String T = "#a#c";
        boolean res = backspaceCompare(S, T);
        System.out.println(res);
    }

    public static boolean backspaceCompare(String S, String T) {

        // 双栈实现
        return rebuildString(S).equals(rebuildString(T));
    }

    public static String rebuildString(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c: s.toCharArray()) {
            if (c != '#') {
                stack.push(c);
            } else if (!stack.isEmpty()) {
                stack.pop();
            }
        }
        return stack.toString();
    }
}
