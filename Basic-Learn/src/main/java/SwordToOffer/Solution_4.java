package SwordToOffer;

import java.util.Arrays;

public class Solution_4 {

    /**
     * 题目：输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     *      例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
     *
     * 解析：递归构建左右子树
     *
     */


    public static TreeNode reConstructBinaryTree(int [] pre,int [] in) {

        TreeNode node = new TreeNode(-1);

        if (pre.length == 0 || in.length == 0 || pre.length != in.length)
            return null;

        for (int i = 0; i < pre.length; i++)
            if (pre[0] == in[i]) {
                node.val = pre[0];
                //Arrays.copyOfRange 从原数组构造一个新数组，包括from不包括to
                node.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1),
                        Arrays.copyOfRange(in, 0, i));
                node.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length),
                        Arrays.copyOfRange(in, i + 1, in.length));
                break;
            }

        return node;
    }

    public static void main(String[] args) {

        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};

        TreeNode node = new TreeNode(-1);
        node = reConstructBinaryTree(pre, in);

    }

}




//Definition for binary tree
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x; }
}
