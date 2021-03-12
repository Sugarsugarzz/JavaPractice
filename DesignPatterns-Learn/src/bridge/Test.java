package bridge;

public class Test {
    public static void main(String[] args) {
        // 苹果笔记本
        Computer computer = new LaptopComputer(new Apple());
        computer.info();
        // 联想台式机
        Computer computer1 = new DesktopComputer(new Lenovo());
        computer1.info();
    }
}
