package Liaoxuefeng_Java._02_Object_Oriented;

public class Student extends Person {

    /*
    子类将继承父类的所有字段，严禁定义与父类重名的字段。
    所有类都首先继承自Object类。
    Java只允许单继承。
    子类无法访问父类private字段，改为protected，可以被子类访问。
     */
    protected int score;
    protected Book book;

    public Student() {

    }

    public Student(String name, int age, int score) {
        /*
        子类不会继承任何父类的构造方法，只继承字段和方法。
        Java中，任何Class的构造方法，第一行语句必须是调用父类的构造方法。
        如果没有明确调用，则编译器自动加一句super()。
        这样name和age就没有赋值，因此需要显式写一下super()。
         */
        super(name, age);
        this.score = score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    /*
    覆写：在继承关系中，子类重写父类的方法。
    Overload是一个新方法；而Override是方法参数相同，返回值也相同
     */
    @Override
    public void run() {
        System.out.println("Student.run");
    }

    /*
    在子类覆写方法中，可以通过 super 来调用父类被覆写的方法。
     */
    @Override
    public String hello() {
        return super.hello() + "!";
    }
}
