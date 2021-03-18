package Leetcode.AimOffer;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 45. 把数组排成最小的数
 * Medium
 */
public class _45_minNumber {
    public static void main(String[] args) {
        int[] nums = {3, 30, 34, 5, 9};
        System.out.println(minNumber(nums));
    }

    public static String minNumber(int[] nums) {

        // 一、库函数自定义排序
//        Queue<String> queue = new PriorityQueue<>((o1, o2) -> (o1 + o2).compareTo(o2 + o1));
//        for (int num: nums) {
//            queue.offer("" + num);
//        }
//        StringBuilder sb = new StringBuilder();
//        while (!queue.isEmpty()) {
//            sb.append(queue.poll());
//        }
//        return sb.toString();

        // 二、优先队列
//        String[] strs = new String[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            strs[i] = "" + nums[i];
//        }
//        Arrays.sort(strs, (o1, o2) -> (o1+o2).compareTo(o2+o1));
//        StringBuilder sb = new StringBuilder();
//        for (String str: strs) {
//            sb.append(str);
//        }
//        return sb.toString();

        // 二、快排 自定义
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = "" + nums[i];
        }
        sort(strs, 0, strs.length - 1);
        StringBuilder sb = new StringBuilder();
        for (String str: strs) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static void sort(String[] strs, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int j = partition(strs, lo, hi);
        sort(strs, lo, j - 1);
        sort(strs, j + 1, hi);
    }

    public static int partition(String[] strs, int lo, int hi) {
        String val = strs[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while ((strs[++i] + val).compareTo(val + strs[i]) <= 0)  if (i == hi)  break;
            while ((strs[--j] + val).compareTo(val + strs[j]) >= 0)  if (j == lo)  break;
            if (i >= j)  break;
            String temp = strs[i];
            strs[i] = strs[j];
            strs[j] = temp;
        }
        strs[lo] = strs[j];
        strs[j] = val;
        return j;
    }
}

/**
 * 字符串 xy < yx , yz < zy ，需证明 xz < zx 一定成立。
 *
 * 设十进制数 x, y, z 分别有 a, b, c 位，则有：
 * （左边是字符串拼接，右边是十进制数计算，两者等价）
 *  xy = x * 10^b + y
 *  yx = y * 10^a + x
 *
 * 则 xy < yx 可转化为：
 *  x * 10^b + y < y * 10^a + x
 *  x (10^b - 1) < y (10^a - 1)
 *  x / (10^a - 1) < y / (10^b - 1)     ①
 *
 * 同理， 可将 yz < zy 转化为：
 *  y / (10^b - 1) < z / (10^c - 1)     ②
 *
 * 将 ① ② 合并，整理得：
 *  x / (10^a - 1) < y / (10^b - 1) < z / (10^c - 1)
 *  x / (10^a - 1) < z / (10^c - 1)
 *  x (10^c - 1) < z (10^a - 1)
 *  x * 10^c + z < z * 10^a + x
 *  ∴  可推出 xz < zx ，传递性证毕
 */
