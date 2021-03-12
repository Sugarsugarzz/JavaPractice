package adaptor;

public class Test {
    public static void main(String[] args) {
        // 电脑、适配器、网线
        Computer computer = new Computer();  // 电脑
        Adaptee adaptee = new Adaptee();  // 网线
        Adaptor adaptor = new Adaptor();  // 转接器
        // 1.继承
        computer.net(adaptor);

        // 2.组合
        Adaptor2 adaptor2 = new Adaptor2(adaptee);
        computer.net(adaptor2);
    }
}
