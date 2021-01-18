package com.sugar;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTransaction {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhsot", 6379);
        jedis.auth("123456");

        JSONObject obj = new JSONObject();
        obj.put("hello", "world");
        obj.put("name", "sugar");
        // 开启事务
        Transaction multi = jedis.multi();

        try {
            multi.set("user1", obj.toJSONString());
            multi.set("user2", obj.toJSONString());
            multi.exec();  // 执行事务
        } catch (Exception e) {
            multi.discard();  // 放弃事务
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close();  // 关闭连接
        }

    }
}
