package com.sugar.aqs_util;

import java.util.concurrent.CountDownLatch;

// 减法计数器
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 在必须要执行的任务中使用
        CountDownLatch countDownLatch = new CountDownLatch(6);  // 总数设为6

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " Go Out!");
                countDownLatch.countDown(); // 数量-1
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();  // 等待计数器归零（即指定数量线程运行结束），然后再向下执行

        System.out.println("Close Door");
    }
}
