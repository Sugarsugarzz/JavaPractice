package Leetcode.ByTags.DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 120. 三角形最小路径和
 * Medium
 */

public class _0120_minimumTotal {

    public static void main(String[] args) {

        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));

        System.out.println(minimumTotal(triangle));
    }

    public static int minimumTotal(List<List<Integer>> triangle) {

        // 一、动态规划
        for (int i = 1; i < triangle.size(); i++) {
            int len = triangle.get(i).size();
            for (int j = 0; j < len; j++) {
                int a = j - 1 >= 0 ? triangle.get(i-1).get(j-1) : Integer.MAX_VALUE;
                int b = j < i ? triangle.get(i-1).get(j) : Integer.MAX_VALUE;
                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(a, b));
            }
        }

        return Collections.min(triangle.get(triangle.size() - 1));
    }
}
