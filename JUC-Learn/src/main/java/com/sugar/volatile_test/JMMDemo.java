package com.sugar.volatile_test;

import java.util.concurrent.TimeUnit;

/**
 * 1、保证可见性
 * Thread 1不知道主内存的值已经被修改了，所以即使 num = 1，Thread 1中的循环依然不会停止。
 * 引入 Volatile 解决该问题，保证可见性。
 */
public class JMMDemo {
    // 不加 volatile 程序就会死循环
    // 加 volatile 可以保证可见性
    private volatile static int num = 0;

    public static void main(String[] args) { // main

        new Thread(() -> {  // Thread 1  对主内存的变化不知道
            while (num == 0) {

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num = 1;

        System.out.println(num);
    }
}
