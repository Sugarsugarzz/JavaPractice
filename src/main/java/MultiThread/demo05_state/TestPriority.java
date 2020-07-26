package MultiThread.demo05_state;

// 测试线程的优先级
public class TestPriority {

    public static void main(String[] args) {
        // 主线程默认优先级
        System.out.println(Thread.currentThread().getName() + " --> " + Thread.currentThread().getPriority());

        MyPriority myPriority = new MyPriority();

        Thread t1 = new Thread(myPriority);
        Thread t2 = new Thread(myPriority);
        Thread t3 = new Thread(myPriority);
        Thread t4 = new Thread(myPriority);
        Thread t5 = new Thread(myPriority);
        Thread t6 = new Thread(myPriority);

        // 先设置优先级，再启动
        t1.start();
        t2.setPriority(1);
        t2.start();
        t3.setPriority(4);
        t3.start();
        t4.setPriority(Thread.MAX_PRIORITY);
        t4.start();
        t5.setPriority(8);
        t5.start();
        t6.setPriority(7);
        t6.start();

        /*
        优先级低只是一味这获得调度的额概率低，并不是不会被调用了，看CPU的调度。
         */
    }
}

class MyPriority implements  Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " --> " + Thread.currentThread().getPriority());
    }
}