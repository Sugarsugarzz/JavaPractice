package com.sugar.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程有序执行
 * 只用一个 condition 的情况。
 * A 执行完调用 B，B 执行完调用 C，C 执行完调用 A
 */
public class D {
    public static void main(String[] args) {
        Data4 data = new Data4();

        new Thread(() -> {for (int i = 0; i < 10; i++) data.printA();}, "A").start();
        new Thread(() -> {for (int i = 0; i < 10; i++) data.printB();}, "B").start();
        new Thread(() -> {for (int i = 0; i < 10; i++) data.printC();}, "C").start();
    }
}

// 资源类 Lock
class Data4 {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private int number = 1;  // 1A 2B 3C

    public void printA() {
        lock.lock();
        try {
            // 业务，判断 -> 执行 -> 通知
            while (number != 1) {
                // 等待
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + " =>> AAA");
            // 唤醒 B
            number = 2;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            // 业务，判断 -> 执行 -> 通知
            while (number != 2) {
                // 等待
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + " =>> BBB");
            number = 3;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            // 业务，判断 -> 执行 -> 通知
            while (number != 3) {
                // 等待
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + " =>> CCC");
            number = 1;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
