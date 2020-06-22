package Liaoxuefeng_Java._01_Introduction;

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

    }
}
