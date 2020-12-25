package Leetcode.ByTags.String;

import java.util.Stack;

/**
 * 394. 字符串解码
 * Medium
 */

public class _0394_decodeString {

    public static void main(String[] args) {
        String s = "3[a2[c]]";
        System.out.println(decodeString(s));
    }

    public static String decodeString(String s) {

        // 维护两个栈，存数字和字符子串
        StringBuilder sb = new StringBuilder();
        Stack<Integer> numStack = new Stack<>();
        Stack<String> strStack = new Stack<>();

        int num = 0;
        for (char ch : s.toCharArray()) {

            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');  // 不能直接加入栈，可能是多位数
            } else if (ch == '[') {
                numStack.push(num);
                strStack.push(sb.toString());
                sb = new StringBuilder();
                num = 0;
            } else if (ch == ']') {
                StringBuilder temp = new StringBuilder();
                int n = numStack.pop();
                for (int i = n; i > 0; i--) {
                    temp.append(sb.toString());
                }
                sb = new StringBuilder(strStack.pop() + temp.toString());
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}
