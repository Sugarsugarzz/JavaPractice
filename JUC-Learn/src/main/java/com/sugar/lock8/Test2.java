package com.sugar.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 八锁，关于锁的八个问题
 * 3. 增加一个普通方法，两个线程先 sendSms 还是 hello？  1/hello  2/sendSms
 * 4. 两个对象，两个同步方法，两个线程先 sendSms 还是 call？  1/call  2/sendSms
 */
public class Test2 {
    public static void main(String[] args) {
        // 两个对象，两个调用者，两个锁
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();

        new Thread(() -> {phone1.sendSms();}, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {phone2.call();}, "B").start();
    }
}

class Phone2 {

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
    // 这里没有锁，不是同步方法，不受锁的影响
    public void hello() {
        System.out.println("hello");
    }
}
