package Leetcode.AimOffer;

/**
 * 33. 二叉搜索树的后序遍历索引
 * Medium
 */
public class _33_verifyPostorder {
    public static void main(String[] args) {
        int[] postorder = {1, 3, 2, 6, 5};
        System.out.println(verifyPostorder(postorder));
    }

    public static boolean verifyPostorder(int[] postorder) {
        // 根据后序遍历特性，找到根节点的位置
        // 根据二叉搜索树的特性，找到左右子树的位置
        return recur(postorder, 0, postorder.length - 1);
    }

    public static boolean recur(int[] postorder, int left, int right) {
        if (left >= right)  return true;

        int index = left;
        while (postorder[index] < postorder[right])  index++;
        int m = index;
        while (postorder[index] > postorder[right])  index++;
        return index == right && recur(postorder, left, m - 1) && recur(postorder, m, right - 1);
    }
}
