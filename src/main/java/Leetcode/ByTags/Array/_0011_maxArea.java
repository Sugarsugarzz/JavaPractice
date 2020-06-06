package Leetcode.ByTags.Array;

/**
 * 11. 盛最多水的容器
 * Medium
 */

public class _0011_maxArea {

    public static void main(String[] args) {

        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int result = maxArea(height);
        System.out.println(result);
    }

    public static int maxArea(int[] height) {

        // 暴力法
//        int max = Integer.MIN_VALUE;
//        for (int i=0; i<height.length; i++)
//            for (int j=i; j<height.length; j++)
//                max = Math.max(Math.min(height[i], height[j]) * (j-i), max);
//        return max;

        // 双指针
        // 关键词：左右两边
        // 公式：双指针其中较小边的高度 * 距离
        // 思路：双指针分别从两边开始，固定高度较高的边，移动高度低的边。
        // 如果移动较高变，只会使距离变小；所以移动较小边才对。
        int max = Integer.MIN_VALUE;
        int left = 0, right = height.length - 1;

        while (left < right) {
            max = Math.max(Math.min(height[left], height[right]) * (right - left), max);
            if (height[left] <= height[right])
                left++;
            else
                right--;
        }

        return max;
    }
}
