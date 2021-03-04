package factory.simple;

public class Consumer {
    public static void main(String[] args) {
        // 1. 原来的方式
        // 需要去了解接口、所有的实现类！
//        Car car = new Benz();
//        Car car2 = new Tesla();

        // 2. 使用工厂创建工厂方式
        Car car = CarFactory.getCar("奔驰");
        Car car2 = CarFactory.getCar("特斯拉");
        car.name();
        car2.name();

        // 缺点：再增加一个大众，需要修改工厂代码，违反了开闭原则
    }
}
