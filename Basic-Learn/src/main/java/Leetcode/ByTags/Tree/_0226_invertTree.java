package Leetcode.ByTags.Tree;

/**
 * 226. 翻转二叉树
 * Easy
 */

public class _0226_invertTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        TreeNode res = invertTree(root);
        System.out.println(res.left.val);
        System.out.println(res.left.left.val);
        System.out.println(res.left.right.val);

    }

    public static TreeNode invertTree(TreeNode root) {

        // 递归，交换左右子树即可
        if (root == null)
            return null;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
