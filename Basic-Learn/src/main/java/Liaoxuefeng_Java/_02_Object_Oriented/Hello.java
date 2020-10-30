package Liaoxuefeng_Java._02_Object_Oriented;

public class Hello {

    /*
    方法重载（Overload）：一系列方法，同名，参数不同，功能相似。
     */
    public void hello()  {
        System.out.println("H");
    }

    public void hello(String name)  {
        System.out.println("H，" + name);
    }

    public void hello(String name, int age)  {
        System.out.println("H，" + name + " " + age);
    }

}
