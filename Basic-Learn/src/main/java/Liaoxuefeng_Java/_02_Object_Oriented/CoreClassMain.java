package Liaoxuefeng_Java._02_Object_Oriented;


import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.util.StringJoiner;

public class CoreClassMain {

    public static void main(String[] args) throws IntrospectionException {

        /*
        String
            - 内部由char[]数组实现。
            - 不可变性：通过内部的 private final char[]字段，及没有修改的char[]的方法实现。
         */

        // 字符串内容比较，必须使用equals()方法。
        String s1 = "hello";
        String s2 = "HELLO".toLowerCase();
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

        // 忽略大小写比较
        System.out.println(s1.equalsIgnoreCase(s2));

        // 包含子串
        System.out.println(s1.contains("ll"));

        // 搜索子串
        System.out.println(s1.indexOf("l"));
        System.out.println(s1.lastIndexOf("l"));
        System.out.println(s1.startsWith("he"));
        System.out.println(s1.endsWith("lo"));

        // 提取子串（索引号从0开始）
        System.out.println(s1.substring(2));
        System.out.println(s1.substring(2, 4)); // 包含2，但不包含4（左开右闭）

        // 去除首尾空白符
        String s3 = "  \t\nHello\r  ";
        System.out.println(s3.trim());
        // System.out.println(s3.strip());  // Java11引入strip()，能够处理中文空白字符

        // 判断字符串是否为空和空白字符串
        System.out.println("".isEmpty());
        // System.out.println("  \n".isBlank());  // Java11引入

        // 替换子串
        System.out.println(s1.replace("ll", "--"));
        String s4 = "A,,B;C ,D";
        // Java中使用正则，使用\\转义才能得到\
        System.out.println(s4.replaceAll("[\\,\\;\\s]+", ","));

        // 分割字符串
        s4 = "A,B,C,D";
        String[] ss = s4.split(",");
        System.out.println(Arrays.toString(ss));

        // 拼接字符串
        String[] arr = {"A", "B", "C"};
        String s = String.join("***", arr);
        System.out.println(s);

        // 格式化字符串
        // System.out.println("Hi %s, age is %d".formatted("June", 12));  // 反正不是Java8的写法
        System.out.println(String.format("Hi %s, age is %d", "June", 12));

        // 其他类型转字符串
        System.out.println(String.valueOf(44.44));
        System.out.println(String.valueOf(true));

        // 字符串转其他类型
        int n1 = Integer.parseInt("123");
        boolean b1 = Boolean.parseBoolean("False");

        // 将字符串对应的系统变量转换为Integer
        System.out.println(Integer.getInteger("java.version"));

        // 与char[]相互转换
        char[] cs = "Hello".toCharArray();
        String sss = new String(cs);

        // 类内部不应直接引用外部传入数组，而应该克隆clone().

        /*
        StringBuilder：高效拼接字符串
            - String虽然可以用 + 拼接，但每次都会创建新的字符串。
            - 这些字符串都是临时对象，不但浪费内存，还影响GC效率。
            - StringBuilder是一个可变对象，可以预分配缓冲区，新增字符不会创建新的临时对象。
            - 可以进行链式操作。
                原因：查看源码，append()方法会返回this，就可以不断调用自身的其他方法。
            - 对于普通的字符串 + 操作，并不需要StringBuilder，编译器自动将多个连续 + 操作优化为StringBuilder。
            - StringBuffer是StringBuilder的线程安全版本，通过同步来保证多线程操作StringBuffer也是安全的，但是同步导致执行速度下降。
         */
        StringBuilder sb = new StringBuilder();
        sb.append("Hello")
                .append(" World")
                .append("!");
        System.out.println(sb.toString());

        /*
        StringJoiner：用分隔符拼接数组
            - String.join()更方便一些
         */
        String[] names = {"1", "2", "3", "4"};
        // 指定分隔符，也可以指定开头和结尾
        StringJoiner sj = new StringJoiner(",", "Hello ", "！");
        for (String name: names) {
            sj.add(name);
        }
        System.out.println(sj.toString());

        /*
        包装类型
            - Java为每种基本类型都提供了对应的包装类型。
            - 自动装箱、拆箱只发生在编译阶段，目的是少写代码。
            - 装箱拆箱会影响代码的执行效率，拆箱可能发生 NullPointerException。
            - 所有包装类型都是不变类。
         */
        int i = 100;
        Integer n = Integer.valueOf(i);  // int -> Integer 自动装箱
        int x = n.intValue(); // Integer -> int 自动拆箱
        // 创建Integer的两种方法
        Integer n2 = new Integer(100);  //总是在创建新的Integer实例
        Integer n3 = Integer.valueOf(100);  // 这种方法更好，把内部优化交给Integer的实现者去做
        // 无符号转换
        byte xx = -1; //11111111
        System.out.println(Byte.toUnsignedInt(xx));

        /*
        JavaBean
            特征：
                - 若干private实例字段
                - 通过public方法来读写实例字段（Getter、Setter）
            - Java核心库的Introspector，可以枚举一个JavaBean的所有属性。
         */
        BeanInfo info = Introspector.getBeanInfo(Person.class);
        for (PropertyDescriptor pd: info.getPropertyDescriptors()) {
            System.out.println(pd.getName());
            System.out.println("  " + pd.getReadMethod());
            System.out.println("  " + pd.getWriteMethod());
        }

        /*
        枚举类
            - 用enum来定义枚举类。
            - 编译器能自动检查某个值在枚举的集合内，且不同用途的枚举需要不同的类型来标记，不能混用。
            - 引用类型。
            优点：
            1. enum常量自带类型信息
            2. 不可能引用到非枚举的值
            3. 不同类型的枚举不能相互比较或赋值
         */
        Weekday day = Weekday.SUN;
        if (day == Weekday.SUN || day == Weekday.SAT)
            System.out.println("At home");
        else
            System.out.println("At office");
        // enum比较
        // enum类型，虽然是引用类型，但每个常量在JVM中只有一个唯一实例，所以可以用==比较。

        // 自带方法
        // 查看常量名
        String str = Weekday.SUN.name();
        // 返回定义常量的顺序（改变顺序，返回值也会变化）
        int nn = Weekday.SUN.ordinal();

        // 防止ordinal返回顺序不同，给每个枚举常量添加字段
        Weekday dayy = Weekday.SUN;
        if (dayy.dayValue == 0)
            System.out.println("Today is " + dayy.toString());

        /*
        记录类
            - Java14 引入 Record类。
         */

        /*
        BigInteger
            - 可以表示任意大小的整数。
            - 用软件来模拟一个超过long型的大整数。
            - 内部用一个int[]数组来实现。
            - 做运算时，只能使用实例方法。
         */
        BigInteger bi = new BigInteger("12345678900000");
        System.out.println(bi.add(bi));

        /*
        BigDecimal
            - 可以表示一个任意大小且精度完全准确的浮点数。
            - 常用于财务计算。
            - 必须使用compareTo()比较，而不能使用equals()。
         */
        BigDecimal bd = new BigDecimal("123.3456");
        System.out.println(bd.multiply(bd));

        /*
        常用工具类
            - Math
            - Random
            - SecureRandom
         */
        // 绝对值
        Math.abs(-100);
        // 取最大最小值
        Math.min(100, 99);
        Math.max(1.1, 2.2);
        // x的y次方
        Math.pow(2, 10);
        // 根号下x
        Math.sqrt(2);
        // e的x次方
        Math.exp(2);
        // 以e为底的对数
        Math.log(4);
        // 以10为底的对数
        Math.log10(3);
        // 三角函数
        Math.sin(3.14);
        // 数学常量
        System.out.println(Math.PI);
        System.out.println(Math.E);
        // 生成范围随机数（10到50内）
        double xxx = Math.random();   // 范围是[0,1]
        double min = 10, max = 50;
        double y = xxx * (max = min) + min;
        long nnn = (long) y;
        System.out.println(nnn);

        // 创建Random实例，不给定种子，就使用系统时间戳作为种子。
        // 如果指定一个种子，就会得到完全确定的随机数序列。
        Random r = new Random(12345);
        for (int ii = 0; ii < 10; ii++)
            // 每次都相同
            System.out.print(r.nextInt(100) + "  ");

        // 安全、不可预测的随机数，必须用SecureRandom来生成
        SecureRandom sr = new SecureRandom();
        for (int ii = 0; ii < 10; ii++)
            // 每次都不同
            System.out.print(sr.nextInt(100) + "  ");



    }
}

/*
枚举类
 */
enum Weekday {
    // 可以给每个枚举常量添加字段

    SUN(0, "星期天"), MON(1, "星期一"),
    TUE(2, "星期一"), WED(3, "星期一"),
    THU(4, "星期一"), FRI(5, "星期一"),
    SAT(6, "星期一");

    public final int dayValue;
    private final String chinese;

    private Weekday(int dayValue, String chinese) {
        this.dayValue = dayValue;
        this.chinese = chinese;
    }

    @Override
    public String toString() {
        return this.chinese;
    }
}
