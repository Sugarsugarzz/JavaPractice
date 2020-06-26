package Liaoxuefeng_Java._02_Object_Oriented;

import java.util.Arrays;

public class Person {

    // 可以对类成员变量初始化
    protected String name = "Unnamed";
    protected int age = 12;
    protected String[] names;
    // 静态字段
    public static int number;

    /*
    构造方法
    调用必须用 new 操作符。
    如果没有编写构造方法，编译器会自动生成一个默认构造方法，无参数无执行语句。
    如果自定义了构造方法，编译器则不再自动创建默认构造方法。
     */

    public Person() { }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 构造方法可以调用其他构造方法，使用 this(...) 语法（代码复用）
    public Person(String name) {
        this(name, 12);
    }

    public String getName() {
        /*
        this变量：始终指向当前实例，通过this.field可以访问当前实例的字段。
        如果没有命名冲突，则可以省略this。
         */
        return name; // 相当于this.name
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Invalid name");
        }
        this.name = name.trim();
    }

    public void setAge(int age) {
        // 在方法内部，就有机会检查参数设定是否正确，防止设成不合理的值
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("Invalid age value");
        }
        this.age = age;
    }

    /*
    可变参数，相当于数组类型。
    可变参数可以保证无法传入null，传入0个参数时，接收到的是一个空数组而不是null。
     */
    public void setNames(String... names) {
        this.names = names;
    }

    public String[] getNames() {
        return names;
    }

    // 静态方法
    public static void setNumber(int number) {
        Person.number = number;
    }

    public void run() {
        System.out.println("Person.run");
    }

    public String hello() {
        return "Hello, " + name;
    }

    /*
    覆写Object方法，使其更有意义
     */

    @Override
    public String toString() {
        return "Person: name=" + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            // 同为Person类型，且name相同
            Person p = (Person) obj;
            return this.name.equals(p.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
