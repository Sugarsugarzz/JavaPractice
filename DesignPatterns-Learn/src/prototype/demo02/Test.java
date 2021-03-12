package prototype.demo02;

import java.util.Date;


public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        // 原型对象
        Date date = new Date();
        Video v1 = new Video("Sugar", date);
        Video v2 = (Video) v1.clone();


        System.out.println("v1=> " + v1);
        System.out.println("v2=> " + v2);

        date.setTime(1121212121);

        System.out.println("v1=> " + v1);
        System.out.println("v2=> " + v2);
        // v1的date变化，v2不变

    }
}
