package Learn_MultiThread.demo05_state;

// 模拟网络延时：放大问题的发生性
public class TestSleep implements Runnable{

    // 票数
    private int ticketNums = 10;

    @Override
    public void run() {
        while (true) {
            if (ticketNums <= 0)
                break;

            // 模拟延时
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " ---> 拿到了 " + ticketNums-- + " 票");
        }
    }

    public static void main(String[] args) {
        TestSleep ticket = new TestSleep();

        // 出现多人抢到同一张票的情况，这是正常情况
        // 不加延时就是一个人瞬间抢到所有票
        new Thread(ticket, "小明").start();
        new Thread(ticket, "老师").start();
        new Thread(ticket, "黄牛党").start();
    }
}
