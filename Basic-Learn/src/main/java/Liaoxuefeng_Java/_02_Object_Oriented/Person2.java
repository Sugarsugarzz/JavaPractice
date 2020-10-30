package Liaoxuefeng_Java._02_Object_Oriented;

/*
 - 抽象类：
 1. 包含抽象方法的类必须声明为抽象类。
 2. 抽象方法没有执行语句。
 3. 抽象类无法实例化。

 适用场景：如果一个父类本身不需要实现功能，仅仅是为了定义方法和字段，让子类去覆写它，则可以声明为抽象类。
 */
public abstract class Person2 {

    public abstract void run();
}
