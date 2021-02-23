package com.sugar.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 异步调用：CompletableFuture
 * 1. 异步执行
 * 2. 成功回调
 * 3. 失败回调
 */
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 没有返回值的 runAsync 异步回调
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + " runAsync=>Void");
//        });
//
//        System.out.println("1111");
//
//        completableFuture.get();  // 获取阻塞执行的结果

        // 有返回值的
        // ajax，成功和失败的回调
        // 返回的是错误信息
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " supplyAsync => Integer");
            return 1024;
        });

        completableFuture.whenComplete((t, u) -> {
            System.out.println("t==>" + t);  // t 是正常返回结果
            System.out.println("u==>" + u);  // u 是错误信息
        }).exceptionally((e) -> {
            e.getMessage();
            return 233;  // 可以获取到错误的返回结果
        }).get();
    }
}
