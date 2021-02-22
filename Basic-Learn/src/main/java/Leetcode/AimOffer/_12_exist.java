package Leetcode.AimOffer;

/**
 * 12. 矩阵中的路径
 * Medium
 */
public class _12_exist {
    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'F'}
        };
        String word = "ABCCED";
        System.out.println(exist(board, word));
    }

//    static int n, m;
//    static boolean[][] marked;
//    static int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
//
//    public static boolean exist(char[][] board, String word) {
//
//        if (board.length == 0)
//            return false;
//
//        n = board.length;
//        m = board[0].length;
//        marked = new boolean[n][m];
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                if (dfs(i, j, word, 0, board))
//                    return true;
//            }
//        }
//
//        return false;
//    }
//
//    public static boolean dfs(int x, int y, String word, int idx, char[][] board) {
//
//        if (idx == word.length() - 1)
//            return word.charAt(idx) == board[x][y];
//
//        if (word.charAt(idx) == board[x][y]) {
//            marked[x][y] = true;
//            for (int i = 0; i < 4; i++) {
//                int newX = x + directions[i][0];
//                int newY = y + directions[i][1];
//
//                if (isInArea(newX, newY) && !marked[newX][newY]) {
//                    if (dfs(newX, newY, word, idx+1, board))
//                        return true;
//                }
//            }
//            marked[x][y] = false;
//        }
//
//        return false;
//    }

//    public static boolean isInArea(int x, int y) {
//        return x >= 0 && x < n && y >= 0 && y < m;
//    }

    public static boolean exist(char[][] board, String word) {

        if (board.length == 0)
            return false;

        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (word.charAt(0) == board[i][j]) {
                    if (dfs(i, j, word, board, visited, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean dfs(int x, int y, String word, char[][] board, boolean[][] visited, int idx) {

        if (idx == word.length())
            return true;

        if (x < 0 || y < 0 || x == board.length || y == board[0].length || word.charAt(idx) != board[x][y] || visited[x][y])
            return false;

        visited[x][y] = true;
        boolean res = dfs(x + 1, y, word, board, visited, idx+1) ||
                dfs(x - 1, y, word, board, visited, idx+1) ||
                dfs(x, y + 1, word, board, visited, idx+1) ||
                dfs(x, y - 1, word, board, visited, idx+1);
        visited[x][y] = false;

        return res;
    }
}
