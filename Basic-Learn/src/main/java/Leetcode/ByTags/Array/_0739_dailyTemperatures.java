package Leetcode.ByTags.Array;

import java.util.Arrays;
import java.util.Stack;

/**
 * 739. 每日温度
 * Medium
 */

public class _0739_dailyTemperatures {

    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] ans = dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(ans));
    }

    public static int[] dailyTemperatures(int[] T) {

        // 一、暴力法
//        int[] ans = new int[T.length];
//
//        for (int i = 0; i < T.length; i++) {
//            int step = 0;
//            for (int j = i; j < T.length; j++) {
//                if (T[j] <= T[i]) {
//                    step++;
//                } else {
//                    break;
//                }
//            }
//
//            if (i + step < T.length) ans[i] = step;
//        }
//        return ans;

        // 二、单调栈  O(n)
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                int pre = stack.pop();
                ans[pre] = i - pre;
            }
            stack.add(i);
        }
        return ans;
    }
}
