package com.sugar.aqs_util;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

// 抢车位，限流！
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 设置线程数量（车位数量）
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    // acquire() 得到
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 抢到车位。");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + " 离开车位。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // release() 释放
                    semaphore.release();
                }

            }, String.valueOf(i)).start();
        }
    }
}
