package Liaoxuefeng_Java._01_Introduction;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /*
        整数运算超出范围会溢出，但是不会出错，而是得到一个奇怪的结果！！！
        解决方法：换成更大范围的类型，比如int转long
         */
        /*
        移位运算：左移就是乘2，右移就是除以2.
        >>> 是带符号的移位运算。
         */
        int a = 7;
        System.out.println(a >> 1);
        int b = -7;
        System.out.println(b >> 1);
        System.out.println(b >>> 1);
        /*
        浮点数计算存在误差
        比较两个浮点数是否相等时，正确方法是判断两个浮点数之差的绝对值是否小于一个很小的数
         */
        double x = 1.0 / 10;
        double y = 1 - 9.0 / 10;
        System.out.println(x);
        System.out.println(y);
        double r = Math.abs(x - y);
        if (r < 0.00001) {
            System.out.println("相等");
        }
        /*
        浮点数在除数为0不会报错，会返回几个特殊值。
         */
        double d1 = 0.0 / 0;
        double d2 = 1.0 / 0;
        double d3 = -1.0 / 0;
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);
        /*
        布尔运算：短路运算！！！
         */
        /*
        字符串拼接：可以使用 + 连接任意字符串和其他数据类型。
         */
        String s = "Hello" + " " + 122 + " " + '中';
        System.out.println(s);
        /*
        多行字符串：用+号拼接。
        """在Java11后才能用。
         */
        String ss = "first line \n"
                + "second line \n"
                + "end";
        System.out.println(ss);
        /*
        字符串：具有不可变性！！！！变的不是字符串，而是字符串的指向！
        可以指向null和""，但这两个是不同的。
         */

        /*
        数组：1、所有元素初始化为默认值；2、创建后，大小不可改变。
        数组也是一个引用类型，大小变化其实只是指向了新的数组。
         */
        String[] names = {"ABC", "XYZ", "zoo"};
        String sss = names[1];
        names[1] = "cat";
        System.out.println(sss);
        System.out.println(names);
        /*
        格式化输出 %d整数  %f浮点数  %s字符串
         */
        double d = 3.1415926;
        System.out.printf("%.2f", d);
        /*
        输入：从控制台读取
         */
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input your name：");
        String name = scanner.nextLine();
        System.out.print("Input your age：");
        int age = scanner.nextInt();
        System.out.printf("Hi, %s, you are %d years old", name, age);
        /*
        ==运算符：可以判断值类型的变量是否相等；判断引用类型的变量时，==表示引用是否相等，内容相同引用对象不同，依然判断为不相等。
        equals()方法：判断引用类型的变量内容是否相等。
            equals()，若变量为null，会报空指针错误，可以利用短路运算符 &&。
            或者把一定不是null的放在前面。
         */
        String s1 = null;
        if (s1 != null && s1.equals("hello"))
            System.out.println("hello");
        /*
        Switch语句：万万不能漏了break
         */
        int option = 99;
        switch (option) {
            case 1:
                System.out.println("Hello");
                break;
            case 2:
                System.out.println("Hello 2");
                break;
            default:
                System.out.println("Other");
                break;
        }
        /*
        遍历数组
        1、for
        2、for each
        直接打印数组，会打印出数组在JVM的引用地址，
        打印方法：1、for each 2、Java标准库的Arrays.toString()
         */
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(nums));
        /*
        冒泡排序
         */
        int[] ns = {1, 5, 8, 2, 3, 9, 4};
        System.out.println(Arrays.toString(ns));
        for (int i = 0; i < ns.length - 1; i++) {
            for (int j = 0; j < ns.length - 1 - i; j++) {
                if (ns[j] > ns[j+1]) {
                    int tmp = ns[j];
                    ns[j] = ns[j+1];
                    ns[j+1] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(ns));
        /*
        Java标准库内置了排序功能
        Arrays.sort()
        数组元素在内存中的位置没有变化，但是对每个元素的指向发生了变化。
         */
        ns = new int[] {28, 12, 89, 73, 55, 22, 33};
        Arrays.sort(ns);
        System.out.println(Arrays.toString(ns));
        /*
        打印二维数组的方法
        1、两层嵌套for循环
        2、Java标准库的Arrays.deepToString()
         */
        int[][] nss = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
        };
        System.out.println(Arrays.deepToString(nss));
        /*
        命令行参数：根据不同的参数执行不同的代码
         */
        for (String arg: args) {
            if ("-version".equals(arg)) {
                System.out.println("v1.0");
                break;
            }
        }
    }
}
