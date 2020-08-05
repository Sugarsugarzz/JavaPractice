package Learn_MultiThread.demo05_state;

// 观察测试线程的状态
public class TestState {

    public static void main(String[] args) throws InterruptedException {

        // 创建线程
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("/////");
        });

        // 观察状态
        Thread.State state = thread.getState();
        System.out.println(state);  // NEW

        // 观察启动后
        thread.start(); // 启动线程
        System.out.println(thread.getState());  // RUNNABLE

        // 观察整个过程
        while (thread.getState() != Thread.State.TERMINATED) {  // 只要线程不终止，就一直输出状态
            Thread.sleep(100);
            System.out.println(thread.getState());  // 输出状态
        }

        // 线程一旦死亡，就不能再启动
    }
}
