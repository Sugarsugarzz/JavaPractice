package com.sugar.pool;

import java.util.concurrent.*;

// 自定义线程池
// 银行取款场景复现
/*
四大拒绝策略：
    - AbortPolicy()：直接拒绝，抛出异常
    - CallerRunsPolicy()：哪来的回哪去，main线程调用的，就由main线程来执行
    - DiscardPolicy()：队列满了，丢掉任务，不会抛出异常
    - DiscardOldestPolicy()：队列满了，试探最早的是否结束，如果结束就跟进，没结束就不处理，不抛出异常
 */
public class Demo02 {
    public static void main(String[] args) {
        /*
        最大线程该如何定义？
        1. CPU密集型，CPU几核，就定义几，保证CPU的效率最高
        2. IO密集型：判断程序中十分耗IO的线程数量，定义大于该数量即可，一般两倍
         */
        // 获取系统CPU核心数
        System.out.println(Runtime.getRuntime().availableProcessors());

        // 工作中，使用 ThreadPoolExecutor() 创建线程池
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),  // 候客区
                Executors.defaultThreadFactory(),  // 线程工厂
                new ThreadPoolExecutor.DiscardOldestPolicy()  // 拒绝策略！
        );

        try {
            // 人数 > 核心线程数 + 阻塞队列大小，则触发最大核心线程数
            /*
            0 <= n <= 5（core + queue），只需2个核心线程
            6 <= n <= 8（max + queue），需要5个最大核心线程，理论上是<=8，受执行速度影响
            8 <= n，拒绝策略，java.util.concurrent.RejectedExecutionException
             */
            for (int i = 0; i < 20; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
