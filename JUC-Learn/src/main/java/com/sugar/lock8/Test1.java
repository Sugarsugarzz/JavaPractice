package com.sugar.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 八锁，关于锁的八个问题
 * 1. 标准情况下，两个线程先 sendSms 还是 call？  1/sendSms 2/call
 * 2. sendSms延迟4 秒，两个线程先 sendSms 还是 call？  1/sendSms 2/call
 */
public class Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> {phone.sendSms();}, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {phone.call();}, "B").start();
    }
}

class Phone {

    // synchronized 锁的对象是方法的调用者，即 phone
    // 两个方法用的是 同一个锁，谁先拿到谁执行
    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }

    public synchronized void call() {
        System.out.println("call");
    }
}
