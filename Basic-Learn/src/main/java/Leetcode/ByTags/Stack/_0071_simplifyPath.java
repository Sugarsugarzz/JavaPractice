package Leetcode.ByTags.Stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 71. 简化路径
 * Medium
 */

public class _0071_simplifyPath {

    public static void main(String[] args) {

        String path = "/home/";
        String res = simplifyPath(path);
        System.out.println(res);

    }

    public static String simplifyPath(String path) {

        // 遇..出栈，遇.和空不变，其他的入栈
        Stack<String> stack = new Stack<>();
        String[] items = path.split("/");

        for (String item: items) {
            if (item.equals(".") || item.equals(""))
                continue;
            else if (item.equals("..")) {
                if (!stack.isEmpty())
                    stack.pop();
            }
            else
                stack.push(item);
        }

        if (stack.isEmpty())
            return  "/";

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i< stack.size(); i++) {
            sb.append("/");
            sb.append(stack.get(i));
        }
        return sb.toString();
    }
}
