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

```java
package com.sugar.demo01;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Lock锁实现卖票
public class SaleTicketDemo02 {
    public static void main(String[] args) {
        Ticket2 ticket = new Ticket2();

        new Thread(() -> { for (int i = 0; i < 60; i++) ticket.sale(); }, "A").start();
        new Thread(() -> { for (int i = 0; i < 60; i++) ticket.sale(); }, "B").start();
        new Thread(() -> { for (int i = 0; i < 60; i++) ticket.sale(); }, "C").start();
    }
}

// Lock 三部曲
// 1. new ReentrantLock()
// 2. Lock.lock() 加锁
// 3. finally => Lock.unlock()  解锁
class Ticket2 {
    // 属性、方法
    private int number = 50;

    Lock lock = new ReentrantLock();

    public void sale() {
        // 加锁
        lock.lock();
        try {
            // 业务代码
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + " 卖出了 " + number-- + " 票，剩余：" + number + " 张");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 解锁
            lock.unlock();
        }

    }
}
```

公平锁：先来后到。

**非公平锁：可以插队。（默认）**

> Synchronized 和 Lock锁 的区别

1、Synchronized 是内置的 Java 关键字，而 Lock 是一个 Java 类

2、 Synchronized 无法判断获取锁的状态，Lock 可以判断是否获取到了锁

3、Synchronized 会自动释放锁，Lock 必须要手动释放锁。如果不释放锁，**死锁**！

4、Synchronized 线程1（获得锁，阻塞），线程2（等待，等待），Lock锁就不一定会等待下去。`lock.trylock()` 

5、Synchronized 可重入锁，不可以中断的，非公平；Lock锁，可重入锁，可以判断锁，非公平（可自行设置）

6、Synchronized 适合锁少量的代码同步问题，Lock 适合锁大量的同步代码。 

> 锁是什么，如果判断锁的是谁？





### 4 生产者和消费者问题

> 生产者和消费者问题 Synchronized 版

```java
/**
 * 线程之间的通信问题（生产者和消费者）等待唤醒、通知唤醒
 * 线程交替执行， A B操作同一个变量  num = 0
 * A num + 1
 * B num - 1
 */
public class A {
    public static void main(String[] args) {
        Data data = new Data();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

    }
}

// 数字 资源类
// 等待、业务、通知
class Data {

    private int number = 0;

    // +1
    public synchronized void increment() throws InterruptedException {
        if (number != 0) {
            // 等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + " => " + number);
        // 通知其他线程，+1完毕
        this.notifyAll();
    }

    // -1
    public synchronized void decrement() throws InterruptedException {
        if (number == 0) {
            // 等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + " => " + number);
        // 通知其他线程，-1完毕
        this.notifyAll();
    }
}
```

> 存在问题：只有A、B线程，如果还有C、D线程，是否还安全？虚假唤醒！

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210209125846089.png" alt="image-20210209125846089" style="zoom:40%;" />

**解决：if 改为 while 判断，防止虚假唤醒**

```java
/**
 * 线程之间的通信问题（生产者和消费者）等待唤醒、通知唤醒
 * 线程交替执行， A B操作同一个变量  num = 0
 * A num + 1
 * B num - 1
 */
public class A {
    public static void main(String[] args) {
        Data data = new Data();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();

    }
}

// 数字 资源类
// 等待、业务、通知
class Data {

    private int number = 0;

    // +1
    public synchronized void increment() throws InterruptedException {
        while (number != 0) {
            // 等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + " => " + number);
        // 通知其他线程，+1完毕
        this.notifyAll();
    }

    // -1
    public synchronized void decrement() throws InterruptedException {
        while (number == 0) {
            // 等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + " => " + number);
        // 通知其他线程，-1完毕
        this.notifyAll();
    }
}
```

> 生产者和消费者问题  Lock 版

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210209130309504.png" alt="image-20210209130309504" style="zoom:40%;" />

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210209130233043.png" alt="image-20210209130233043" style="zoom:40%;" />

```java
/**
 * 线程之间的通信问题（生产者和消费者）等待唤醒、通知唤醒
 * 线程交替执行， A B操作同一个变量  num = 0
 * A num + 1
 * B num - 1
 */
public class B {
    public static void main(String[] args) {
        Data2 data = new Data2();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

// 数字 资源类
// 等待、业务、通知
class Data2 {

    private int number = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    // condition.await();       等待
    // condition.signalAll();   唤醒

    // +1
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (number != 0) {
                // 等待
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + " => " + number);
            // 通知其他线程，+1完毕
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    // -1
    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (number == 0) {
                // 等待
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + " => " + number);
            // 通知其他线程，-1完毕
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
```

> Condition  可以精准的通知和唤醒线程，使其有序执行

自测，只用一个condition，改为signalAll，同样实现有序唤醒，根据number控制唤醒对象。

```java
package com.sugar.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程有序执行
 * A 执行完调用 B，B 执行完调用 C，C 执行完调用 A
 */
public class C {
    public static void main(String[] args) {
        Data3 data = new Data3();

        new Thread(() -> {for (int i = 0; i < 10; i++) data.printA();}, "A").start();
        new Thread(() -> {for (int i = 0; i < 10; i++) data.printB();}, "B").start();
        new Thread(() -> {for (int i = 0; i < 10; i++) data.printC();}, "C").start();
    }
}

// 资源类 Lock
class Data3 {

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    private int number = 1;  // 1A 2B 3C

    public void printA() {
        lock.lock();
        try {
            // 业务，判断 -> 执行 -> 通知
            while (number != 1) {
                // 等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + " =>> AAA");
            // 唤醒 B
            number = 2;
            condition2.signal();
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
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + " =>> BBB");
            number = 3;
            condition3.signal();
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
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + " =>> CCC");
            number = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

```

### 5 八锁现象

如何判断锁的是谁？

对象、Class

```java
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

```

```java
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

```

```java
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

```

```java
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

```

> 小结

new  this  具体的一个对象

static  Class  唯一的一个模板