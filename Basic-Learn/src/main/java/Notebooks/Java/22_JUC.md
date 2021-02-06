### 1 JUC

`java.util.concurrent`

`java.util.concurrent.atomic`

`java.util.concurrent.locks`

java.util工具包

**业务：普通的线程代码  Thread**

**Runnable：没有返回值，效率相比 Callable较低，功能没有Callable强大。**

### 2 线程和进程

进程：执行程序的执行过程。

一个进程往往包含多个线程，至少包含一个！

**Java默认有几个线程？ 2个（Main，GC）**

线程：比如 Typora 进程，有写字、自动保存线程。

**Java能开线程吗？不能，只能调用底层的C++方法开启，Java无法直接操作硬件。**

```java
    public synchronized void start() {
        /**
         * This method is not invoked for the main method thread or "system"
         * group threads created/set up by the VM. Any new functionality added
         * to this method in the future may have to also be added to the VM.
         *
         * A zero status value corresponds to state "NEW".
         */
        if (threadStatus != 0)
            throw new IllegalThreadStateException();

        /* Notify the group that this thread is about to be started
         * so that it can be added to the group's list of threads
         * and the group's unstarted count can be decremented. */
        group.add(this);

        boolean started = false;
        try {
            start0();
            started = true;
        } finally {
            try {
                if (!started) {
                    group.threadStartFailed(this);
                }
            } catch (Throwable ignore) {
                /* do nothing. If start0 threw a Throwable then
                  it will be passed up the call stack */
            }
        }
    }
		// 本地方法！
    private native void start0();
```

> 并发、并行

并发编程：并发、并行

并发（多线程操作同一个资源）

- CPU 一核，模拟出来多条线程，快速交替

并行（多个人一起行走）

- CPU多核，多个线程可以同时执行；线程池

```java
public class Test1 {
    public static void main(String[] args) {
        // 获取CPU的核数
        // CPU密集型，IO密集型
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
```

并发编程的本质：**充分利用CPU的资源**

> 线程的状态

```java
public enum State {
    		// 新建
        NEW,
  			// 运行
        RUNNABLE,
  			// 阻塞
        BLOCKED,
  			// 等待，死等
        WAITING,
  			// 超时等待
        TIMED_WAITING,
        // 终止
        TERMINATED;
    }
```

> wait / sleep 的区别

1. 来自不同的类

   wait => Object

   sleep => Thread（企业中一般不用sleep，用 `TimeUnit.Days.sleep()`。

2. 关于锁的释放

   wait 会释放锁，而 sleep 会抱着锁睡觉，不会释放！

3. 使用的范围不同

   wait 必须再同步代码块中。

   sleep 可以在任何地方。

4. 是否需要捕获异常

   wait 要捕获异常，但不必须，被中断会抛出异常。

   sleep 必须要捕获异常。

### 3 Lock锁（重点）

> 传统 Synchronized

```java
/**
 * 真正的多线程开发
 * 线程就是一个单独的资源类，没有任何附属的操作
 * 1、属性，方法
 */
// 基本的卖票例子
public class SaleTicketDemo01 {
    public static void main(String[] args) {
        // 并发，多线程操作同一个资源类，把资源类丢入线程
        Ticket ticket = new Ticket();

        // 函数式接口，lambda表达式 (参数)->{代码}
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "C").start();
    }
}

// 资源类 OOP
class Ticket {
    // 属性、方法
    private int number = 50;

    // 卖票的方式
    // synchronized 本质：队列，锁
    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + " 卖出了 " + number-- + " 票，剩余：" + number + " 张");
        }
    }
}
```

> Lock 接口

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210206170417947.png" alt="image-20210206170417947" style="zoom:40%;" />

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210206170507783.png" alt="image-20210206170507783" style="zoom:40%;" />

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210206170726247.png" alt="image-20210206170726247" style="zoom:40%;" />

公平锁：先来后到。

**非公平锁：可以插队。（默认）**

> Synchronized 和 Lock锁 的区别

1、Synchronized 是内置的 Java 关键字，而 Lock 是一个 Java 类

2、 Synchronized 无法判断获取锁的状态，Lock 可以判断是否获取到了锁

3、Synchronized 会自动释放锁，Lock 必须要手动释放锁。如果不释放锁，**死锁**！

4、Synchronized 线程1（获得锁，阻塞），线程2（等待，等待），Lock锁就不一定会等待下去。`lock.trylock()` 

5、Synchronized 可重入锁，不可以中断的，非公平；Lock锁，可重入锁，可以判断锁，非公平（可自行设置）

6、Synchronized 适合锁少量的代码同步问题，Lock 适合锁大量的同步代码。 