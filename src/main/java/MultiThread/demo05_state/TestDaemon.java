package MultiThread.demo05_state;

// 测试守护线程
// 上帝守护你
public class TestDaemon {

    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread thread = new Thread(god);
        thread.setDaemon(true);  // 默认是false，表示是用户线程（正常的线程都是用户线程）

        thread.start();  // 上帝 守护线程 启动

        new Thread(you).start();  // 你 用户线程 启动

        /*
            守护线程会随着用户线程一直执行。
            虚拟机在保证用户线程执行完毕后，就会关闭，不会等待守护线程执行完毕。
         */
    }
}

// 上帝
class God implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("上帝保佑着你");
        }
    }
}

// 你
class You implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("一生都开心活着");
        }
        System.out.println("====== Good Bye =======");
    }
}
