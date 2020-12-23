package Leetcode.ByTags.Array;

import java.util.*;

/**
 * 621. 任务调度器
 * Medium
 */

public class _0621_leastInterval {

    public static void main(String[] args) {

        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 2;
        System.out.println(leastInterval(tasks, n));
    }

    public static int leastInterval(char[] tasks, int n) {
        
        int[] cnt = new int[26];
        for (char ch : tasks) {
            cnt[ch - 'A']++;
        }
        int max = 0;
        for (int i : cnt) {
            max = Math.max(max, i);
        }
        int k = 0;
        for (int i : cnt) {
            if (i == max) k++;
        }
        int ans = (max - 1) * (n + 1) + k;
        return Math.max(ans, tasks.length);
    
    }
}
