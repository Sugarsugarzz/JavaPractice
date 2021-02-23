package com.sugar.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 懒汉式单例
 */
public class LazyMan {

    // 信号量，防止未调用getInstance，直接用两个反射破坏单例
    private static boolean sugar = false;

    private LazyMan() {
        // 三重，防止反射
//        synchronized (LazyMan.class) {
//            if (lazyMan != null) {
//                throw new RuntimeException("不要试图使用反射破坏单例！");
//            }
//        }

        // 更进一步，用信号量
        synchronized (LazyMan.class) {
            if (sugar == false) {
                sugar = true;
            } else {
                throw new RuntimeException("不要试图使用反射破坏单例！");
            }
        }
    }

    private volatile static LazyMan lazyMan;

    // 双重检测锁模式的 懒汉式单例 DCL懒汉式
    public static LazyMan getInstance() {
        if (lazyMan == null) {
            synchronized (LazyMan.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan();  // 不是一个原子性操作
                }
            }
        }
        return lazyMan;
    }

    /**
     * 1. 分配内存空间
     * 2. 执行构造方法，初始化对象
     * 3. 把这个对象指向这个空间
     *
     * 123
     * 132  A
     *      B  // 此时LazyMan还没有完成构造
     */
    // 单线程可以，多线程并发存在问题
//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                LazyMan.getInstance();
//            }).start();
//        }
//    }

    /**
     * 利用反射破坏单例！
     */
    public static void main(String[] args) throws Exception {
        // 三重
//        LazyMan instance = LazyMan.getInstance();

        // final 修改 sugar 属性  ==>>  枚举 ENUM
        Field sugar = LazyMan.class.getDeclaredField("sugar");
        sugar.setAccessible(true);

        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        // 更进一步
        LazyMan instance = declaredConstructor.newInstance();

        sugar.set(instance, false);
        LazyMan instance2 = declaredConstructor.newInstance();



        System.out.println(instance);  // instance不同
        System.out.println(instance2);


    }
}
