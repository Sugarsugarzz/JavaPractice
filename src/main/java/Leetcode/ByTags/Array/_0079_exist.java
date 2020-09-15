package Leetcode.ByTags.Array;

/**
 * 79. 单词搜索
 * Medium
 */
public class _0079_exist {

    // 标记状态位
    private boolean[][] marked;
    private int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    private int m, n;
    private String word;
    private char[][] board;


    public boolean exist(char[][] board, String word) {

        if (board.length == 0)
            return false;
        m = board.length;
        n = board[0].length;
        marked = new boolean[m][n];
        this.word = word;
        this.board = board;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(i, j, 0))
                    return true;
            }
        }

        return false;
    }

    private boolean dfs(int i, int j, int idx) {
        if (idx == word.length() - 1)
            return board[i][j] == word.charAt(idx);

        if (board[i][j] == word.charAt(idx)) {
            marked[i][j] = true;
            for (int k = 0; k < 4; k++) {
                int newX = i + direction[k][0];
                int newY = j + direction[k][1];
                if (isInArea(newX, newY) && !marked[newX][newY]) {
                    if (dfs(newX, newY, idx+1)) {
                        return true;
                    }
                }
            }
            marked[i][j] = false;
        }
        return false;
    }

    private boolean isInArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public static void main(String[] args) {

        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "FCS";

        System.out.println(new _0079_exist().exist(board, word));
    }
}
