package Leetcode.ByTags.Stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 84. 柱状图中最大的矩形
 * Difficult
 */

public class _0084_largestRectangleArea {

    public static void main(String[] args) {

        int[] heights = {2, 1, 2};
        System.out.println(largestRectangleArea(heights));
    }

    public static int largestRectangleArea(int[] heights) {

        // 一、暴力法
        // 遍历每个矩形的高度，向两边扩展
//        int len = heights.length;
//        if (len == 0)    return 0;
//
//        int max = 0;
//        for (int i = 0; i < len; i++) {
//            int currentHeight = heights[i];
//            // 左延伸
//            int left = i;
//            while (left - 1 >= 0 && (heights[left - 1] >= currentHeight)) left--;
//            // 右延伸
//            int right = i;
//            while (right + 1 < len && (heights[right + 1] >= currentHeight)) right++;
//
//            max = Math.max(max, (right - left + 1) * currentHeight);
//        }
//        return max;

        // 二、单调栈
//        int len = heights.length;
//        if (len == 0)  return 0;
//        if (len == 1)  return heights[0];
//
//        int max = 0;
//        Stack<Integer> stack = new Stack<>();
//        for (int i = 0; i < len; i++) {
//            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
//                // 当当前柱形高度小于栈顶的柱形高度，出栈，计算最大面积
//                int currentHeight = heights[stack.pop()];
//                while (!stack.isEmpty() && heights[stack.peek()] == currentHeight) {
//                    stack.pop();
//                }
//                int currentWidth;
//                if (stack.isEmpty()) {
//                    currentWidth = i;
//                } else {
//                    currentWidth = i - stack.peek() - 1;
//                }
//                max = Math.max(max, currentHeight * currentWidth);
//            }
//            stack.push(i);
//        }
//
//        while (!stack.isEmpty()) {
//            int currentHeight = heights[stack.pop()];
//            while (!stack.isEmpty() && heights[stack.peek()] == currentHeight) {
//                stack.pop();
//            }
//            int currentWidth;
//            if (stack.isEmpty()) {
//                currentWidth = len;
//            } else {
//                currentWidth = len - stack.peek() - 1;
//            }
//            max = Math.max(max, currentHeight * currentWidth);
//        }
//
//        return max;

        // 三、单调栈+哨兵
        // 左右加一个高度为0的柱形，让栈元素能够一边遍历后全部出栈
        int len = heights.length;
        if (len == 0)  return 0;
        if (len == 1)  return heights[0];

        int max = 0;
        // 添加哨兵
        int[] newHeights = new int[len + 2];
        System.arraycopy(heights, 0, newHeights, 1, len);
        newHeights[0] = 0;
        newHeights[len + 1] = 0;
        len += 2;
        heights = newHeights;

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i < len; i++) {
            while (heights[i] < heights[stack.peek()]) {
                int currentHeight = heights[stack.pop()];
                int currentWidth = i - stack.peek() - 1;
                max = Math.max(max, currentHeight * currentWidth);
            }
            stack.push(i);
        }
        return max;
    }
}
