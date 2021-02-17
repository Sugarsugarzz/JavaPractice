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
