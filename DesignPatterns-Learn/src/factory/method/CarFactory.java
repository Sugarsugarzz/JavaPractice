package factory.method;

import factory.method.Car;

/**
 * 工厂方法模式
 *    为每个实例都创建了一个工厂
 *    扩展自由
 */
public interface CarFactory {
    Car getCar();
}
