package Leetcode.ByTags.Tree;

/**
 * 104. 二叉树的最大深度
 * Easy
 */

public class _0104_maxDepth {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        int res = maxDepth(root);
        System.out.println(res);
    }

    public static int maxDepth(TreeNode root) {

        // 深度优先搜索，递归
        if (root == null)
            return 0;
        int left_dep = maxDepth(root.left);
        int right_dep = maxDepth(root.right);

        return Math.max(left_dep, right_dep) + 1;
    }
}
