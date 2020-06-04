package Leetcode.ByDifficulty.Easy;

/**
 * 
 * title: 将有序数组转换为二叉搜索树
 * 
 * description：将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * 
 * limit:本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * 
 * label：二叉树
 * 
 */

public class _0108_sortedArrayToBST {

	public static void main(String[] args) {
		// 测试
		int[] nums = {1, 2, 3, 4, 5};
		
		TreeNode result = sortedArrayToBST(nums);
		System.out.println(result.val);
	}
	
	public static TreeNode sortedArrayToBST(int[] nums) {
			
		return sortedArrayToBST(nums, 0, nums.length);
	}
	
	public static TreeNode sortedArrayToBST(int[] nums, int start, int end) {
		
		if (start == end)
			return null;
		
		int mid = (start + end) >>> 1;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = sortedArrayToBST(nums, start, mid);
		root.right = sortedArrayToBST(nums, mid+1, end);
		return root;
	}
	
	

}
