package MultiThread.demo01;

// 模拟龟兔赛跑
public class Race implements Runnable{

    // 胜利者
    private static String winner;

    @Override
    public void run() {
        // 假设需要100步
        for (int i = 0; i <= 100; i++) {

            // 模拟兔子休息，每十步休息一次
            if (Thread.currentThread().getName().equals("兔子") && i % 10 == 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 判断比赛是否结束
            boolean flag = isGameOver(i);
            // 如果比赛结束了，就停止程序
            if (flag)
                break;

            System.out.println(Thread.currentThread().getName() + " --> 跑了 " + i + " 步");
        }
    }

    // 判断是否完成比赛
    private boolean isGameOver(int steps) {
        // 存在胜利者
        if (winner != null) {
            return true;
        } else {
            if (steps == 100) {
                winner = Thread.currentThread().getName();
                System.out.println("winner is " + winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        Race race = new Race();

        new Thread(race, "兔子").start();
        new Thread(race, "乌龟").start();
    }
}
