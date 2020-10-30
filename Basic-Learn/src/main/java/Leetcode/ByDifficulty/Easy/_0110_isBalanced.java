package Leetcode.ByDifficulty.Easy;

/**
 * 
 * title: 平衡二叉树
 * 
 * description：给定一个二叉树，判断它是否是高度平衡的二叉树。。
 * 
 * limit:本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * 
 * label：树
 * 
 */

public class _0110_isBalanced {

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
		
		boolean result = isBalanced(root);
		System.out.println(result);
		
	}
	
	public static boolean isBalanced(TreeNode root) {	
		
		return depth(root) != -1;
    }
	
	private static int depth(TreeNode root) {
		
		if (root == null) return 0;
		int left = depth(root.left);
		if (left == -1) return -1;
		int right = depth(root.right);
		if (right == -1) return -1;
		
		return Math.abs(right - left) < 2 ? Math.max(right, left) : -1;
	}
}
