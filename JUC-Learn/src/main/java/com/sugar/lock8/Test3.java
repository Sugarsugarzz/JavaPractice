package com.sugar.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 八锁，关于锁的八个问题
 * 5. 增加两个静态同步方法，只有一个对象，两个线程先 sendSms 还是 call？  1/sendSms  2/call
 * 6. 两个对象，两个线程先 sendSms 还是 call？  1/sendSms  2/call
 */
public class Test3 {
    public static void main(String[] args) {
        // 两个对象，两个调用者，两个锁
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();

        new Thread(() -> {phone1.sendSms();}, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {phone2.call();}, "B").start();
    }
}

class Phone3 {

    // synchronized 锁的对象是方法的调用者，即 phone
    // static 静态方法
    // 类一加载就有了，锁的是 Class
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }

    public static synchronized void call() {
        System.out.println("call");
    }
    // 这里没有锁，不是同步方法，不受锁的影响
    public void hello() {
        System.out.println("hello");
    }
}
