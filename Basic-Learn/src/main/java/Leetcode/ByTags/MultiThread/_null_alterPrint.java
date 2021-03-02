package Leetcode.ByTags.MultiThread;

/**
 * 其他题库
 *   用两个线程，交替打印字母和数字  1A2B3C4D5C...
 * Easy
 */
public class _null_alterPrint {

    public static void main(String[] args) {
       final Object o = new Object();

       new Thread(() -> {
           synchronized (o) {
               for (int i = 1; i <= 10; i++) {
                   System.out.println(i);

                   try {
                       o.notify();
                       o.wait();  // 会释放对象锁
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               o.notify();
           }
       }, "Thread1").start();

       new Thread(() -> {
            synchronized (o) {
                for (char ch = 'A'; ch <= 'J'; ch++) {
                    System.out.println(ch + "");
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
       }, "Thread2").start();
    }
}
