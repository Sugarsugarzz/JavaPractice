package factory.simple;

/**
 * 静态工厂模式（简单工厂模式）
 *    方法都是静态的，通过传入不同参数，返回不同实例
 *    弊端：增加一个新的产品，必须要修改工厂代码！
 * 但在大多数情况下，使用简单工厂模式，为了遵循开闭原则，需要付出许多代价。
 */
public class CarFactory {

    // 方法一：违反开闭原则
    public static Car getCar(String car) {
        if (car.equals("奔驰")) {
            return new Benz();
        } else if (car.equals("特斯拉")) {
            return new Tesla();
        } else {
            return null;
        }
    }

    // 方法二：优化，但还是在修改工厂类
    public static Car getBenz() {
        return new Benz();
    }
    public static Car getTesla() {
        return new Tesla();
    }



}
