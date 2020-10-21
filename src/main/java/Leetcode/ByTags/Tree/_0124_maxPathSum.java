package Leetcode.ByTags.Tree;

/**
 * 124. 二叉树中的最大路径和（可从任一点出发）
 * Hard
 */

public class _0124_maxPathSum {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(maxPathSum(root));
    }

    static int max = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        /*
        left -> root -> right 这种情况无法做递归
        root -> left | root -> right 可以递归
         */
        if (root == null)  return 0;
        dfs(root);
        return max;
    }

    public static int dfs(TreeNode node) {
        if (node == null)  return 0;
        // 计算左右分支最大值，如果为负数则不选择
        int leftMax = Math.max(dfs(node.left), 0);
        int rightMax = Math.max(dfs(node.right), 0);
        // 左右最大值与加上root的值作比较
        max = Math.max(max, node.val + leftMax + rightMax);
        return node.val + Math.max(leftMax, rightMax);
    }


    /**
     * 从根节点出发最大路径和的算法（本题可从任意点出发）
     */
    static int maxFromRoot = 0;

    public static int maxPathSumFromRoot(TreeNode root) {
        dfsFromRoot(root, 0);
        return maxFromRoot;
    }

    private static void dfsFromRoot(TreeNode root, int val) {
        if (root == null) { maxFromRoot = Math.max(maxFromRoot, val);  return; }
        dfsFromRoot(root.left, val + root.val);
        dfsFromRoot(root.right, val + root.val);
    }
}
