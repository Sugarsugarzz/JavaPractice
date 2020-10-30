package SwordToOffer;

public class Solution_1 {

    /**
     * 题目：在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
     *      每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     *
     * 解析：从左下角元素往上查找，右边元素是比这个元素大，上边是的元素比这个元素小。于是，target比这个元素小就往上找，比这个元素大就往右找。
     *      如果出了边界，则说明二维数组中不存在target元素。
     */


    public static boolean Find(int target, int[][] array) {

        int rows = array.length;
        int cols = array[0].length;
        int i = rows - 1;
        int j = 0;

        while (i >= 0 && j < cols) {
            if (target > array[i][j])
                j++;
            else if (target < array[i][j])
                i--;
            else
                return true;
        }
        return false;
    }


    public static void main(String[] args) {


        int[][] array= {
                {1, 2, 3},
                {3, 4, 5},
                {5, 6, 7}
        };

        System.out.println(Find(3, array));

    }
}
