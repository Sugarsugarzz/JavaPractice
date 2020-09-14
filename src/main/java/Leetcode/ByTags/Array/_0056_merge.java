package Leetcode.ByTags.Array;

import java.util.Arrays;

/**
 * 56. 合并区间
 * Medium
 */
public class _0056_merge {

    public static void main(String[] args) {

        int[][] intervals = {
                {1, 3},
                {2, 6},
                {15, 18},
                {8, 10}
        };
        for (int[] nums : merge(intervals)) {
            System.out.println(Arrays.toString(nums));
        }
    }

    public static int[][] merge(int[][] intervals) {

        /*
        首先根据前一位数，对数组进行排序。
            {v1, v2} 与 {k1, k2}
        三种情况：
            1. v2 < k1 （不可合并）
            2. v2 >= k1 （可合并）
         */
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);

        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval : intervals) {
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }

        return Arrays.copyOf(res, idx+1);
    }
}
