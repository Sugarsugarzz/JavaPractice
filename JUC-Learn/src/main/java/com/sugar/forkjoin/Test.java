package com.sugar.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test1();
//        test2();
        test3();
    }

    // normal（Level 1）
    public static void test1() {
        long start = System.currentTimeMillis();

        long sum = 0L;
        for (long i = 0; i < 10_0000_0000; i++) {
            sum += i;
        }

        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + " 时间：" + (end - start));
    }

    // ForkJoin（Level 2）
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);// 提交任务，得到结果。  execute() 无结果
        Long sum = submit.get();

        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + " 时间：" + (end - start));
    }

    // Stream（Level 3）
    public static void test3() {
        long start = System.currentTimeMillis();

        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);

        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + " 时间：" + (end - start));
    }
}
