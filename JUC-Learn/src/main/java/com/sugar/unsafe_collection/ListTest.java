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
