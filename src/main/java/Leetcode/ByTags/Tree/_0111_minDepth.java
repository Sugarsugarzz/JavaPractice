package Leetcode.ByTags.Tree;

/**
 * 111. 二叉树的最小深度
 * Easy
 */

public class _0111_minDepth {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        int res = minDepth(root);
        System.out.println(res);
    }

    public static int minDepth(TreeNode root) {

        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;

        int min_depth = Integer.MAX_VALUE;
        // 一定要取有节点的
        if (root.left != null)
            min_depth = Math.min(minDepth(root.left), min_depth);
        if (root.right != null)
            min_depth = Math.min(minDepth(root.right), min_depth);

        return min_depth + 1;
    }
}
