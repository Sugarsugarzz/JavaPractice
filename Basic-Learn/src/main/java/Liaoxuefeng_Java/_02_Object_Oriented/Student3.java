package Liaoxuefeng_Java._02_Object_Oriented;

public class Student3 implements Person3{

    protected String name;

    public Student3(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(this.name + " run");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void hello() {

    }
}
