package Leetcode.ByTags.String;

import javax.servlet.jsp.PageContext;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

/**
 * 32. 最长有效括号
 * Difficult
 */

public class _0032_longestValidParentheses {

    public static void main(String[] args) {

        String s = "(()())";
        System.out.println(longestValidParentheses(s));
    }

    public static int longestValidParentheses(String s) {

        // 一、暴力法（超时）
//        int max = 0;
//        for (int i = 0; i < s.length(); i++) {
//            for (int j = i + 1; j < s.length(); j++) {
//                boolean flag = isValid(i, j, s);
//                if (flag) {
//                    max = Math.max(max, j - i + 1);
//                }
//            }
//        }
//        return max;

        // 二、栈方法
//        if (s.length() == 0 || s == null)
//            return 0;
//        Stack<Integer> stack = new Stack<>();
//        stack.push(-1);
//        int max = 0;
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) == '(') {
//                stack.push(i);
//            } else {
//                stack.pop();
//                if (stack.isEmpty())
//                    stack.push(i);
//                else
//                    max = Math.max(max, i - stack.peek());
//            }
//        }
//        return max;

        // 三、动态规划
        if (s.length() == 0 || s == null)
            return 0;
        int[] dp = new int[s.length()];
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                dp[i] = 0;
            } else if (s.charAt(i) == ')') {
                if (s.charAt(i-1) == '(') {
                    dp[i] = i > 1 ? dp[i-2] + 2 : 2;
                } else if (s.charAt(i-1) == ')' && i - 1 >= dp[i-1] && s.charAt(i - dp[i-1] -1) == '('){
                    dp[i] = i-dp[i-1]-2 >= 0 ? dp[i - dp[i-1] - 2] + dp[i-1] + 2 : dp[i-1] + 2;
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // 子串是否是有效括号子串
    public static boolean isValid(int left, int right, String s) {
        Stack<Character> stack = new Stack<>();

        while (left <= right) {
            if (s.charAt(left) == '(') {
                stack.push('(');
            } else {
                if (stack.isEmpty())
                    return false;
                stack.pop();
            }
            left++;
        }
        return stack.isEmpty();
    }
}
