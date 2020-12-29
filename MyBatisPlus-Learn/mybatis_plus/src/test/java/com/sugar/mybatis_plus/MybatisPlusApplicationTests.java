package com.sugar.mybatis_plus;

import com.sugar.mybatis_plus.mapper.UserMapper;
import com.sugar.mybatis_plus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

	// 继承了BaseMapper，所有的方法都来自父类
	// 也可以编写自己的扩展方法
	@Autowired
	private UserMapper userMapper;

	@Test
	void contextLoads() {
		// wapper是一个条件构造器，不用先null
		// 查询全部用户
		List<User> users = userMapper.selectList(null);
		users.forEach(System.out::println);

	}

	// 测试插入
	@Test
	public void testInsert() {
		User user = new User();
		user.setName("Sugar");
		user.setAge(3);
		user.setEmail("qq.com");
		int result = userMapper.insert(user);
		System.out.println(result);
		System.out.println(user);
	}

}
