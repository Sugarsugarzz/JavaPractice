package com.sugar.block_queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列
 * 和其他的BlockingQueue不一样，SynchronousQueue不存储元素
 * put一个元素后，必须从里面先take出来，否则不能再put进去值
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();  // 同步队列

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " put 1");
                queue.put("1");
                System.out.println(Thread.currentThread().getName() + " put 2");
                queue.put("2");
                System.out.println(Thread.currentThread().getName() + " put 3");
                queue.put("3");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "T1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " take " + queue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " take " + queue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " take " + queue.take());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "T2").start();
    }
}
