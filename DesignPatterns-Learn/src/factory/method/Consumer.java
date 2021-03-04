package factory.method;

public class Consumer {
    public static void main(String[] args) {
        Car car = new BenzFactory().getCar();
        Car car2 = new TeslaFactory().getCar();
        car.name();
        car2.name();

        // 新增一个摩拜，无需修改原工厂代码
        Car car3 = new MobaiFactory().getCar();
        car3.name();
    }
}
