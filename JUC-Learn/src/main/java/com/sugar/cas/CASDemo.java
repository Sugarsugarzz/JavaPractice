package com.sugar.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {

    // CAS  compareAndSet：比较并交换
    // CAS 是 CPU 的并发原语。
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);

        // public final boolean compareAndSet(int expect, int update)
        // 如果达到期望值，则更新；否则不更新
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());
//        atomicInteger.getAndIncrement();

        // 不再更新，false
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());
    }
}
