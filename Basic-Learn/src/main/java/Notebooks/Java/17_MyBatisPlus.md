### 1 简介

官网：https://mp.baomidou.com

- **无侵入**：只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑
- **损耗小**：启动即会自动注入基本 CURD，性能基本无损耗，直接面向对象操作
- **强大的 CRUD 操作**：内置通用 Mapper、通用 Service，仅仅通过少量配置即可实现单表大部分 CRUD 操作，更有强大的条件构造器，满足各类使用需求
- **支持 Lambda 形式调用**：通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错
- **支持主键自动生成**：支持多达 4 种主键策略（内含分布式唯一 ID 生成器 - Sequence），可自由配置，完美解决主键问题
- **支持 ActiveRecord 模式**：支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大的 CRUD 操作
- **支持自定义全局通用操作**：支持全局通用方法注入（ Write once, use anywhere ）
- **内置代码生成器**：采用代码或者 Maven 插件可快速生成 Mapper 、 Model 、 Service 、 Controller 层代码，支持模板引擎，更有超多自定义配置等您来使用
- **内置分页插件**：基于 MyBatis 物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同于普通 List 查询
- **分页插件支持多种数据库**：支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer 等多种数据库
- **内置性能分析插件**：可输出 Sql 语句以及其执行时间，建议开发测试时启用该功能，能快速揪出慢查询
- **内置全局拦截插件**：提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误操作

### 2 快速入门

1. 导入依赖
2. 研究依赖如何配置
3. 代码如何编写
4. 提高扩展技术能力

> 步骤

1. 创建数据库 `mybatis_plus`

2. 创建 user 表

   ```sql
   DROP TABLE IF EXISTS user;
   
   CREATE TABLE user
   (
   	id BIGINT(20) NOT NULL COMMENT '主键ID',
   	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
   	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
   	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
   	PRIMARY KEY (id)
   );
   -- 真实开发中，version（乐观锁）、deleted（逻辑删除）、gmt_create、gmt_modified
   ```

3. 编写项目，使用 SpringBoot 初始化。

4. 导入依赖

   ```xml
   		<dependency>
   			<groupId>com.baomidou</groupId>
   			<artifactId>mybatis-plus-boot-starter</artifactId>
   			<version>3.2.0</version>
   		</dependency>
   ```

5. 连接数据库

   ```yml
   spring:
     datasource:
       username: root
       password: 123456
       url: jdbc:mysql://localhost:3306/mybatis_plus?useSSL=false&useUnicode=true&characterEncoding=utf-8
       driver-class-name: com.mysql.cj.jdbc.Driver
   ```

6. **传统方式**： pojo - dao（连接 mybatis，配置 mapper.xml 文件） - service -controller

7. **mybatis-plus 方式**：

   - pojo

     ```java
     @Data
     @AllArgsConstructor
     @NoArgsConstructor
     public class User {
     
         private Long id;
         private String name;
         private Integer age;
         private String email;
     }
     ```

   - mapper接口

     ```java
     // 在对应的Mapper上实现基本的接口
     @Repository
     public interface UserMapper extends BaseMapper<User> {
         // 所有的CRUD操作已完成，不需要像以前一样都写出来
     }
     ```

   - 需要在主启动类上扫描mapper接口

     ```java
     @MapperScan("com.sugar.mybatis_plus.mapper")
     ```

   - 测试

     ```java
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
     ```

### 3 配置日志

所有 sql 是不可见的，必须知道它是怎么执行的。

```yml
# 配置日志
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

**4 CRUD扩展**

#### 4.1 INSERT

```java
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
```

> 数据库插入的 id 为默认值：全局的唯一 id

**主键生成策略：雪花算法！**

​	snowflake 是 Twitter 开源的分布式 ID 生成算法，结果是一个 long 型的 ID，核心思想是：使用 41bit 作为毫秒数， 10bit 作为机器的 ID（5个bit是数据中心：北京、上海等，5个bit的机器ID），12bit作为毫秒内的流水号（意味着每个节点在每毫秒可以产生 4096 个 ID），最后还有一个符号位，永远是0。

```java
// 默认的 ，可以自行设置
@TableId(type = IdType.ID_WORKER)
private Long id;
```

**主键自增**

1. 实体类字段上 `@TableId(type = IdType.AUTO)`
2. 数据库字段一定要自增