package prototype.demo01;

import java.util.Date;

/*
  客户端：  克隆
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        // 原型对象
        Date date = new Date();
        Video v1 = new Video("Sugar", date);
        System.out.println("v1=> " + v1);
        System.out.println("v1=>hash:" + v1.hashCode());

        // v1 克隆 v2
//        Vedio v2 = new Video("Sugar", new Date());
        Video v2 = (Video) v1.clone();
        System.out.println("v2=> " + v2);
        System.out.println("v2=>hash:" + v2.hashCode());

        // 克隆出来的对象和原来是一模一样的（参数），但不是同一个对象，hashcode不同

        // 但存在一个问题，两个对象在内存中引用了同一个Date对象，浅拷贝！
        // 浅拷贝：对象中的基本类型进行值拷贝，引用类型对引用地址进行拷贝
        date.setTime(221312121);
        System.out.println("v1=> " + v1);
        System.out.println("v2=> " + v2);

        // demo02中演示修改clone方法实现深拷贝，一般也可通过序列化和反序列化实现深拷贝
    }
}
