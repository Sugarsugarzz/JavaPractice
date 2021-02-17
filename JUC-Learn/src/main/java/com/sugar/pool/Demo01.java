package com.sugar.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Executors 工具类，三大方法
// 使用了线程池之后，要使用线程池创建线程
public class Demo01 {
    public static void main(String[] args) {
        // Method 1：单个线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // Method 2：创建一个固定的线程池大小
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // Method 3：可伸缩的，遇强则强，遇弱则弱
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池用完，需要关闭线程池
            threadPool.shutdown();
        }
    }
}
