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
