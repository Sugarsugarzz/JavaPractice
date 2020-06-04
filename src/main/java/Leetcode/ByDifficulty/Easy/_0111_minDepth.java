package Leetcode.ByDifficulty.Easy;

/**
 * 
 * title: 二叉树的最小深度
 * 
 * description：给定一个二叉树，找出其最小深度。。
 * 
 * limit:  。
 * 
 * label：树
 * 
 */

public class _0111_minDepth {

	public static void main(String[] args) {
		// 测试
		TreeNode root = new TreeNode(1);
		TreeNode l1 = new TreeNode(1);
		TreeNode r1 = new TreeNode(1);
		TreeNode l2 = new TreeNode(1);
		TreeNode l3 = new TreeNode(1);
		
		root.left = l1;
		root.right = r1;
		l1.left = l2;
		l2.left = l3;
		
		int result = minDepth(root);
		System.out.println(result);
		
	}
	// 递归
	public static int minDepth(TreeNode root) {
		// 根节点为空，返回0
		if (root == null) return 0;
		// 叶子节点，返回1
		if (root.left == null && root.right == null) return 1;
		
		int left = minDepth(root.left);
		int right = minDepth(root.right);
		// 左右子树其中一个为空，取有节点的那边
		if (root.left == null || root.right == null) return left + right + 1;
		
		return Math.min(left, right) + 1;
			
			
	}
}
