package com.sugar.volatile_test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 2、不保证原子性
 * 加了 volatile，num 依然达不到 20000，只有加 synchronized 或 lock锁
 */
public class VDemo02 {

    // volatile 不保证原子性
    // 使用原子类！
//    private volatile static int num = 0;
    private volatile static AtomicInteger num = new AtomicInteger();

    public static void add() {
//        num++;  // 不是一个原子性操作
        num.getAndIncrement();  // AtomicInteger + 1 方法  CAS

    }

    public static void main(String[] args) {
        // 理论上 num 为 20000
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        };

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " num = " + num);
    }
}
