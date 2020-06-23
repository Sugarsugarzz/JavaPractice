package Liaoxuefeng_Java._02_Object_Oriented;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        /*
        各实例独自拥有一份数据，互不干扰。
         */
        Person ming = new Person();
        ming.setName("Xiaoming");
        ming.setAge(12);
        System.out.println(ming.getName());
        // 可变数组类型
        ming.setNames("Hello", "World");
        /*
        基本类型参数传递：是调用方值的复制，后续修改互不影响。
        引用类型参数传递：
         */
        // 基本类型：不变化
        Person p = new Person();
        int n = 15;
        p.setAge(n);
        System.out.println(p.getAge());
        n = 20;
        System.out.println(p.getAge());
        // 引用类型：变化
        String[] fullname = {"Homer", "Simpon"};
        p.setNames(fullname);
        System.out.println(Arrays.toString(p.getNames()));
        fullname[0] = "Bart";
        System.out.println(Arrays.toString(p.getNames()));
    }

}
