package Liaoxuefeng_Java._02_Object_Oriented;

import java.util.Arrays;

public class Person {

    private String name;
    private int age;
    private String[] names;

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
}
