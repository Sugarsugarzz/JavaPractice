package Leetcode.ByTags.Array;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 349. 两个数组的交集
 * Easy
 */
public class _0349_intersection {

    public static void main(String[] args) {
        int[] nums1 = new int[] {1, 2, 4};
        int[] nums2 = new int[] {1, 3, 5};
        System.out.println(Arrays.toString(intersection(nums1, nums2)));
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        // 一、Stream利用set特性
//        Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
//        return Arrays.stream(nums2).distinct().filter(set1::contains).toArray();

        // 二、排序 + 双指针
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] inter = new int[nums1.length + nums2.length];
        int p1 = 0, p2 = 0, index = 0;
        while (p1 != nums1.length && p2 != nums2.length) {
            if (nums1[p1] == nums2[p2]) {
                if (index == 0 || inter[index-1] != nums1[p1])
                    inter[index++] = nums1[p1];
                p1++;
                p2++;
            } else if (nums1[p1] < nums2[p2]) {
                p1++;
            } else {
                p2++;
            }
        }
        return Arrays.copyOfRange(inter, 0, index);
    }
}
