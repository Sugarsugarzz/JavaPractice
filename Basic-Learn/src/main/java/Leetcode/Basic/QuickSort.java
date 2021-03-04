package Leetcode.Basic;

import java.util.Arrays;

/**
 * 快速排序
 *   归并排序将数组分成两个子数组分别排序，并将有序的子数组归并以将整个数组排序；而快速排序将数组排序的方式则是当两个子数组都有序时整个数组也自然有序了
 *   快排递归的将子数组 a[lo..hi]排序，先用 partition() 方法将 a[j] 放到一个合适位置，然后再用递归调用将其他位置的元素排序。
 *   对于 j，a[j]已经位置确定，则 a[lo]..a[j-1]中所有元素不大于a[j]，a[j+1]..a[hi]中所有元素不小于a[j].
 *   切分方法：
 *      一般策略是先随意地取 a[lo] 作为切分元素，即将会被排定的元素。
 *      然后从数组的左端开始向右扫描直到找到一个大于等于它的元素，再从数组的右端开始向左扫描直到找到一个小于等于它的元素，这两个元素是没有排定的，因此交换它们的位置。
 *      如此继续，就可以保证左指针 i 左侧元素都不大于切分元素；右指针 j 的右侧元素都不小于切分元素。
 *      当两指针相遇时，只需将切分元素 a[lo] 与左子数组最右侧元素(a[i]或a[j]，i==j)交换，返回 j 即可。
 */
public class QuickSort {
    public static void sort(int[] arr) {
        if (arr.length == 0)
            return;
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[] arr, int lo, int hi) {
        if (lo >= hi)
            return;
        int j = partition(arr, lo, hi);
        sort(arr, lo, j-1);
        sort(arr, j+1, hi);
    }

    public static int partition(int[] arr, int lo, int hi) {
        int val = arr[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (arr[++i] < val)  if (i == hi)  break;
            while (arr[--j] > val)  if (j == lo)  break;
            if (i >= j)  break;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        arr[lo] = arr[j];
        arr[j] = val;
        return j;
    }
}

class QuickSortTest {
    public static void main(String[] args) {
        int[] arr = {7, 6, 5, 4, 3, 2, 1};
        QuickSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
