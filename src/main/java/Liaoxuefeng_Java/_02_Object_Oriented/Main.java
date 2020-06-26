package Liaoxuefeng_Java._02_Object_Oriented;

import javax.swing.*;
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
        引用类型参数传递：调用方和接收方指向的是同一个对应对象，任意一方修改都会对这个对象做修改，影响到对方。
                    （以上是String数组的情况，String如果另外赋值是不变的，即它指向了新的String，而类成员变量指向的String没变。）
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
        /*
        利用构造方法创建实例
         */
        Person person = new Person("Xiao Ming", 15);
        System.out.println(person.getName());
        System.out.println(person.getAge());

        // 访问protected字段
        Student s = new Student();
        System.out.println(s.age);

        /*
        向上转型：把一个子类类型安全地变为父类类型的赋值。
        可以将一个引用对象，赋值给继承树中的某个父类。
        它只保留申明对象所拥有的字段和方法。
         */
        Person pp = new Student();
        Student student = new Student();
        pp = student;
        Object o1 = new Person();
        Object o2 = new Student();

        /*
        向下转型：将一个父类强制转型为子类类型。
        p1转s1成功，但p2转s2失败。
        原因：因为p1本就是Student，而p2是Person，缺少Student的功能，因此无法强制转为Student。
         */
        Person p1 = new Student();
        Person p2 = new Person();
        Student s1 = (Student) p1;
        //Student s2 = (Student) p2;

        /*
        为了避免向下转型出错，可以利用 instanceof 操作符，先判断一个实例是否属于某种类型
         */
        Person p3 = new Student();
        if (p3 instanceof Student) {
            Student s3 = (Student) p3;
        }

        /*
        Java的实例方法调用时基于运行时的实际类型的动态调用，而非变量的声明类型。
        例子：一个实际类型为 Student，引用类型为 Person 的变量，调用是Student的run()方法，
         */
        Person p4 = new Student();
        p4.run();

        /*
        多态：针对某个类型的方法调用，其真正执行的方法取决于运行时期实际类型的方法。
            个人理解：超出申明类型的方法没法调用，所以执行的方法只针对当前类型覆写父类的方法。
        多态允许添加更多类型的子类实现功能扩展，却不需要修改基于父类的代码。
         */
        Income[] incomes = new Income[] {
                new Income(3000),
                new Salary(7500),
                new StateCouncilSpecialAllowance(15000)
        };
        System.out.println(totalTax(incomes));

        /*
        覆写Object方法：
            - toString()：输出为String。
            - equals()：判断两个instance是否逻辑相等。
            - hashCode()：计算一个instance的哈希值。
         */
        Person ppp = new Person("July");
        System.out.println(ppp.toString());

        /*
        在子类覆写方法调用父类被覆写的方法
         */
        Student sss = new Student("June", 15, 80);
        System.out.println(sss.hello());

        /*
        final 关键字
            - final修饰的方法不能被覆写（Override）；
            - final修饰的类不能被继承；
            - final修饰的字段不能被修改，只能在构造方法中初始化。
         */

        /*
        抽象类
         */
        Person2 pppp = new Student2();
        pppp.run();

        /*
        面向对象编程：尽量引用高层类型，而避免引用实际子类型的方式。
          例：通过引用抽象类对其进行方法调用，而不用关心Person2类型的具体子类型。
          本质：
            - 上层代码只定义规范
            - 不需要子类就可以实现业务逻辑。
            - 具体的业务逻辑由不同的子类实现，调用者无需关心。
         */
        Person2 ppppp = new Student2();
        ppppp.run();

        /*
        接口
        - Java中，一个类只能继承自另一个类，不能从多个类继承，但一个类可以实现多个interface、
         */

        /*
        静态字段（static fields）：无论修改哪个实例的静态字段，所有实例的静态字段都将被修改。
            - 推荐用类名访问静态字段。
        静态方法（static methods）：同样无需实例变量，直接通过类名调用。
            - 静态方法属于Class不属于实例，所以方法内部无法访问this变量，也无法访问实例字段，只能访问静态字段。
            - 通过实例变量可以调用静态方法，但只是编译器自动将实例改为类名而已。
         */
        Person hua = new Person();
        Person hong = new Person();
        hua.number = 88;
        System.out.println(ming.number);
        System.out.println(hua.number);
        System.out.println(Person.number); // 推荐
        Person.setNumber(100);
        System.out.println(Person.number);

        /*
        包作用域：位于同一个包的类，可以访问包作用域的字段和方法，而不需要public、protected、private修饰。
        编译器遇到简单类名的查找顺序：
            - 在当前package查找这个Class
            - 在import的包里查找这个Class
            - 在java.lang包查找这个Class
         */

        /*
        为了避免名字冲突，要确定唯一的包名。
        推荐：使用倒置的域名来确保唯一性。
        例：
            - org.apache
            - com.liaoxuefeng.sample
         */

        /*
        作用域
            - 不确定是否需要public，就不声明为public，尽可能减少对字段和方法的暴露。
            - 将方法定义为package域有助于测试。
            - 一个.java文件中只能包含一个public类，且文件与类名需相同。
         */

        /*
        classpath
            JVM通过classpath决定搜索Class的路径和顺序。
            默认为.（当前目录）
            不推荐设置系统环境变量classpath，推荐通过-cp命令传入。

        jar包：相当于目录，可以包含很多.class文件。
            制造jar包：压缩，改后缀为jar。
            jar包还可以包含一个特殊的 /META-INF/MANIFEST.MF 文件。
         */

    }

    public static double totalTax(Income... incomes) {

        double total = 0;
        for (Income income: incomes) {
            total += income.getTax();
            System.out.println(income.getTax());
        }
        return total;
    }

}
