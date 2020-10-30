package Liaoxuefeng_Java._01_Introduction;

public class Hello {

    // 文件名和类名保持一致
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        /*
        八个基本类型：byte、short、int、long、float、double、char、boolean
        byte占1个字节。
        整型的首位bit是符号位。
         */
        double d = -1.79e308;
        System.out.println(d);
        /*
        引用类型
         */
        String a = "haha";
        String b = a;
        b = "lalla";
        System.out.println(a);
        System.out.println(b);
    }
}
