package Liaoxuefeng_Java._02_Object_Oriented;

/*
 - 接口：
 1. 比抽象类还要抽象的纯抽象接口。
 2. 字段不能有。
 3. 所有方法默认public abstract，所以一般省略。

 - 接口继承：
 1. 接口继承使用extends。
 2. 相当于扩展了接口的方法。

 适用场景：如果一个抽象类没有字段，且所有方法都是抽象方法。
 */
public interface Person3 extends Hello2{

    /*
    interface不能定义实例字段，但可以有静态字段，且必须为final类型。
     */
    // 编译器自动加上 public static final
    int MALE = 1;
    int FEMALE = 2;

    void run();
    String getName();

    /*
    default方法
        - 当需要给接口新增一个方法时，会涉及修改全部子类。
        - 通过default，子类不必全部修改，只需要在需要覆写的地方去覆写新增方法即可。
     */
    default void run2() {
        System.out.println(getName() + " run");
    }

}
