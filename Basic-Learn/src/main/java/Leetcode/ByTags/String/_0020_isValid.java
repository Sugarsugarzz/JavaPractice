package Leetcode.ByTags.String;

import java.util.HashMap;
import java.util.Stack;

/**
 * 20.有效的括号
 * Easy
 */

public class _0020_isValid {

    public static void main(String[] args) {

        String s = "({{[[]]}})";
        boolean result = isValid(s);
        System.out.println(result);
    }

    public static boolean isValid(String s) {

        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        Stack<Character> stack = new Stack<>();

        // 遍历
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if (stack.empty() || stack.pop() != map.get(s.charAt(i)))
                    return false;
            } else {
                stack.push(s.charAt(i));
            }
        }

        if (stack.empty())
            return true;
        else
            return false;
    }

}
