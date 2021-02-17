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

static  Class  唯一的一个模板类



### 6 集合类不安全

> List 不安全

```java
package com.sugar.unsafe_collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

// java.util.ConcurrentModificationException  并发修改异常。
public class ListTest {
    public static void main(String[] args) {
        // 并发情况下，ArrayList是不安全的
        /**
         * 解决方案：
         * 1. List<String> list = new Vector<>();
         *    Vector与ArrayList的add方法，区别在于Vector加了synchronized
         *    但事实上，Vector在1.1提出，而ArrayList在1.2提出，去掉锁提升了效率，所以Vector不是很好的解决方案
         *
         * 2. List<String> list = Collections.synchronizedList(new ArrayList<>());
         *    Collections集合类提供的方法，转换为synchronized
         *
         * 3. List<String> list = new CopyOnWriteArrayList<>();
         *    JUC解决方案
         *      private transient volatile Object[] array;
         *    底层同样是使用了数组，用到了 volatile 关键字
         *    CopyOnWrite 写入时复制，COW  计算机程序设计领域的一种优化策略
         *    多个线程调用的时候，List，读取的时候是固定的，写入（存在覆盖操作）
         *    在写入的时候避免覆盖，造成数据问题
         *    
         *    CopyOnWriteArrayList 比 Vector 的优势：
         *    - 使用lock锁，效率更高
         */
//        List<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
```

> Set 不安全

```java
package com.sugar.unsafe_collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * java.util.ConcurrentModificationException
 * 解决方案：
 * 1. Set<String> set = Collections.synchronizedSet(new HashSet<>());
 * 2. Set<String> set = new CopyOnWriteArraySet<>();
 */
public class SetTest {
    public static void main(String[] args) {
//        Set<String> set = new HashSet<>();
//        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
```

补充：HashSet的底层？

```java
public HashSet() {
  map = new HashMap<>();
}

// add set的本质，就是map的key
public boolean add(E e) {
  return map.put(e, PRESENT)==null;
}

private static final Object PRESENT = new Object();  // 不变的值
```

> Map 不安全

HashMap基本参数

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210217143025578.png" alt="image-20210217143025578" style="zoom:40%;" />

```java
package com.sugar.unsafe_collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * java.util.ConcurrentModificationException
 * 1. Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
 * 2. Map<String, String> map = new ConcurrentHashMap<>();
 */
public class MapTest {
    public static void main(String[] args) {
        /**
         * map 是这样用的吗？
         *   no，工作中不用HashMap
         * 默认等价于什么？
         *   new HashMap<>(16, 0.75);  默认初始容量、加载因子
         */
//        Map<String, String> map = new HashMap<>();
//        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
```



### 7 Callable

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210217143419584.png" alt="image-20210217143419584" style="zoom:40%;" />

1、可以有返回值

2、 可以抛出异常

3、 方法不同，run() / call()

> 代码测试

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210217143735896.png" alt="image-20210217143735896" style="zoom:40%;" />

如何启动 Callable？

Thread的构造方法中，只能丢入 Runnable 接口。

Runnable 只是一个函数式接口，里面有个 run() 方法而已，但其有两个重要的实现类：

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210217150537311.png" alt="image-20210217150537311" style="zoom:40%;" />

进入 FutureTask，可以看到可以丢入 Callable接口的构造器，成功将 Callable 通过 FutureTask实现类和 Thread类 挂上关系。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210217150654082.png" alt="image-20210217150654082" style="zoom:40%;" />

==在很多高并发的环境下，某些任务只需要执行一次，这种场景正适合FutreTask==

```java
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
```

##### 细节问题：

1. 有缓存（一个FutureTask对象只能够被run一次，不会重复执行）
2. 返回结果可能需要等待，有阻塞



### 8 常用的辅助类

#### 8.1 CountDownLatch（减法计数器）

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210217153550322.png" alt="image-20210217153550322" style="zoom:40%;" />

```java
package com.sugar.assist_util;

import java.util.concurrent.CountDownLatch;

// 减法计数器
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 在必须要执行的任务中使用
        CountDownLatch countDownLatch = new CountDownLatch(6);  // 总数设为6

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " Go Out!");
                countDownLatch.countDown(); // 数量-1
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();  // 等待计数器归零（即指定数量线程运行结束），然后再向下执行

        System.out.println("Close Door");
    }
}
```

**原理：**

`countDownLatch.countDown();`  // 数量-1

`countDownLatch.await();`  // 等待计数器归零（即指定数量线程运行结束），然后再向下执行

每次有线程调用 countDown()方法，则数量-1，假设计数器变为0，await() 就会被唤醒，继续执行。

#### 8.2 CyclicBarrier（加法计数器）

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210217155005778.png" alt="image-20210217155005778" style="zoom:40%;" />

如果设置的计数大于启动的线程数，则设定的将无法执行。

```java
package com.sugar.assist_util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

// 加法计数器
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        /*
        集齐7颗龙珠版
         */
        // 召唤神龙的线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤成功！");
        });
        for (int i = 0; i < 7; i++) {
            // lambda能直接操作i么？ 否，可以操作final定义的常量
            final int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 收集了 " + temp + " 个龙珠");

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
```

#### 8.3 Semaphore（信号量）

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210217160230668.png" alt="image-20210217160230668" style="zoom:40%;" />

抢车位！

```java
package com.sugar.assist_util;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

// 抢车位，限流！
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 设置线程数量（车位数量）
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    // acquire() 得到
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 抢到车位。");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + " 离开车位。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // release() 释放
                    semaphore.release();
                }

            }, String.valueOf(i)).start();
        }
    }
}
```

**原理：**

`semaphore.acquire();`  获得，如果已经满了，等待直至有释放为止

`semaphore.release();`  释放，会将当前的信号量释放 + 1，然后唤醒等待的线程

**作用：**

- 多个共享资源互斥使用
- 并发限流，控制最大的线程数



### 9 读写锁

ReadWriteLock，唯一实现类 ReentranReadWriteLock

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210217161840020.png" alt="image-20210217161840020" style="zoom:40%;" />

```java
package com.sugar.read_write_lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock
 * 读-读：可以共存
 * 读-写：不能共存
 * 写-写：不能共存
 *
 * 独占锁（写锁）：一次只能被一个线程占有
 * 共享锁（读锁）：多个线程可以同时占有
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCacheLock myCache = new MyCacheLock();

        // 写入
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.put(temp+"", temp+"");
            }, String.valueOf(i)).start();
        }

        // 读取
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.get(temp+"");
            }, String.valueOf(i)).start();
        }
    }
}

/**
 * 加锁的自定义缓存
 * 写的时候，只能一个线程写
 * 读的时候，可以多个线程读
 */
class MyCacheLock {

    private volatile Map<String, Object> map = new HashMap<>();
    // 读写锁，相比ReentrantLock，能够更加细粒度的控制
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    // 存，写入的时候，只希望同时只有一个线程写
    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 写入 " + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " 写入完毕");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    // 取，所有线程都可以读
    // 读的时候必须加锁，否则无法保证数据一致性，导致出现幻读
    // 读加锁就是为了防止读在写的前面
    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 读取 " + key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + " 读取完毕");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

/**
 * 自定义缓存
 */
class MyCache {

    private volatile Map<String, Object> map = new HashMap<>();

    // 存，写
    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + " 写入 " + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + " 写入完毕");
    }

    // 取，读
    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + " 读取 " + key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + " 读取完毕");
    }
}
```



### 10 阻塞队列

阻塞队列是典型的生产者消费者问题。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210217171346246.png" alt="image-20210217171346246" style="zoom:40%;" />

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210217171537346.png" alt="image-20210217171537346" style="zoom:40%;" />

Queue：

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210217172445905.png" alt="image-20210217172445905" style="zoom:40%;" />

Queue家族关系：

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210217172452373.png" alt="image-20210217172452373" style="zoom:40%;" />

==什么情况下使用阻塞队列：多线程并发处理，线程池！==

**使用队列**

添加、移除

**四组API**

| 方式         | 抛出异常 | 不会抛出异常，有返回值 | 阻塞等待 | 超时等待          |
| ------------ | -------- | ---------------------- | -------- | ----------------- |
| 添加         | add      | offer                  | put      | offer(, TimeUnit) |
| 移除         | remove   | poll                   | take     | poll(, TimeUnit)  |
| 检测队列首部 | element  | peek                   | -        | -                 |

```java
package com.sugar.block_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
//        test3();
        test4();
    }

    /**
     * 1. 抛出异常
     */
    public static void test1() {
        // 队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        // 队列满，添加抛出异常
        // java.lang.IllegalStateException: Queue full
//        System.out.println(blockingQueue.add("d"));

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        // 队列空，取队首抛出异常
        // java.util.NoSuchElementException
        System.out.println(blockingQueue.element());
        // 队列空，移除抛出异常
        // java.util.NoSuchElementException
        System.out.println(blockingQueue.remove());
    }

    /**
     * 2. 有返回值
     */
    public static void test2() {
        // 队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));  // false，不抛出异常

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.peek());  // null，队首为空
        System.out.println(blockingQueue.poll());  // null，没有值了
    }

    /**
     * 3. 阻塞等待（一直阻塞）
     */
    public static void test3() throws InterruptedException {
        // 队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
//        blockingQueue.put("d");  // 队列没有位置，一直阻塞

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());  // 队列中没有这个元素，一直阻塞
    }

    /**
     * 4. 阻塞等待（等待超时）
     */
    public static void test4() throws InterruptedException {
        // 队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
//        blockingQueue.offer("d", 2, TimeUnit.SECONDS);  // 等待超过2秒就退出

        blockingQueue.poll();
        blockingQueue.poll();
        blockingQueue.poll();
        blockingQueue.poll(2, TimeUnit.SECONDS);  // 等待超过2秒就退出
    }
}
```

> Synchronized ueue 同步队列（属于BlockingQueue的实现）

没有容量

进去一个元素，必须等待取出来之后，才能再往里面放一个元素！（容量 1）

```java
package com.sugar.block_queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列
 * 和其他的BlockingQueue不一样，SynchronousQueue不存储元素
 * put一个元素后，必须从里面先take出来，否则不能再put进去值
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();  // 同步队列

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " put 1");
                queue.put("1");
                System.out.println(Thread.currentThread().getName() + " put 2");
                queue.put("2");
                System.out.println(Thread.currentThread().getName() + " put 3");
                queue.put("3");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "T1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " take " + queue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " take " + queue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " take " + queue.take());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "T2").start();
    }
}
```



### 11 线程池（重点）

线程池：三大方法、七大参数、四种拒绝策略

> 池化技术

程序的运行，本质：占用系统资源！优化资源的使用！  ==>  池化技术

线程池、连接池、内存池、对象池......创建、销毁，十分浪费资源。

优化技术：事先准备好一些资源，用的时候直接拿，用完以后归还。



**线程池的好处：**

1. 降低资源消耗
2. 提高响应的速度
3. 方便管理

==线程复用，可以控制最大并发数，管理线程==

> 线程池：三大方法

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210217193742237.png" alt="image-20210217193742237" style="zoom:40%;" />

```java
package com.sugar.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Executors 工具类，三大方法
// 使用了线程池之后，要使用线程池创建线程
public class Demo01 {
    public static void main(String[] args) {
        // Method 1：单个线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // Method 2：创建一个固定的线程池大小
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // Method 3：可伸缩的，遇强则强，遇弱则弱
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池用完，需要关闭线程池
            threadPool.shutdown();
        }
    }
}
```

> 七大参数

源码分析：

```java
public static ExecutorService newSingleThreadExecutor() {
  return new FinalizableDelegatedExecutorService
    (new ThreadPoolExecutor(1, 1,
                            0L, TimeUnit.MILLISECONDS,
                            new LinkedBlockingQueue<Runnable>()));
}

public static ExecutorService newFixedThreadPool(int nThreads) {
  return new ThreadPoolExecutor(nThreads, nThreads,
                                0L, TimeUnit.MILLISECONDS,
                                new LinkedBlockingQueue<Runnable>());
}

public static ExecutorService newCachedThreadPool() {
  return new ThreadPoolExecutor(0, Integer.MAX_VALUE,  // 约等于21亿
                                60L, TimeUnit.SECONDS,
                                new SynchronousQueue<Runnable>());
}

====>>>>>  其本质上都是 ThreadPoolExecutor()
  
  public ThreadPoolExecutor(int corePoolSize,  // 核心线程池大小
                            int maximumPoolSize,  // 最大核心线程池大小
                            long keepAliveTime,  // 超时没人调用就会释放
                            TimeUnit unit,  // 超时单位
                            BlockingQueue<Runnable> workQueue,  // 阻塞队列
                            ThreadFactory threadFactory,  // 线程工厂，创建线程的，一般不用动
                            RejectedExecutionHandler handler  // 拒绝策略
                           ) {
  if (corePoolSize < 0 ||
      maximumPoolSize <= 0 ||
      maximumPoolSize < corePoolSize ||
      keepAliveTime < 0)
    throw new IllegalArgumentException();
  if (workQueue == null || threadFactory == null || handler == null)
    throw new NullPointerException();
  this.acc = System.getSecurityManager() == null ?
    null :
  AccessController.getContext();
  this.corePoolSize = corePoolSize;
  this.maximumPoolSize = maximumPoolSize;
  this.workQueue = workQueue;
  this.keepAliveTime = unit.toNanos(keepAliveTime);
  this.threadFactory = threadFactory;
  this.handler = handler;
}
```

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210217201734330.png" alt="image-20210217201734330" style="zoom:40%;" />

> 手动创建线程池

```java
package com.sugar.pool;

import java.util.concurrent.*;

// 自定义线程池
// 银行取款场景复现
/*
四大拒绝策略：
    - AbortPolicy()：直接拒绝，抛出异常
    - CallerRunsPolicy()：哪来的回哪去，main线程调用的，就由main线程来执行
    - DiscardPolicy()：队列满了，丢掉任务，不会抛出异常
    - DiscardOldestPolicy()：队列满了，试探最早的是否结束，如果结束就跟进，没结束就不处理，不抛出异常
 */
public class Demo02 {
    public static void main(String[] args) {
        // 工作中，使用 ThreadPoolExecutor() 创建线程池
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),  // 候客区
                Executors.defaultThreadFactory(),  // 线程工厂
                new ThreadPoolExecutor.DiscardOldestPolicy()  // 拒绝策略！
        );

        try {
            // 人数 > 核心线程数 + 阻塞队列大小，则触发最大核心线程数
            /*
            0 <= n <= 5（core + queue），只需2个核心线程
            6 <= n <= 8（max + queue），需要5个最大核心线程，理论上是<=8，受执行速度影响
            8 <= n，拒绝策略，java.util.concurrent.RejectedExecutionException
             */
            for (int i = 0; i < 20; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}

```

> 拒绝策略

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210217205334222.png" alt="image-20210217205334222" style="zoom:40%;" />

```java
/*
四大拒绝策略：
    - AbortPolicy()：直接拒绝，抛出异常
    - CallerRunsPolicy()：哪来的回哪去，main线程调用的，就由main线程来执行
    - DiscardPolicy()：队列满了，丢掉任务，不会抛出异常
    - DiscardOldestPolicy()：队列满了，试探最早的是否结束，如果结束就跟进，没结束就不处理，不抛出异常
 */
```

> 小结和扩展

**最大核心线程数量（池的大小）如何定义？**

IO密集型、CPU密集型（调优）

```java
/*
最大线程该如何定义？
  1. CPU密集型，CPU几核，就定义几，保证CPU的效率最高
  2. IO密集型：判断程序中十分耗IO的线程数量，定义大于该数量即可，一般两倍
*/
// 获取系统CPU核心数
System.out.println(Runtime.getRuntime().availableProcessors());
```



### 12 四大函数式接口（重点必掌握）

基础掌握：泛型、枚举、反射

新时代需掌握：lambda表达式、链式编程、函数式接口、Stream流式计算

> 函数式接口：只有一个方法的接口

```java
// 举例，Runnable
@FunctionalInterface
public interface Runnable {
    public abstract void run();
}

// Java中有超级多的函数式接口
// 简化编程模型，在新版本的框架底层大量应用
// forEach()消费者类的函数式接口
```

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210217210733532.png" alt="image-20210217210733532" style="zoom:40%;" />

##### 代码测试

> Function  函数型接口

```java
package com.sugar.function_interface;

import java.util.function.Function;

/**
 * Function 函数型接口：有一个输入参数，有一个输出
 *  只要是函数式接口，都可以用lambda表达式简化
 */
public class Demo01 {
    public static void main(String[] args) {
        // 工具类：输出输入的值
//        Function<String, String> function = new Function<String, String>() {
//            @Override
//            public String apply(String o) {
//                return o;
//            }
//        };

        // lambda简化写法
        Function function = (o) -> {return o;};

        System.out.println(function.apply("aaa"));
    }
}
```

> Predicate  断定型接口

```java
package com.sugar.function_interface;

import java.util.function.Predicate;

/**
 * Predicate 断定型接口：有一个输入参数，返回值只能是布尔值。
 */
public class PredicateDemo {
    public static void main(String[] args) {
        // 判断字符串是否为空
//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String o) {
//                return o.isEmpty();
//            }
//        };

        Predicate<String> predicate = String::isEmpty;

        System.out.println(predicate.test("haha"));
    }
}
```

> Consumer  消费型接口

```java
package com.sugar.function_interface;

import java.util.function.Consumer;

/**
 * Consumer 消费型接口：只有输入，没有返回值
 */
public class ConsumerDemo {
    public static void main(String[] args) {
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String o) {
//                System.out.println(o);
//            }
//        };

        Consumer<String> consumer = System.out::println;

        consumer.accept("haha");
    }
}
```

> Supplier  供给型接口

```java
package com.sugar.function_interface;

import java.util.function.Supplier;

/**
 * Supplier 供给型接口：没有参数，只有返回值
 */
public class SupplierDemo {
    public static void main(String[] args) {
//        Supplier<String> supplier = new Supplier<String>() {
//            @Override
//            public String get() {
//                return "haha";
//            }
//        };

        Supplier<String> supplier = () -> "hahaa";

        System.out.println(supplier.get());
    }
}
```



























