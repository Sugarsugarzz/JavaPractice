package Leetcode.AimOffer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 13. 机器人的运动范围
 * Medium
 */
public class _13_movingCount {
    public static void main(String[] args) {
        System.out.println(movingCount(2, 3, 1));
    }


    public static int movingCount(int m, int n, int k) {

        // 一、DFS
//        boolean[][] visited = new boolean[m][n];
//        return dfs(0, 0, m, n, k, visited);

        // 二、BFS
        int[][] directions = {{0, 1}, {1, 0}};
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        visited[0][0] = true;
        queue.add(new int[]{0, 0});
        int count = 0;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            count++;
            for (int i = 0; i < 2; i++) {
                int nx = pos[0] + directions[i][0];
                int ny = pos[1] + directions[i][1];
                if (nx < 0 || ny < 0 || nx >= m || ny >= n || visited[nx][ny] || (nx/10 + nx%10 + ny/10 + ny%10 > k)) {
                    continue;
                }
                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny});
            }
        }

        return count;

    }

    public static int dfs(int x, int y, int m, int n, int k, boolean[][] visited) {

//        if (x < 0 || y < 0 || x >= m || y >= n || (getDigit(x) + getDigit(y)) > k || visited[x][y]) {
        // 根据题目设定，x和y不大于100
        if (x < 0 || y < 0 || x >= m || y >= n || (x/10 + x%10 + y/10 + y%10) > k || visited[x][y]) {
            return 0;
        }
        visited[x][y] = true;
        // 从(0,0)出发，移动方向只能为向下和向右
//        return dfs(x + 1, y, m, n, k, visited) + dfs(x - 1, y, m, n, k, visited)
//                + dfs(x, y + 1, m, n, k, visited) + dfs(x, y - 1, m, n, k, visited) + 1;
        return dfs(x + 1, y, m, n, k, visited) + dfs(x, y + 1, m, n, k, visited) + 1;
    }

//    public static int getDigit(int x) {
//        int sum = 0;
//        while (x != 0) {
//            sum += x % 10;
//            x /= 10;
//        }
//        return sum;
//    }
}
