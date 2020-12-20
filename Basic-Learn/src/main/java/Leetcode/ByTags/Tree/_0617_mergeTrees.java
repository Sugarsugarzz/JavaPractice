package Leetcode.ByTags.Tree;

/**
 * 617. 合并二叉树
 * Easy
 */

public class _0617_mergeTrees {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(3);
        t1.right = new TreeNode(2);
        t1.left.left = new TreeNode(5);

        TreeNode t2 = new TreeNode(2);
        t2.left = new TreeNode(1);
        t2.right = new TreeNode(3);
        t2.left.right = new TreeNode(4);
        t2.right.right = new TreeNode(7);

        System.out.println(mergeTrees(t1, t2).val);
    }

    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

        if (t1 == null)  return t2;
        if (t2 == null)  return t1;

        TreeNode root = new TreeNode(t1.val + t2.val);
        root.left = mergeTrees(t1.left, t2.left);
        root.right = mergeTrees(t1.right, t2.right);
        return root;
    }
}
