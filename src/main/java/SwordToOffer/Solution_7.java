package SwordToOffer;

public class Solution_7 {

    /**
     * 题目：大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
     *      n<=39
     *
     * 解析：Fibonacci(n) = Fibonacci(n-1) + Fibonacci(n-2)
     *
     */


    public static int Fibonacci(int n) {

        //Solution1
//        if (n <= 1)
//            return n;
//        else
//            return Fibonacci(n - 1) + Fibonacci(n - 2);


        //Solution2 Force
//        int[] ns = {0,1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,6765,
//                10946,17711,28657,46368,75025,121393,196418,317811,514229,832040,1346269,
//                2178309,3524578,5702887,9227465,14930352,24157817,39088169,63245986};
//        return ns[n];


        //Solution3 动态规划 （Best）
        if (n < 0)
            return -1;
        else if (n == 0)
            return 0;
        else if (n == 1)
            return 1;

        int one = 0;
        int two = 1;
        int sum = 0;
        for (int i = 2; i <= n; ++ i) {
            sum = one + two;
            one = two;
            two = sum;
        }

        return sum;
    }




    public static void main(String[] args) {

        System.out.println(Fibonacci(39));
    }
}
