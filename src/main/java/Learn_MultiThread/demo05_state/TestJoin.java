package Learn_MultiThread.demo05_state;

// 测试join方法
// 想象为插队
public class TestJoin implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("线程VIP来了 " + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // 启动线程
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();

        // 主线程
        for (int i = 0; i < 1000; i++) {
            if (i == 50) {
                // 插队（让子线程插入主线程）
                thread.join();  // 让子线程执行完，再执行主线程
            }
            System.out.println("main " + i);
        }
    }
}
