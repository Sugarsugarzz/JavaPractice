package Leetcode.ByTags.Array;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 200. 岛屿数量
 * Medium
 */

public class _0200_numIslands {
    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(numIslands(grid));
    }

    public static int numIslands(char[][] grid) {

        // 一、深度优先搜索
//        if (grid == null || grid.length == 0)
//            return 0;
//        int islands_num = 0;
//        int nr = grid.length, nc = grid[0].length;
//
//        for (int i = 0; i < nr; i++) {
//            for (int j = 0; j < nc; j++) {
//                if (grid[i][j] == '1') {
//                    dfs(grid, i, j, nr, nc);
//                    ++islands_num;
//                }
//            }
//        }
//        return islands_num;

        // 二、广度优先搜索
        // 以1为起始搜索点，遇到1将其加入队列处理，然后置为0
//        if (grid == null || grid.length == 0)
//            return 0;
//        int islands_num = 0;
//        int nr = grid.length, nc = grid[0].length;
//
//        for (int r = 0; r < nr; r++) {
//            for (int c = 0; c < nc; c++) {
//                if (grid[r][c] == '1') {
//                    ++islands_num;
//                    grid[r][c] = '0';
//                    Queue<String> queue = new LinkedList<>();
//                    queue.add(r + "-" + c);
//                    while (!queue.isEmpty()) {
//                        String pos = queue.remove();
//                        int row = Integer.parseInt(pos.split("-")[0]);
//                        int col = Integer.parseInt(pos.split("-")[1]);
//                        if (row - 1 >= 0 && grid[row-1][col] == '1') {
//                            queue.add((row-1) + "-" + col);
//                            grid[row-1][col] = '0';
//                        }
//                        if (row + 1 < nr && grid[row+1][col] == '1') {
//                            queue.add((row+1) + "-" + col);
//                            grid[row+1][col] = '0';
//                        }
//                        if (col - 1 >= 0 && grid[row][col-1] == '1') {
//                            queue.add(row + "-" + (col-1));
//                            grid[row][col-1] = '0';
//                        }
//                        if (col + 1 < nc && grid[row][col+1] == '1') {
//                            queue.add(row + "-" + (col+1));
//                            grid[row][col+1] = '0';
//                        }
//                    }
//                }
//            }
//        }
//        return islands_num;

        // 三、并查集
        if (grid == null || grid.length == 0)
            return 0;
        int nr = grid.length, nc = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    if (r - 1 >= 0 && grid[r-1][c] == '1') {
                        uf.union(r * nc + c, (r-1) * nc + c);
                    }
                    if (r + 1 < nr && grid[r+1][c] == '1') {
                        uf.union(r * nc + c, (r+1) * nc + c);
                    }
                    if (c - 1 >= 0 && grid[r][c-1] == '1') {
                        uf.union(r * nc + c, r * nc + c - 1);
                    }
                    if (c + 1 < nc && grid[r][c+1] == '1') {
                        uf.union(r * nc + c, r * nc + c + 1);
                    }
                }
            }
        }
        return uf.getCount();
    }

    /**
     * 深度优先搜索
     * 以1的为起始搜索点，搜索过程中将遇到的1置0，岛屿数量即为进行DFS的次数
     */
    private static void dfs(char[][] grid, int r, int c, int nr, int nc) {

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0')
            return;

        grid[r][c] = '0';
        dfs(grid, r + 1, c, nr, nc);
        dfs(grid, r - 1, c, nr, nc);
        dfs(grid, r, c + 1, nr, nc);
        dfs(grid, r, c - 1, nr, nc);
    }

    /**
     * 并查集
     * 以1为起始，将其相邻四个方向上的1在并查集中进行合并，岛屿数量就是并查集中连通分量的数量
     */
    static class UnionFind {
        int count;
        int[] parent;
        int[] rank;  // 秩

        public UnionFind(char[][] grid) {
            // 初始化
            count = 0;
            int nr = grid.length, nc = grid[0].length;
            parent = new int[nr * nc];
            rank = new int[nr * nc];
            for (int i = 0; i < nr; i++) {
                for (int j = 0; j < nc; j++) {
                    if (grid[i][j] == '1') {
                        parent[i * nc + j] = i * nc + j;  // 将每个为1节点的父节点都先设为自己
                        ++count;
                    }
                    rank[i * nc + j] = 0;  // 深度都为0
                }
            }
        }

        public int find(int i) {
            if (parent[i] != i)
                parent[i] = find(parent[i]);
            return parent[i];
        }

        public void union(int x, int y) {
            // 根据秩合并
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {  // 根父节点不同，秩小的合并到秩大的
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx;
                    rank[rootx]++;
                }
                --count;  // 合并后，节点变少一个
            }
        }

        public int getCount() {
            return count;
        }
    }
}
