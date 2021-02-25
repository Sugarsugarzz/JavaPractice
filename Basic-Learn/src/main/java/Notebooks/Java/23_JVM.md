面试

- 谈谈对 JVM 的理解？Java8虚拟机和之前的变化更新？
- 什么是OOM？什么是栈溢出（StackOverFlowError）？怎么分析？
- JVM 常用调优参数有哪些？
- 内存快照如何抓取？怎么分析Dump文件？
- 谈谈 JVM 中类加载的认识？



### 1 JVM 的位置

​		硬件体系  **<==>**  操作系统（Windows，Linux，Mac）  **<==>**  JRE（JVM）  **<==>**  各种Java程序

### 2 JVM 体系结构

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210225125350720.png" alt="image-20210225125350720" style="zoom:50%;" />

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210225125014651.png" alt="image-20210225125014651" style="zoom:50%;" />

Java 栈、本地方法栈、程序计数器 **不会有垃圾回收**！

只有方法区和堆需要垃圾回收，所谓的 **JVM 调优**，就是在调 **方法区** 和 **堆（99%）**。

创建的对象之类的，都放在堆里面，堆的空间是最大的。



### 3 类加载器（类装载器）

​	**作用**：加载 .Class 文件

.class 文件装载到类加载器中，得到一个Class模板类，通过实例化得到不同的对象；通过反射从实例化对象得到模板类。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210225130512732.png" alt="image-20210225130512732" style="zoom:60%;" />

```java
package com.sugar.class_loader;

public class Car {
    public static void main(String[] args) {
        // 类是模板，对象是具体的
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();

        System.out.println(car1.hashCode());  // 实例化对象的hashcode不同
        System.out.println(car2.hashCode());
        System.out.println(car3.hashCode());

        Class<? extends Car> aClass1 = car1.getClass();
        Class<? extends Car> aClass2 = car2.getClass();
        Class<? extends Car> aClass3 = car3.getClass();

        System.out.println(aClass1.hashCode());  // 模板类的hashcode相同
        System.out.println(aClass2.hashCode());
        System.out.println(aClass3.hashCode());
    }
}
```

类加载器

1. 虚拟机自带的加载器
2. 启动类（根）加载器（BOOT）
3. 扩展类加载器（EXT）
4. 应用程序加载器（APP）

```java
package com.sugar.class_loader;

public class Car {
    public static void main(String[] args) {
        // 类是模板，对象是具体的
        Car car1 = new Car();
        Class<? extends Car> aClass1 = car1.getClass();

        // 从模板类获取ClassLoader
        ClassLoader classLoader = aClass1.getClassLoader();
        System.out.println(classLoader);  // AppClassLoader
        System.out.println(classLoader.getParent());  // ExtClassLoader  jre/lib/ext
        System.out.println(classLoader.getParent().getParent());  // null(BootClassLoader) java程序获取不到（底层C++写的）  rt.jar

    }
}
```



### 4 双亲委派机制

步骤：

1. 类加载器收到类加载的请求
2. 将这个请求向上委托给父类加载器去完成，一直向上委托，直到启动类加载器
3. 启动类加载器会检查是否能够加载当前这个类，能加载就使用当前加载器，否则抛出异常，通知子加载器进行加载
4. 重复步骤3
5. 大部分写的类都在 ApplicationClassLoader，如果不存在则抛出 Class not Found 异常。

```java
package java.lang;

public class String {
    /**
     * 双亲委派机制，为了保证安全
     * 1. APP --> EXT --> BOOT（最终执行）
     * 会一层层向上找String，找到则执行；如果找不到则再返回找，BOOT->EXT->APP，找到则执行。
     */
    public String toString() {
        return "Hello";
    }

    public static void main(String[] args) {
        String s = new String();
        System.out.println(s.toString());  // 错误: 在类 java.lang.String 中找不到 main 方法
    }
}
```

```java
package com.sugar.class_loader;

public class Student {
    /**
     * 双亲委派
     * BOOT->EXT->APP
     */
    public String toString() {
        return "Hello";
    }

    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student.toString());
        // 确定Student是否在用户加载器  ->  APPClassLoader
        System.out.println(student.getClass().getClassLoader());
    }
}
```



### 5 沙箱安全机制

​		Java 安全模型的核心就是 Java沙箱（sandbox），沙箱是一个限制程序运行的环境。沙箱机制就是将 Java 代码限定在虚拟机（JVM）特定的运行范围中，并且严格限制代码对本地系统资源访问，通过这样的措施来保证对代码的有效隔离，防止对本地系统造成破坏。沙箱**主要限制系统资源访问**，系统资源包括 CPU、内存、文件系统、网络。不同级别的沙箱对这些资源访问的限制也可以不一样。

​		所有的 Java程序运行都可以指定沙箱，可以定制安全策略。

​		在 Java中将执行程序分成本地代码和远程代码两种，本地代码默认视为可信任的，而远程代码则被看作是不受信的。对于授信的本地代码，可以访问一切本地资源，而对于非授信的远程代码，在早期的 Java实现中，安全依赖于沙箱（Sandbox）机制。如下图 JDK1.0 安全模型。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210225141533404.png" alt="image-20210225141533404" style="zoom:70%;" />

​		**但如此严格的安全机制也给程序的功能扩展带来障碍，比如当用户希望远程代码访问本地系统的文件时，就无法实现。**因此在后续的 Java1.1 版本中，针对安全机制做了改进，增加了 `安全策略`，允许用户指定代码对本地资源的访问权限。如下图 JDK1.1 安全模型。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210225141746856.png" alt="image-20210225141746856" style="zoom:70%;" />

​		在 Java1.2 版本中，再次改进了安全机制，增加了 `代码签名`。无论本地代码还是远程代码，都会按照用户的安全策略设定，由类加载器加载到虚拟机中权限不同的运行空间，来实现差异化的代码执行权限控制。如下图 JDK1.2 安全模型

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210225141914299.png" alt="image-20210225141914299" style="zoom:70%;" />

​		当前最新的安全机制实现，引入了**域（Domain）**的概念。虚拟机会把所有代码加载到不同的系统域和应用域，系统域部分专门负责与关键资源进行交互，而各个应用域部分则通过系统域的部分代理来对各种需要的资源进行访问。虚拟机中不同的受保护域（Protected DOmain），对应不一样的权限（Permission）。存在于不同域中的类文件就具有了当前域的全部权限。如下图 JDK1.6 安全模型。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210225142229612.png" alt="image-20210225142229612" style="zoom:70%;" />

​		组成沙箱的基本组件：

- `字节码校验器`（bytecode verifier）：确保 Java类文件遵循 Java语言规范。这样可以帮助 Java程序实现内存保护。但并不是所有的类文件都会经过字节码校验，比如核心类。
- 类装载器（Class Loader）：其中类装载器在三个方面对 Java沙箱起作用
  - 防止恶意代码去干涉善意代码.   -  双亲委派机制
  - 守护了被信任的类库边界
  - 将代码归入保护域，确定了代码可以进行哪些操作

​		虚拟机为不同的类加载器载入的类提供不同的命名空间，命名空间由一系列唯一的名称组成，每一个被装载的类将有一个名字，这个命名空间是由 Java虚拟机为每一个类装载器维护的，它们互相之间甚至不可见。

​		**类装载器采用的机制是双亲委派模式**。

1. 从最内层 JVM自带类加载器开始加载，外层恶意同名类得不到加载从而无法使用；
2. 由于严格通过包来区分了访问域，外层恶意的类通过内置代码也无法获得权限访问到内层类，破坏代码自然无法生效。

- `存取控制器`（access controller）：存取控制器可以控制核心 API 对操作系统的存取权限，而这个控制的策略设定，可以由用户指定。

- `安全管理器`（security manager）：是核心 API和操作系统之间的主要接口。实现权限控制，比存取控制器优先级高。

- `安全软件包`（security package）：java.security下的类和扩展包下的类，允许用户为自己的应用增加新的安全特性，包括：

  - 安全提供者

  - 消息摘要

  - 数字签名  keytools

  - 加密

  - 鉴别

    

### 6 Native

Thread.start()方法源码

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
```

```java
package com.sugar.native_test;

public class Demo {
    public static void main(String[] args) {
        /**
         * Thread.start()中调用了 start0()方法
         * private native void start0();
         * 一般在
         */
        new Thread(() -> {

        }, "MyThread").start();
    }

    // native：凡是带了native关键字的，说明 Java的作用范围达不到了，会调用底层C语言的库！
    // 会进入本地方法栈，调用本地方法接口（JNI）
    // JNI的作用：扩展 Java的使用，融合不同的编程语言为Java所用！最初融合：C、C++
    // 在内存区域专门开辟了一块标记区域：Native Method Stack，登记native方法
    // 在最终执行的时候，加载本地方法库中的方法，通过JNI

    // Java程序驱动打印机、管理系统，掌握即可，在企业级应用中较为少见！
    private native void start0();
}
```

凡是带了 native 关键字的，说明 Java的作用范围达不到，去调用底层 C语言的库！

**JNI：Java Native Interface（Java本地方法接口）**

凡是带了native关键字的方法就会进入本地方法栈，其他的就是Java栈。



**Native Interface** 本地接口

​		本地接口的作用是融合不同的编程语言为 Java所用，它的初衷是融合C/C++程序，Java在诞生的时候是C/C++恒星的时候，想要立足，必须有调用C、C++的程序，于是就在内存中专门开辟了一块区域处理标记为native的代码，它的具体做法是在 Native Method Stack 中登记 native 方法，在执行引擎（Execution Engine）执行的时候加载 Native Libraries。

​		目前该方法使用的越来越少了，除非是与硬件有关的应用，比如通过 Java程序驱动打印机或者 Java系统管理生产设备，在企业级应用中已经比较少见。因为现在的异构领域间通信很发达，比如可以使用Socket通信，也可以使用 WebService等。



**Native Method Stack**

​		它的具体做法是在 Native Method Stack 中登记 native 方法，在执行引擎（Execution Engine）执行的时候加载本地库（Native Libraries）。



### 7 PC寄存器

​		程序计数器：Program Counter Register

​		每个线程都有一个程序计数器，是线程私有的，就是一个指针，指向方法去中的方法字节码（用来存储指向下一条指令的地址，也即将要执行的指令代码），在执行引擎读取下一条指令，是一个非常小的内存空间，几乎可以忽略不计。



### 8 方法区

**Method Area 方法区**

​		方法去是被所有线程共享，所有字段和方法字节码，以及一些特殊方法，如构造函数、接口代码也在此定义。简单说，所有定义的方法的信息都保存在该区域，**此区域属于共享区间。**

​		==静态变量、常量、类信息（构造方法、接口定义）、运行时的常量池存在方法区中，但是实例变量存在堆内存中，与方法区无关。==

​		static、final、Class模板、常量池（1.7以后存放在堆中，逻辑上还属于方法区）



### 9 栈

栈内存，主观程序的运行，生命周期和线程同步。

线程结束，栈内存也就释放了。对于栈来说，**不存在垃圾回收问题。**

一旦线程结束，栈就Over。

栈存放：8大基本类型 + 对象引用 + 实例的方法

栈运行原理：栈帧

栈满了：抛出 StackOverFlowError

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210225161504717.png" alt="image-20210225161504717" style="zoom:80%;" />

栈 + 堆 + 方法区：交互关系

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210225161750278.png" alt="image-20210225161750278" style="zoom:70%;" />



### 10 三种 JVM

- Sun公司 HotSpot  `Java HotSpot(TM) 64-Bit Server VM (build 25.201-b09, mixed mode)`
- BEA  JRocket
- IBM  J9 VM

一般使用的都是 `HotSpot`



### 11 堆（Heap）

​		一个 JVM 只有一个堆内存，堆内存的大小时可以调节的。

​		类加载器读取了类文件后，一般会把什么东西放到堆中？  类、方法、常量、变量！保存所有引用类型的真实对象。

​		堆内存中还要细分三个区域：

- 新生区
- 老年区
- 永久区（1.8 永久区已经被方法区的元空间取代）

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210225162950469.png" alt="image-20210225162950469" style="zoom:80%;" />

​		GC 垃圾回收，主要是在**伊甸园区**和**老年区**。

​		假设内存满了，抛出 OutOfMemoryError：Java heap space，堆内存不够！

​		在 JDK8以后，永久存储区改了个名字（元空间）。



### 12 新生区、老年区

**新生区**

- 类：诞生、成长的地方，甚至死亡
- 伊甸园，所有的对象都是在伊甸园区new出来的！
- 幸存者区（0区、1区）

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210225164128340.png" alt="image-20210225164128340" style="zoom:70%;" />

​		经过研究，99%的对象都是临时对象！



**老年区**

​		在新生区幸存的，在幸存者0区和1区都满了后，放到老年区。



### 13 永久区（元空间）

**永久区**：这个区域常驻内存。用来存放 JDK 自身携带的 Class 对象。Interface元数据，存储的是Java运行时的一些环境或类信息，该区域不存在垃圾回收！关闭虚拟机就会释放该区域的内存。

​		该区域出现 OOM 的情况：一个启动类，加载了大量的第三方jar包、Tomcat部署了太多的应用、大量动态生成的反射类。不断的被加载，直到内存满，就会出现OOM。		

- jdk1.6之前：永久代，常量池是在方法区中。
- jdk1.7：永久代，但是慢慢退化了。 `去永久代`，常量池在堆中。
- jdk1.8之后：无永久代，常量池在元空间。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210225165623399.png" alt="image-20210225165623399" style="zoom:80%;" />

```java
package com.sugar.heap;

public class Demo02 {
    public static void main(String[] args) {
        // 返回虚拟机试图使用的最大内存
        long max = Runtime.getRuntime().maxMemory();
        System.out.println("max=" + max + "字节\t" + (max/(double) 1024/1024) + "MB");
        // 返回JVM的初始化总内存
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("totalMemory=" + totalMemory + "字节\t" + (max/(double) 1024/1024) + "MB");

        // 默认情况下，分配的总内存 是 电脑内存的 1/4，初始化的内存：1/64
    }

    // 调参：-Xms1024m -Xmx1024m -XX:+PrintGCDetails
    // 新生区 + 老年区 = 1024m，所以元空间在逻辑上存在，物理上不存在
    /**
     * 出现OOM：
     * 1. 尝试扩大堆内存，看结果
     * 2. 分析内存，看哪个地方出现了问题（专业工具JProfiler）
     */
}
```



### 14 堆内存调优







### 15 GC



### 16 JMM