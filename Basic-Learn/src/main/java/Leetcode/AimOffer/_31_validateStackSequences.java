package Leetcode.AimOffer;

/**
 * 31. 栈的压入、弹出序列
 * Medium
 */
public class _31_validateStackSequences {
    public static void main(String[] args) {

    }

    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        int[] stack = new int[1000];
        int popIndex = 0, index = -1;
        for (int i = 0; i < pushed.length; i++) {
            stack[++index] = pushed[i];
            while (index != -1 && stack[index] == popped[popIndex]) {
                index--;
                popIndex++;
            }
        }
        return index == -1;
    }
}
