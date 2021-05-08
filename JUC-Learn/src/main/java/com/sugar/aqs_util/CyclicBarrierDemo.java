package com.sugar.aqs_util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

// 加法计数器
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        /*
        集齐7颗龙珠版
         */
        // 召唤神龙的线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤成功！");
        });
        for (int i = 0; i < 7; i++) {
            // lambda能直接操作i么？ 否，可以操作final定义的常量
            final int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 收集了 " + temp + " 个龙珠");

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
