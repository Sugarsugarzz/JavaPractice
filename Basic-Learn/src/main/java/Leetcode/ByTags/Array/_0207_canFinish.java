package Leetcode.ByTags.Array;

import java.util.*;

/**
 * 207. 课程表
 * Medium
 */

public class _0207_canFinish {
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {
                {1, 0},
                {0, 1}
        };
        System.out.println(canFinish(numCourses, prerequisites));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {

        // 利用拓扑排序思想
        // 一、广度优先搜索（入度表 + 邻接表）
//        Queue<Integer> queue = new LinkedList<>();
//        int[] indegrees = new int[numCourses];
//        List<List<Integer>> adjacency = new ArrayList<>();
//        // 初始化
//        for (int i = 0; i < numCourses; i++)
//            adjacency.add(new ArrayList<>());
//        // 邻接表
//        for (int[] prerequisite : prerequisites) {
//            indegrees[prerequisite[0]]++;
//            adjacency.get(prerequisite[1]).add(prerequisite[0]);
//        }
//        // 起始节点入队
//        for (int i = 0; i < numCourses; i++) {
//            if (indegrees[i] == 0)
//                queue.add(i);
//        }
//        while (!queue.isEmpty()) {
//            int pre = queue.poll();
//            --numCourses;
//            for (Integer cur : adjacency.get(pre)) {
//                if (--indegrees[cur] == 0)
//                    queue.add(cur);
//            }
//        }
//
//        return numCourses == 0;

        // 二、深度优先搜索
        List<List<Integer>> adjacency = new ArrayList<>();
        int[] flags = new int[numCourses];
        for (int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());
        for (int[] prerequisite : prerequisites) {
            adjacency.get(prerequisite[1]).add(prerequisite[0]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(adjacency, flags, i))
                return false;
        }
        return true;
    }

    public static boolean dfs(List<List<Integer>> adjacency, int[] flags, int i) {
        if (flags[i] == 1)
            return false;
        if (flags[i] == -1)
            return true;
        flags[i] = 1;
        for (Integer j : adjacency.get(i)) {
            if (!dfs(adjacency, flags, j))
                return false;
        }
        flags[i] = -1;
        return true;
    }
}
