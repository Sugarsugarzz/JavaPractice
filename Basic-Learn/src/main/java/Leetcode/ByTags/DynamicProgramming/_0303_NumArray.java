package Leetcode.ByTags.DynamicProgramming;


import org.omg.CORBA.INTERNAL;

import java.util.*;

/**
 * 303. 区域和检索 - 数组不可变
 * Easy
 */

public class _0303_NumArray {

    private int[] nums;
    private Map<Pair<Integer, Integer>, Integer> map = new HashMap<>();
    private int[] sum;

    public _0303_NumArray(int[] nums) {
        // 一、暴力法
//        this.nums = nums;

        // 二、缓存
//        for (int i = 0; i < nums.length; i++) {
//            int sum = 0;
//            for (int j = i; j < nums.length; j++) {
//                sum += nums[j];
//                map.put(Pair.create(i, j), sum);
//            }
//        }

        // 三、动态规划
        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++)
            sum[i] = sum[i - 1] + nums[i - 1];
    }

    public int sumRange(int i, int j) {
        // 一、暴力法
//        int count = 0;
//        for (int k = i; k <= j; k++)
//            count += nums[k];
//        return count;

        // 二、缓存
        // 会多次调用sumRange方法，因当缓存
//        return map.get(Pair.create(i, j));

        // 三、动态规划
        return sum[j + 1] - sum[i];
    }
}

class Pair<F, S> {
    public final F first;
    public final S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    public static <A, B> Pair<A, B> create(A a, B b) {
        return new Pair<A, B>(a, b);
    }
}

class Test {
    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        _0303_NumArray obj = new _0303_NumArray(nums);
        System.out.println(obj.sumRange(0, 5));
    }
}
