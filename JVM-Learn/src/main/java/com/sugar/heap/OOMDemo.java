package com.sugar.heap;

import java.util.ArrayList;
import java.util.List;

// -Xms 设置初始化内存分配大小，默认电脑内存的 1/64
// -Xmx 设置最大分配内存，默认电脑内存的 1/4
// -XX:+PrintGCDetails，打印GC垃圾回收信息
// -XX:+HeapDumpOnOutOfMemoryError，OOM生成Dump文件

// -Xms1m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError
//  Dumping heap to java_pid99986.hprof ...
//  Heap dump file created [12801156 bytes in 0.042 secs]
public class OOMDemo {
    public static void main(String[] args) {
        byte[] array = new byte[1*1024*1024];  // 1m

        List<OOMDemo> list = new ArrayList<>();
        int count = 0;

        try {
            while (true) {
                list.add(new OOMDemo());
                count++;
            }
        } catch (Exception e) {
            System.out.println("count:" + count);
            e.printStackTrace();
        }
    }
}
