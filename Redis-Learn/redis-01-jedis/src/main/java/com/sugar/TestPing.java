package com.sugar;

import redis.clients.jedis.Jedis;

public class TestPing {
    public static void main(String[] args) {
        // 1、new Jedis
        Jedis jedis = new Jedis("localhost", 6379);
        // jedis 所有命令即 Redis所有指令
        // NOAUTH Authentication required.
        jedis.auth("123456");
        // 连接测试
        System.out.println(jedis.ping());
    }
}
