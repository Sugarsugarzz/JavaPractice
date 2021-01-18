package com.sugar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sugar.pojo.User;
import com.sugar.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
class Redis02SpringbootApplicationTests {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private RedisUtil redisUtil;

	@Test
	void contextLoads() {

		// 在企业开发中，80%情况下都不会使用这种原生的方式去编写原生代码。  ==>  RedisUtils

		// redisTemplate  操作不同的数据类型，opt和Redis指令一样
		// opsForValue  操作字符串 类型String
		// opsForList   操作List  类型String
		// opsForSet
		// opsForHash
		// opsForZSet
		// opsForGeo
		// opsForHyperLoglog

		// 除了基本的操作，常用的方法都可以直接redisTemplate操作，比如事务、基本的CRUD
//		RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//		connection.flushDb();
//		connection.flushAll();

		redisTemplate.opsForValue().set("mykey", "sugar");
		System.out.println(redisTemplate.opsForValue().get("mykey"));
	}

	@Test
	void test() throws JsonProcessingException {
		// 真实开发使用JSON来传递对象
		User user = new User("sugar", 3);
//		String jsonUser = new ObjectMapper().writeValueAsString(user);
//		redisTemplate.opsForValue().set("user", user);  // 直接传递对象会报错，需要将对象序列化
//		redisTemplate.opsForValue().set("user", jsonUser);

		redisTemplate.opsForValue().set("user", user);
		System.out.println(redisTemplate.opsForValue().get("user"));
	}

	@Test
	void testRedisUtil() {
		redisUtil.set("name", "sugar");
		System.out.println(redisUtil.get("name"));
	}
}
