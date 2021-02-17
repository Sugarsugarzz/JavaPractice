package com.sugar.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 传统方式 Runnable
//        new Thread(new Runnable()).start();

        new Thread().start(); // 如何启动Callable？
//        new Thread(new FutureTask<V>()).start();
//        new Thread(new FutureTask<V>(Callable)).start();

        MyThread callableThread = new MyThread();
        FutureTask futureTask = new FutureTask(callableThread);
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();  // 事实上，只打印一个call()，结果被缓存

        // get方法可能会产生阻塞，把它放到最后，或者使用异步通信来处理
        Integer res = (Integer) futureTask.get();  // 获取Callable的返回结果
        System.out.println(res);
    }
}

class MyThread implements Callable<Integer> {
    @Override
    public Integer call() {
        System.out.println("call()");
        return 1;
    }
}