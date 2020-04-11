package Leetcode.ByDifficulty.easy;

/**
 * 
 * title: 二叉树的最大深度
 * 
 * description：给定一个二叉树，找出其最大深度。
 * 			二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 
 * limit:
 * 
 * label：二叉树
 * 
 */

public class _0104_maxDepth {

	public static void main(String[] args) {
		// 测试
		TreeNode a1 = new TreeNode(3);
		TreeNode b1 = new TreeNode(5);
		TreeNode b2 = new TreeNode(10);
		TreeNode c1 = new TreeNode(20);
		TreeNode c2 = new TreeNode(15);
		a1.left = b1;
		a1.right = b2;
		b1.left = c1;
		b1.right = c2;
		
		int depth = maxDepth(a1);
		System.out.println(depth);
	}
	
	// 递归法
	public static int maxDepth(TreeNode root) {
		
		if (root == null)
			return 0;
		int left_height = maxDepth(root.left);
		int right_height = maxDepth(root.right);
		
		return Math.max(left_height, right_height) + 1;
	}
}
