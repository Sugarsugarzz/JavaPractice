package Leetcode.ByTags.Stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 496. 下一个更大的元素 I
 * Easy
 */

public class _0496_nextGreaterElement {

    public static void main(String[] args) {

        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        int[] res = nextGreaterElement(nums1, nums2);
        System.out.println(Arrays.toString(res));
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {

        // 自写方法
//        int[] res = new int[nums1.length];
//        // 遍历nums1
//        for (int i = 0; i < nums1.length; i++) {
//            res[i] = -1;
//            // 在nums2找到对应元素
//            for (int j = 0; j < nums2.length; j++) {
//                if (nums1[i] == nums2[j]) {
//                    int num = nums2[j];
//                    // 继续向后遍历，获取更大的元素
//                    for (; j + 1 < nums2.length; j++) {
//                        if (num < nums2[j+1]) {
//                            res[i] = nums2[j+1];
//                            break;
//                        }
//                    }
//                    break;
//                }
//            }
//        }
//
//        return res;

        // 栈 + Map 的方法
        // 利用栈，构建 每个数与其下一个更大数 的map
        int[] res = new int[nums1.length];
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                map.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }
        while (!stack.isEmpty())
            map.put(stack.pop(), -1);

        // 根据map获取答案
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }

        return res;
    }
}
