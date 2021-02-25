package com.sugar.class_loader;

public class Car {
    public static void main(String[] args) {
        // 类是模板，对象是具体的
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();

        System.out.println(car1.hashCode());  // 实例化对象的hashcode不同
        System.out.println(car2.hashCode());
        System.out.println(car3.hashCode());
        // 从实例化对象获取模板类
        Class<? extends Car> aClass1 = car1.getClass();
        Class<? extends Car> aClass2 = car2.getClass();
        Class<? extends Car> aClass3 = car3.getClass();

        System.out.println(aClass1.hashCode());  // 模板类的hashcode相同
        System.out.println(aClass2.hashCode());
        System.out.println(aClass3.hashCode());

        // 从模板类获取ClassLoader
        ClassLoader classLoader = aClass1.getClassLoader();
        System.out.println(classLoader);  // AppClassLoader
        System.out.println(classLoader.getParent());  // ExtClassLoader  jre/lib/ext
        System.out.println(classLoader.getParent().getParent());  // null(BootClassLoader) java程序获取不到（C++写的）  rt.jar

    }
}
