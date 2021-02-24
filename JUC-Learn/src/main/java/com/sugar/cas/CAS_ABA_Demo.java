package com.sugar.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.ReentrantLock;

public class CAS_ABA_Demo {
    public static void main(String[] args) {
//        AtomicInteger atomicInteger = new AtomicInteger(2020);

        // 对于平时写的SQL：乐观锁！
        // Java  ==>>  原子引用！
        // ============  捣乱的线程  ============
//        System.out.println(atomicInteger.compareAndSet(2020, 2021));
//        System.out.println(atomicInteger.get());
//
//        System.out.println(atomicInteger.compareAndSet(2021, 2020));
//        System.out.println(atomicInteger.get());

        // ============  捣乱的线程  ============
//        System.out.println(atomicInteger.compareAndSet(2020, 6666));
//        System.out.println(atomicInteger.get());

        // 原子引用版
        // 同时验证期望值和版本号
        // 注：如果泛型是包装类，注意对象的引用问题
        AtomicStampedReference<Integer> atomicInteger = new AtomicStampedReference<>(1, 1);

        new Thread(() -> {
            int stamp = atomicInteger.getStamp();  // 获取版本号
            System.out.println("A1=> " + stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Version + 1
            System.out.println(atomicInteger.compareAndSet(1, 2,
                    atomicInteger.getStamp(), atomicInteger.getStamp() + 1));
            System.out.println("A2=> " + atomicInteger.getStamp());

            System.out.println(atomicInteger.compareAndSet(2, 1,
                    atomicInteger.getStamp(), atomicInteger.getStamp() + 1));
            System.out.println("A3=> " + atomicInteger.getStamp());
        }, "A").start();

        // 和乐观锁的原理相同
        new Thread(() -> {
            int stamp = atomicInteger.getStamp();  // 获取版本号
            System.out.println("B1=> " + stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicInteger.compareAndSet(1, 5,
                    stamp, stamp + 1));
            System.out.println("B2=> " + atomicInteger.getStamp());
        }, "B").start();
    }
}
