package Leetcode.AimOffer;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 40. 最小的k个数
 * Easy
 * 思路：一般排序后截取，需要O(nlogn)，但利用变形快排可将复杂度降到O(n)(平均)，堆排降到O(nlogk)。
 * 堆排序可以利用优先队列简单实现，但面试可能会考不用优先队列的堆排实现方式。
 * 快排利用基准数左边均小于基准书的特性。
 *
 * 对比：快速排序在时间、空间复杂度上都优于堆排序，但存在以下两个缺陷：
 *  1. 需要修改原数组，不能修改的话，需要拷贝一份数组，增加了空间复杂度。
 *  2. 需要保存整个数组，数据量非常大的时候，内存可能放不下。但堆的处理方式是来一个处理一个，只需要存储K个空间。
 */
public class _40_getLeastNumbers {
    public static void main(String[] args) {
        int[] leastNumbers = getLeastNumbers(new int[]{4, 3, 5, 1}, 2);
        System.out.println(Arrays.toString(leastNumbers));
    }

    public static int[] getLeastNumbers(int[] arr, int k) {

        // 一、快速排序
//        if (arr.length == 0 || k == 0)
//            return new int[0];
//        else if (arr.length <= k)
//            return arr;
//        sort(arr, 0, arr.length - 1, k);
//        return Arrays.copyOf(arr, k);

        // 二、堆排序
        if (arr.length == 0 || k == 0)
            return new int[0];
        else if (arr.length < k)
            return arr;

        Queue<Integer> queue = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);

        for (int num : arr) {
            if (queue.isEmpty() || queue.size() < k || num < queue.peek()) {
                queue.offer(num);
            }
            if (queue.size() > k) {
                queue.poll();
            }
        }

        return queue.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void sort(int[] arr, int lo, int hi, int k) {
        // partition后，arr[j]到最终位置，且左边均小于arr[j]，右边均大于arr[j]
        int j = partition(arr, lo, hi);
        if (j == k) {
            return;
        } else if (j > k) {
            sort(arr, lo, j - 1, k);
        } else {
            sort(arr, j + 1, hi, k);
        }
    }

    public static int partition(int[] arr, int lo, int hi) {
        int val = arr[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (arr[++i] < val)  if (i == hi)  break;
            while (arr[--j] > val)  if (j == lo)  break;
            if (i >= j)  break;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        arr[lo] = arr[j];
        arr[j] = val;
        return j;
    }
}
