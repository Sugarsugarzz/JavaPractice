package Leetcode.ByTags.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 406. 根据身高重建队列
 * Medium
 */

public class _0406_reconstructQueue {
    public static void main(String[] args) {
        int[][] people = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        System.out.println(Arrays.deepToString(reconstructQueue(people)));
    }

    public static int[][] reconstructQueue(int[][] people) {


        // 套路：数对类型，涉及排序的，根据第一个元素正向排序，第二个元素反向排序；
        // 或者第一个元素反向排序，第二个元素正向排序。都能够简化题解
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o1[1] - o2[1];
                else
                    return o2[0] - o1[0];
            }
        });
//        System.out.println(Arrays.deepToString(people));

        List<int[]> list = new ArrayList<>();
        for (int[] person : people) {
            if (list.size() > person[1]) {
                list.add(person[1], person);
            } else {
                list.add(person);
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}
