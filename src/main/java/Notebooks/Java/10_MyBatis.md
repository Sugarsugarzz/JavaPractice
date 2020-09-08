## MyBatis-9.28

环境：

- JDK1.8
- MySQL 5.7
- Maven 3.6.1
- IDEA

前提知识：

- JDBC
- MySQL
- Java基础
- Maven
- Junit

框架：配置文件，最好的方式是看官网文档。

### 1. 简介

### 1.1 MyBatis介绍

- MyBatis 是**持久层框架**

- 支持定制化 SQL、存储过程以及高级映射。

- MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。

- MyBatis 可以使用简单的 XML 或注解来配置和映射原生类型、接口和 Java 的POJO 为数据库中的记录。

- MyBatis 本是 Apache 的一个开源项目 iBatis，于 2010 年迁移到 Google code，改名 MyBatis。

- 2013年11月迁移到 GitHub。

  https://github.com/mybatis/mybatis-3



获取 MyBatis 的方式

- Maven 仓库

  ```xml
  <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
  <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.5</version>
  </dependency>
  ```

- GitHub：https://github.com/mybatis/mybatis-3/releases

- 中文文档：https://mybatis.org/mybatis-3/zh/index.html

#### 1.2 持久化

数据持久化

- 持久化就是将程序的数据在**持久状态**和**瞬时状态**转化的过程。
- 内存：**断电即失**
- 数据库（JDBC）、IO文件持久化（浪费资源）。

**持久化的原因**

- 有一些数据，不能丢掉。
- 内存太贵

### 1.3 持久层

Dao层、Service层、Controller层...

- 完成持久化工作的代码块
- 层是界限十分明显

#### 1.4 需要 MyBatis 的原因

- 实现将数据存入数据库

- 方便，容易上手
- 传统的 JDBC 代码复杂 --> 简化 --> 框架。（自动化）
- 不用 MyBatis 也可以
- 优点：
  - 简单易学
  - 灵活
  - SQL 和代码分离，提高可维护性
  - 提供映射标签，支持对象与数据库的 ORM 字段关系映射
  - 提供对象关系映射标签，支持对象关系组建维护
  - 提供 XML 标签，支持编写动态 SQL

### 2. 第一个 MyBatis 程序

思路：搭建环境 --> 导入MyBatis --> 编写代码 --> 测试

#### 2.1 搭建环境（先建一个主模块）

搭建数据库

```sql
CREATE DATABSES `learn_mybatis`;

USE `learn_mybatis`;

CREATE TABLE `user` (
	`id` INT(20) NOT NULL PRIMARY KEY,
	`name` VARCHAR(30) DEFAULT NULL,
	`pwd` VARCHAR(30) DEFAULT NULL
)ENGINE=INNODB, DEFAULT CHARSET=utf8;

INSERT INTO `user` (`name`, `pwd`) VALUES ('Sugar1', '123456'), ('Sugar2', '123456'), ('Sugar3', '123456');
```

新建项目

1. 新建一个普通 Maven 项目

2. 删除 src 目录（作为主模块）

3. 导入 Maven 依赖

   ```xml
       <!--导入依赖-->
       <dependencies>
           <!--mysql驱动-->
           <dependency>
               <groupId>mysql</groupId>
               <artifactId>mysql-connector-java</artifactId>
               <version>5.1.46</version>
           </dependency>
   
           <!--mybatis-->
           <dependency>
               <groupId>org.mybatis</groupId>
               <artifactId>mybatis</artifactId>
               <version>3.5.5</version>
           </dependency>
   
           <!--junit-->
           <dependency>
               <groupId>junit</groupId>
               <artifactId>junit</artifactId>
               <version>4.12</version>
           </dependency>
       </dependencies>
   ```

#### 2.2 创建子模块

在主模块下新疆子模块，子模块不需要再导依赖。

- 编写 mybatis 的核心配置文件

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
  
  <!--configuration 核心配置文件-->
  <configuration>
  
      <!-- 配置MyBatis运⾏环境 -->
      <environments default="development">
          <environment id="development">
              <!-- 配置JDBC事务管理理 -->
              <transactionManager type="JDBC"></transactionManager>
              <!-- POOLED配置JDBC数据源连接池 -->
              <dataSource type="POOLED">
                  <property name="driver" value="com.mysql.cj.jdbc.Driver"></property>
                  <property name="url" value="jdbc:mysql://localhost:3306/learn_mybatis?useUnicode=true&amp;characterEncoding=UTF-8&amp;useSSL=false"></property>
                  <property name="username" value="root"></property>
                  <property name="password" value="123456"></property>
              </dataSource>
          </environment>
      </environments>
  
  </configuration>
  ```

- 编写 mybatis 工具类

  ```java
  package utils;
  
  import org.apache.ibatis.io.Resources;
  import org.apache.ibatis.session.SqlSession;
  import org.apache.ibatis.session.SqlSessionFactory;
  import org.apache.ibatis.session.SqlSessionFactoryBuilder;
  
  import java.io.IOException;
  import java.io.InputStream;
  
  // sqlSessionFactory  -->  sqlSession
  public class MybatisUtils {
  
      private static SqlSessionFactory sqlSessionFactory;
  
      static {
          try {
              // 使用 MyBatis 第一步：获取 sqlSessionFactory 对象
              String resources = "mybatis-config.xml";
              InputStream inputStream = Resources.getResourceAsStream(resources);
              sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  
      // 既然有了 sqlSessionFactory，就可以从中获得 SqlSession 的实例了。
      // SqlSession 完全包含了面向数据库执行 SQL 命令所需的所有方法。
      public static SqlSession getSqlSession() {
          return sqlSessionFactory.openSession();
      }
  }
  ```

#### 2.3 编写代码

- 实体类

  ```java
  package pojo;
  
  public class User {
  
      private int id;
      private String name;
      private String pwd;
  
      public User() {
      }
  
      public User(int id, String name, String pwd) {
          this.id = id;
          this.name = name;
          this.pwd = pwd;
      }
  
      public int getId() {
          return id;
      }
  
      public void setId(int id) {
          this.id = id;
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public String getPwd() {
          return pwd;
      }
  
      public void setPwd(String pwd) {
          this.pwd = pwd;
      }
  
      @Override
      public String toString() {
          return "User{" +
                  "id=" + id +
                  ", name='" + name + '\'' +
                  ", pwd='" + pwd + '\'' +
                  '}';
      }
  }
  ```

- Dao接口

  ```java
  public interface UserDao {
      List<User> getUserList();
  }
  ```

- 接口实现类（由 UserImpl 转变为一个Mapper.xml配置文件）

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  
  <!--namespace绑定一个对应的Dao/Mapper接口-->
  <mapper namespace="dao.UserDao">
  
      <!--查询语句-->
      <select id="getUserList" resultType="pojo.User">
          SELECT * FROM `user`
      </select>
  
  </mapper>
  ```

#### 2.4 测试

报错一：

```error
org.apache.ibatis.binding.BindingException: Type interface dao.UserDao is not known to the MapperRegistry.
```

**原因**：Mapper.xml 未注册到 mybatis-config.xml 核心配置文件中。

```xml
    <!--每一个Mapper.xml，都需要在 MyBatis 核心配置文件中注册-->
    <mappers>
        <mapper resource="dao/UserMapper.xml" />
    </mappers>
```

报错二：

```error
org.apache.ibatis.exceptions.PersistenceException: 
### Error querying database.  Cause: java.sql.SQLException: Error setting driver on UnpooledDataSource. Cause: java.lang.ClassNotFoundException: Cannot find class: com.mysql.cj.jdbc.Driver
```

 **原因**：Maven 中生成时，对xml文件进行了过滤，需要在 pom.xml 中配置。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200908103317763.png" alt="image-20200908103317763" style="zoom:30%;" />



- Junit测试

  ```java
  public class UserDaoTest {
  
      @Test
      public void test() {
  
          // 第一步：获得 SqlSession 对象
          SqlSession sqlSession = MybatisUtils.getSqlSession();
          // 方式一：getMapper
          UserDao userDao = sqlSession.getMapper(UserDao.class);
          List<User> userList = userDao.getUserList();
  
          for (User user : userList) {
              System.out.println(user);
          }
  
          // 关闭 sqlSession
          sqlSession.clearCache();
      }
  }
  ```



**可能遇到的问题**：

- 配置文件没有注册
- 绑定接口错误
- 方法名不对
- 返回类型不对
- Maven 导出资源问题

