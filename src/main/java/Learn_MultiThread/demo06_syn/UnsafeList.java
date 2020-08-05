package Learn_MultiThread.demo06_syn;

import java.util.ArrayList;
import java.util.List;

// 线程不安全的集合
public class UnsafeList {
    public static void main(String[] args) {

        /*
        ArrayList是线程不安全的。
            原因：是因为两个线程统一瞬间，操作了同一个数，添加到了同一个位置，所以就覆盖掉了，元素就会少。
         */
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                synchronized (list) {
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }
        // 线程不安全，所以总数是少于10000的。（CPU比较强的每次10000也是可以的...）
        System.out.println(list.size());
    }
}
