package com.sugar.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 求和计算的任务！
 * 如何使用ForkJoin？
 * 1、ForkJoinPool  通过它来执行
 * 2、计算任务 forkjoinpool.execute(ForkJoinTask task)
 * 3、计算类要继承 RecursiveTask
 */
public class ForkJoinDemo  extends RecursiveTask<Long> {

    private Long start;
    private Long end;

    // 临界值
    private Long temp = 10000L;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    // 计算方法
    @Override
    protected Long compute() {
        if ((end - start) < temp) {
            long sum = 0L;
            for (Long i = start; i < end; i++) {
                sum += i;
            }
            return sum;
        } else {
            // forkjoin
            long mid = (start + end) / 2;  // 中间值
            ForkJoinDemo task1 = new ForkJoinDemo(start, mid);
            task1.fork();  // 拆分，把任务压入线程队列
            ForkJoinDemo task2 = new ForkJoinDemo(mid + 1, end);
            task2.fork();

            return task1.join() + task2.join();

        }
    }
}
