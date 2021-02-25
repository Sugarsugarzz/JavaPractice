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
