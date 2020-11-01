//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.sugar.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SugarRule extends AbstractLoadBalancerRule {

    // 每个机器访问5次，换下一个服务（3个）
    // total=0，如果为5则指向下一个服务节点
    // index=0，如果total=5，index++，如果index>3，total和index置为0

    private int total = 0;  // 调用次数
    private int currentIndex = 0;  // 当前提供服务的

    @SuppressWarnings({"RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE"})
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                List<Server> upList = lb.getReachableServers();  // 获得还活着的服务
                List<Server> allList = lb.getAllServers();  // 获得全部的fuwu
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }

//                int index = this.chooseRandomInt(serverCount);  // 生成区间随机数
//                server = (Server)upList.get(index);

                // 自定义算法部分
                // ================================================
                if (total < 5) {
                     server = upList.get(currentIndex);
                     total++;
                } else {
                    total = 0;
                    currentIndex++;
                    if (currentIndex > upList.size()) {
                        currentIndex = 0;
                    }
                    server = upList.get(currentIndex);
                }
                // ================================================
                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
