package com.sugar.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 八锁，关于锁的八个问题
 * 7. 一个静态同步方法，一个普通同步方法，一个对象，两个线程先 sendSms 还是 call？  1/call  2/sendSms
 * 8. 两个对象，两个线程先 sendSms 还是 call？  1/call  2/sendSms
 *
 * 是因为：静态锁的是Class，普通锁的是调用者，锁不是同一个对象，因此先执行call。
 */
public class Test4 {
    public static void main(String[] args) {
        // 两个对象，两个调用者，两个锁
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();

        new Thread(() -> {phone1.sendSms();}, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {phone2.call();}, "B").start();
    }
}

class Phone4 {

    // 静态同步方法 锁的是Class
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }

    // 普通同步方法 锁的是调用者
    public synchronized void call() {
        System.out.println("call");
    }

}
