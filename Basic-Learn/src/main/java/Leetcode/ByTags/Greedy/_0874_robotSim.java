package Leetcode.ByTags.Greedy;

import java.util.HashSet;
import java.util.Set;

/**
 * 874. 模拟行走机器人
 * Easy
 */

public class _0874_robotSim {

    public static void main(String[] args) {

        int[] commands = {4, -1, 4, -2, 4};
        int[][] obstacles = {{2, 4}};

        int res = robotSim(commands, obstacles);
        System.out.println(res);
    }

    public static int robotSim(int[] commands, int[][] obstacles) {

        // direction表示朝向的下标，0123分别代表北东南西
        // 北 -> Direction[0] -> {0,1} -> x坐标+0,y坐标+1
        int res = 0, direction = 0, x = 0, y = 0;
        int[][] Direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // 存障碍物的坐标
        Set<String> set = new HashSet<>();
        for (int[] obs: obstacles)
            set.add(obs[0] + "," + obs[1]);

        // 执行移动命令
        for (int com: commands) {
            // 预先定义下一步的坐标
            int next_x = 0, next_y = 0;
            // 前进命令
            if (com >= 0) {
                // 分为一步步走
                for (int i = 0; i < com; i++) {
                    // 取得下一步的坐标
                    next_x = x + Direction[direction][0];
                    next_y = y + Direction[direction][1];
                    // 如果存在障碍，则暂停
                    if (set.contains(next_x + "," + next_y))
                        break;
                    // 更新当前坐标与欧氏距离
                    x = next_x;
                    y = next_y;
                    res = Math.max(res, x*x + y*y);
                }
            }
            // 转向命令
            else {
                direction = com == -1 ? (direction + 1) % 4 : (direction + 3) % 4;
            }
        }
        return res;
    }
}
