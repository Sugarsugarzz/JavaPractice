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

### 3. CRUD

#### 3.1 namespace

namespace 中的包名要与 Dao/Mapper 接口的包名一致。

#### 3.2 select

查询语句

- id：对应namespace中的方法名，必须一致
- parameterType：参数类型
- resultType：SQL 语句执行的返回值

实现步骤：

1. 编写接口

   ```java
   public interface UserMapper {
       // 根据ID查询用户
       User getUserByID(int id);
   }
   ```

2. 编写对应的 Mapper 中的 SQL 语句

   ```xml
       <select id="getUserByID" parameterType="int" resultType="pojo.User">
           SELECT * FROM `user`
           WHERE id = #{id}
       </select>
   ```

3. 测试

   ```java
       @Test
       public void getUserByID() {
           SqlSession sqlSession = MybatisUtils.getSqlSession();
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
   
           User user = mapper.getUserByID(1);
           System.out.println(user);
   
           sqlSession.close();
       }
   ```

#### 3.3 insert

```xml
    <insert id="addUser" parameterType="pojo.User">
        INSERT INTO `user` (`id`, `name`, `pwd`)
        VALUES (#{id}, #{name}, #{pwd})
    </insert>
```

#### 3.4 update

```xml
    <update id="updateUser" parameterType="pojo.User">
        UPDATE `user` SET `name`=#{name}, `pwd`=#{pwd}
        WHERE `id` = #{id}
    </update>
```

#### 3.5 delete

```xml
    <delete id="deleteUser" parameterType="int">
        DELETE FROM `user`
        WHERE `id` = #{id}
    </delete>
```

#### 3.6 注

- 增删改需要提交事务！

#### 3.7 错误分析

- Mapper.xml 文件中，标签与语句不能匹配错
- resources文件 绑定 mapper.xml，需要使用路径
- 程序配置文件必须符合规范
- 输出的 xml 文件中存在中文乱码问题
- Maven 资源没有导出，配置 pom.xml 即可

#### 3.8 万能的Map

假设，实体类，或者数据库中的表，字段或者参数过多，应当考虑使用 Map。

Map 传递参数，直接在 SQL 中取出 key 即可。【parameterType="map"】

对比来说，对象传递参数，直接在 SQL 中取对象的属性即可。【parameterType="pojo.User"】

调用时，只有一个基本类型参数的情况下，可以直接在 SQL 中取到。

多个参数用 Map。**或者注解**！

```java
    // 万能的Map
    int addUser2(Map<String, Object> map);
```

```xml
    <!--万能的Map-->
    <!--传递 map 的 key-->
    <insert id="addUser2" parameterType="map">
        INSERT INTO `user` (`id`, `name`, `pwd`)
        VALUES (#{userid}, #{userName}, #{passWord})
    </insert>
```

```java
    @Test
    public void addUser2() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Map<String, Object> map = new HashMap<>();
        map.put("userid", 5);
        map.put("userName", "heihei");
        map.put("passWord", "222222");
        mapper.addUser2(map);
        sqlSession.commit();
        
        sqlSession.close();
    }
```

#### 3.9 模糊查询

1. Java代码执行的时候，传递通配符 % %

   ```java
   List<User> users = mapper.getUserLike("%Sugar%");
   ```

2. 在 SQL 拼接中使用通配符。

   ```xml
       <select id="getUserLike" resultType="pojo.User">
           SELECT * FROM `user` WHERE `name` LIKE CONCAT("%", #{value}, "%")
       </select>
   ```

### 4. 配置解析

#### 4.1 核心配置文件

- mybatis-config.xml

- MyBatis 的配置文件包含会影响 MyBatis 行为的设置和属性信息。

  ```xml
  - configuration（配置）
  	- properties（属性）
  	- settings（设置）
  	- typeAliases（类型别名）
  	- typeHandlers（类型处理器）
  	- objectFactory（对象工厂）
  	- plugins（插件）
  	- environments（环境配置）
  		- environment（环境变量）
  			- transactionManager（事务管理器）
  			- dataSource（数据源）
  	- databaseIdProvider（数据库厂商标识）
  	- mappers（映射器）
  ```

#### 4.2 环境配置（enviroments）

MyBatis 可以配置成适应多种环境

**记住：尽管可以配置多个环境，但每个 SqlSessionFactory 实例只能选择一种环境。**

学会使用配置多套运行环境。

MyBatis 默认的事务管理器是 **JDBC**，连接池 **POOLED**。

- default：决定默认使用哪套环境
- 事务管理器（transactionManager）
  - **JDBC**
  - MANAGED
- 数据源（dataSource）
  - UNPOOLED：无池
  - **POOLED**：有池，响应更快
  - JNDI：正常连接

#### 4.3 属性（properties）

可以通过 properties 属性来实现引用配置文件。

可以属性都是可外部配置且可动态替换的，既可以在典型的 Java 属性文件中配置，也可以通过 properties 元素的子元素来传递（为了解耦）。【db.properties】

编写一个配置文件 db.properties

```xml
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/learn_mybatis?useUnicode=true&characterEncoding=UTF-8&amp;useSSL=false
username=root
password=123456
```

在核心配置文件中引入

```xml
    <!--引入外部配置文件 properties-->
    <properties resource="db.properties">
        <property name="username" value="root"/>
        <property name="password" value="111111"/>
    </properties>
```

- 可以直接引入外部文件
- 可以在其中增加一些属性配置
- 如果两个文件有同一个字段，**优先使用外部配置文件的**！

#### 4.4 类型别名（typeAliases）

- 类型别名是为 Java 类型设置一个短的名字。
- 它只和 XML 配置有关，存在的意义仅在于用来接减少类完全限定名的冗余。

**typeAlias**：直接指定实体类名，并设置一个别名。

```xml
    <!--类型别名：给实体类起别名-->
    <typeAliases>
        <typeAlias type="pojo.User" alias="User"/>
    </typeAliases>
```

**package**：指定一个包名，MyBatis 会在包名下搜索需要的 Java Bean，以实体类的类名作为别名，不区分大小写。

```xml
    <!--类型别名：给实体类起别名-->
    <typeAliases>
        <!--以实体类类名作为别名，不区分大小写-->
        <package name="pojo"/>
    </typeAliases>
```

在实体类比较少的时候，使用第一种方式；如果实体类多，使用第二种。

区别：

- 第一种可以DIY别名，第二种不行（**也行的，在实体类上添加 @Alias("xx") 的注解**）。
- 基本类型的别名前面加_，对象类型的别名是小写。（ int -> _int，Integer -> int）

#### 4.5 设置（settings）

MyBatis 中极为重要的调整设置，会改变 MyBatis 的运行时行为。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200909192902102.png" alt="image-20200909192902102" style="zoom:50%;" />

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200909192852180.png" alt="image-20200909192852180" style="zoom:50%;" />

#### 4.6 其他配置

- typeHandlers（类型处理器）
- objectFactory（对象工厂）
- plugins插件
  - mybatis-generator-core
  - mybatis-plus
  - 通用mapper

#### 4.7 映射器（mappers）

MapperRegistry：注册绑定 Mapper.xml 文件

方式一：指定 Mapper.xml 文件相对路径

```xml
    <mappers>
        <mapper resource="dao/UserMapper.xml" />
    </mappers>
```

方式二：使用 class 文件绑定注册

- 接口和它的 Mapper.xml 配置文件必须同名
- 接口和它的 Mapper.xml 配置文件必须在同一个包下（Mapper文件放src下，Mapper.xml放resources下，保证路径一致即可）

```xml
    <mappers>
        <mapper class="dao.UserDao"/>
    </mappers>
```

方式三：使用扫描包进行注入绑定

- 接口和它的 Mapper.xml 配置文件必须同名
- 接口和它的 Mapper.xml 配置文件必须在同一个包下（Mapper文件放src下，Mapper.xml放resources下，保证路径一致即可）

```xml
    <mappers>
        <package name="dao"/>
    </mappers>
```

#### 5.8 生命周期和作用域

生命周期是至关重要的，因为错误的使用会导致非常严重的**并发问题**。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200909194635681.png" alt="image-20200909194635681" style="zoom:30%;" />



**SqlSessionFactoryBuilder**：

- 一旦创建了 SqlSessionFactory，就不再需要它了。
- 局部变量

**SqlSessionFactory**：

- 其实就是：数据库连接池
- SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，**没有任何理由丢弃或重新创建另一个实例**
- 最佳作用域是应用作用域。
- 最简单的就是**单例模式**或者静态单例模式。

**SqlSession**：

- 连接到连接池的一个请求
- SqlSession 的实例不是线程安全的，因此不能被共享
- 最佳作用域就是请求或方法应用域
- 用完需要关闭，否则资源被占用

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200909195347071.png" alt="image-20200909195347071" style="zoom:30%;" />

每一个 Mapper，代表一个具体的业务。

### 5. ResultMap（解决属性名和字段名不一致的问题）

```java
数据库：
  id  name  pwd
Java：
  id  name  password
```

**测试出现问题**：

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200909200442263.png" alt="image-20200909200442263" style="zoom:50%;" />

**原因**：

MyBatis 使用 selecet id, name, pwd from user where id = #{id}，而 User 类中字段为 password，映射不上。

**解决方法**：

- 起别名

  ```sql
  SELECT `id`, `name`, `pwd` as `password`  
  FROM `user`
  WHERE id = #{id}
  ```

- resultMap

  结果集映射

  ```xml
      <!--结果集映射-->
      <resultMap id="UserMap" type="User">
          <!--column：数据库中的字段，property：实体类中的属性-->
          <result column="id" property="id" />
          <result column="name" property="name" />
          <result column="pwd" property="password" />
      </resultMap>
  
      <select id="getUserByID" resultMap="UserMap">
          SELECT `id`, `name`, `pwd`
          FROM `user`
          WHERE id = #{id}
      </select>
  ```

  - resultMap 元素是 MyBatis 中最重要最强大的元素
  - ResultMap 的设计思想是，对于简单的语句根本不需要配置显式的结果映射，而对于复杂一点的语句只需要描述它们的关系即可。
  - ResultMap 最优秀的地方在于，虽然已对它相当了解，但是根本不需要显式地用到它们。

### 6. 日志

#### 6.1 日志工厂

如果一个数据库操作，出现了异常，需要排错，日志是最好的助手。

曾经：sout、debug

现在：日志工厂！

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200910123546275.png" alt="image-20200910123546275" style="zoom:50%;" />

- SLF4J
- LOG4J【掌握】
- LOG4J2
- JDK_LOGGING
- COMMONS_LOGGING
- STDOUT_LOGGING【掌握】
- NO_LOGGING

在 MyBatis 中具体使用哪一个日志实现，在 settings 中设定。

**STDOUT_LOGGING 在标准日志输出**

在 MyBatis 核心配置文件中，配置日志。

```xml
    <settings>
				<!--标准日志工厂-->
        <setting name="logImpl" value="STDOUT_LOGGING"/>
    </settings>
```

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200910124119985.png" alt="image-20200910124119985" style="zoom:40%;" />

#### 6.2 Log4j

Log4j：

- Log4j 是 Apache 的一个开源项目，通过使用 Log4j，可以控制日志信息输送的目的地是控制台、文件、GUI组件。
- 也可以控制每一条日志的输出格式。
- 通过定义每一条日志信息的级别，能够更加细致地控制日志的生成过程。
- 通过一个配置文件来灵活进行配置，而不需要修改应用的diamante

1. 导入 log4j 的包。

   ```xml
       <dependency>
           <groupId>log4j</groupId>
           <artifactId>log4j</artifactId>
           <version>1.2.17</version>
       </dependency>
   ```

2. log4j.properties

   ```xml
   #将等级为DEBUG的日志信息输出到console和file这两个目的地，console和file的定义在下面的代码
   log4j.rootLogger=DEBUG,console,file
   
   #控制台输出的相关设置
   log4j.appender.console = org.apache.log4j.ConsoleAppender
   log4j.appender.console.Target = System.out
   log4j.appender.console.Threshold=DEBUG
   log4j.appender.console.layout = org.apache.log4j.PatternLayout
   log4j.appender.console.layout.ConversionPattern=[%c]-%m%n
   
   #文件输出的相关设置
   log4j.appender.file = org.apache.log4j.RollingFileAppender
   log4j.appender.file.File=./log/debug.log
   log4j.appender.file.MaxFileSize=10mb
   log4j.appender.file.Threshold=DEBUG
   log4j.appender.file.layout=org.apache.log4j.PatternLayout
   log4j.appender.file.layout.ConversionPattern=[%p][%d{yy-MM-dd}][%c]%m%n
   
   #日志输出级别
   log4j.logger.org.mybatis=DEBUG
   log4j.logger.java.sql=DEBUG
   log4j.logger.java.sql.Statement=DEBUG
   log4j.logger.java.sql.ResultSet=DEBUG
   log4j.logger.java.sql.PreparedStatement=DEBUG
   ```

3. 配置 lo4j 为日志的实现方式

   ```xml
       <settings>
           <setting name="logImpl" value="LOG4J"/>
       </settings>
   ```

**简单使用**：

1. 在要使用 log4j 的类中，导入包

   ```java
   import org.apache.log4j.Logger;
   ```

2. 生成日志对象，参数为当前类的 class

   ```java
   static Logger logger = Logger.getLogger(UserDaoTest.class);
   ```

3. 日志级别

   ```java
   logger.info("info 进入了 testLog4j 方法");
   logger.debug("debug 进入了 testLog4j 方法");
   logger.error("error 进入了 testLog4j 方法");
   ```

### 7. 分页

目的：

- 减少数据的处理量

#### 7.1 使用 limit 分页

```sql
语法：
SELECT * FROM `user` LIMIT startIndex, pageSize;
SELECT * FROM `user` LIMIT n;  #[0, n]
```

**使用 MyBatis 实现分页，核心SQL**

1. 接口

   ```java
       // 分页
       List<User> getUserByLimit(Map<String, Integer> map);
   ```

2. Mapper.xml

   ```xml
       <!--分页查询-->
       <select id="getUserByLimit" parameterType="map" resultMap="UserMap">
           SELECT * FROM `user` LIMIT #{startIndex}, #{pageSize}
       </select>
   ```

3. 测试

   ```java
       @Test
       public void getUserByLimit() {
   
           SqlSession sqlSession = MybatisUtils.getSqlSession();
           UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
   
           Map<String, Integer> map = new HashMap<>();
           map.put("startIndex", 0);
           map.put("pageSize", 2);
           List<User> users = userMapper.getUserByLimit(map);
           System.out.println(users);
   
           sqlSession.clearCache();
       }
   ```

#### 7.2 RowBounds 分页（不建议在实际开发中使用）

不再使用 SQL 实现分页

1. 接口

   ```java
       // RowBounds
       List<User> getUserByRowBounds();
   ```

2. Mapper.xml

   ```xml
       <!--RowBounds查询-->
       <select id="getUserByRowBounds" resultMap="UserMap">
           SELECT * FROM `user`
       </select>
   ```

3. 测试

   ```java
       @Test
       public void getUserByRowBounds() {
   
           SqlSession sqlSession = MybatisUtils.getSqlSession();
   
           // RowBounds 实现（实际开发中用得少了）
           RowBounds rowBounds = new RowBounds(1, 2);
   
           // 通过 Java 代码层面实现分页
           List<User> users = sqlSession.selectList("dao.UserMapper.getUserByRowBounds", null, rowBounds);
           for (User user : users) {
               System.out.println(user);
           }
   
           sqlSession.clearCache();
       }
   ```

#### 7.3 分页插件

- MyBatis PageHelper（了解即可）

### 8. 使用注解开发

#### 8.1 面向接口编程

- 实际开发中，大多时候选择面向接口编程，而非面向对象编程
- **根本原因**：**解耦**、可扩展、提高复用，分层开发中，上层不用管具体的实现，开发者遵守共同的标准，使得开发容易、规范。
- 在面向对象的系统中，系统的各种功能是由许许多多不同对象协作完成的。各个对象内部如何实现的，对系统设计人员来说并不重要。
- 而各个对象之间协作关系则成为系统设计的关键。小到不同类之间的通信，大到各模块之间的交互，在系统设计之初都是需要考虑的，这也是系统设计的主要工作内容，面向接口编程就是指按照这种思想来编程。

**关于接口的理解**

- 接口从更深层次的理解，应该是定义（规范、约束）与实现（名实分离的原则）的分离。
- 接口的本身反映了系统设计人员对系统的抽象理解。
- 接口应有两类：
  - 第一类是对一个个体的抽象，对应为一个抽象类（abstract class）
  - 第二类是对一个个体某一方面的抽象，形成一个抽象面（interface）
- 一个个体可能有多个抽象面。抽象体与抽象面是由区别的。

**三个面向区别**

- 面向对象，考虑问题时，以对象为单位，考虑它的属性及方法。
- 面向过程，考虑问题时，以一个具体的流程（事务过程）为单位，考虑它的实现流程。
- 接口设计与非接口设计是针对复用技术而言的，与面向对象（过程）不是一个问题，更多的体现就是对系统整体的架构。

#### 8.2 注解开发

1. 注解在接口上实现

   ```java
       @Select("SELECT * FROM `user`")
       List<User> getUsers();
   ```

2. 需要在核心配置文件中绑定接口（必须 class 实现）

   ```xml
       <mappers>
           <mapper class="dao.UserMapper" />
       </mappers>
   ```

3. 测试

   ```java
       @Test
       public void test() {
   
           SqlSession sqlSession = MybatisUtils.getSqlSession();
           // 底层主要通过反射来实现
           UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
           List<User> users = userMapper.getUsers();
           for (User user : users) {
               System.out.println(user);
           }
   
           sqlSession.clearCache();
       }
   ```

**本质**：反射机制实现

**底层**：动态代理

**MyBatis详细执行流程！**

#### 8.3 CRUD

一般简单的SQL可以用接口，不然还是 XML 好一些。

可以在工具类创建的时候实现自动提交事务。

```java
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession(true);
    }
```

编写接口，增加注解

```java
public interface UserMapper {

    @Select("SELECT * FROM `user`")
    List<User> getUsers();

    // 方法存在多个参数，所有的参数前面必须加上 @Param 注解
    @Select("SELECT * FROM `user` WHERE id = #{id}")
    User getUserByID(@Param("id") int id);

    @Insert("INSERT INTO `user`(`id`, `name`, `pwd`) VALUES (#{id}, #{name}, #{password})")
    int addUser(User user);

    @Update("UPDATE `user` SET name=#{name}, pwd=#{password} WHERE id=#{id}")
    int updateUser(User user);

    @Delete(("DELETE FROM `user` WHERE id = #{id}"))
    int deleteUser(@Param("id") int id);
}
```

测试

【注意，必须将接口类绑定到核心配置文件】

```java
    @Test
    public void test() {

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 底层主要通过反射来实现
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        List<User> users = userMapper.getUsers();
//        for (User user : users) {
//            System.out.println(user);
//        }

//        User user = userMapper.getUserByID(1);
//        System.out.println(user);

//        userMapper.addUser(new User(6, "Sugar6", "123123"));

//        userMapper.updateUser(new User(6, "Sugar666", "123123"));

        userMapper.deleteUser(5);

        sqlSession.close();
    }
```

#### 8.4 @Param() 注解

- 基本类型的参数或者String类型的，需要加上
- 引用类型不需要加
- 如果只有一个基本类型的话，可以忽略，但是建议都加上
- 在 SQL 中引用的，就是这里的 @Param("") 中设定的属性名。

#### 8.5 ${} 和 #{} 的区别

//TODO

### 9. Lombok

使用步骤：

1. 在 IDEA 中安装 Lombok 插件。（Preferences  -->  Plugins）

2. 在项目中导入 Lombok 的jar包。

   ```xml
   <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
   <dependency>
       <groupId>org.projectlombok</groupId>
       <artifactId>lombok</artifactId>
       <version>1.18.12</version>
       <scope>provided</scope>
   </dependency>
   ```

3. 在实体类上加注解

   ```java
   @Alias("user")
   @Data
   @AllArgsConstructor
   public class User {
   
       private int id;
       private String name;
       private String password;
   }
   ```

**@Data**：无参构造、Getter、Setter、toString、hashCode、equals

**@AllArgsConstructor**：有参构造

**@NoArgsConstructor**：无参构造

```java
@Getter and @Setter
@FieldNameConstants
@ToString
@EqualsAndHashCode
@AllArgsConstructor, @RequiredArgsConstructor and @NoArgsConstructor
@Log, @Log4j, @Log4j2, @Slf4j, @XSlf4j, @CommonsLog, @JBossLog, @Flogger, @CustomLog
@Data
@Builder
@SuperBuilder
@Singular
@Delegate
@Value
@Accessors
@Wither
@With
@SneakyThrows
@val
@var
experimental @var
@UtilityClass
```

### 10. 多对一处理

- 多个学生，对应一个老师
- 对于学生而言，**关联**...多个学生，关联一个老师【多对一】
- 对于老师而言，**集合**...一个老师有很多学生【一对多】

```sql
CREATE TABLE `teacher` (
`id` INT(10) NOT NULL,
`name` VARCHAR(30) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO teacher(`id`, `name`) VALUES (1, '秦老师');

CREATE TABLE `student` (
`id` INT(10) NOT NULL,
`name` VARCHAR(30) DEFAULT NULL,
`tid` INT(10) DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `fktid` (`tid`),
CONSTRAINT `fktid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('1', '小明', '1');
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('2', '小红', '1');
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('3', '小张', '1');
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('4', '小李', '1');
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('5', '小王', '1');
```

测试环境搭建

1. 导入lombok

2. 新建实体类 Teacher、Student

   ```java
   @Data
   public class Teacher {
   
       private int id;
       private String name;
   }
   
   @Data
   public class Student {
   
       private int id;
       private String name;
       // 学生需要关联一个老师
       private Teacher teacher;
   }
   ```

3. 建立 Mapper 接口

4. 建立 Mapper.xml 文件

5. 在核心配置文件中绑定注册 Mapper 接口或者文件【多种方式】

6. 测试查询是否成功

#### 按照查询嵌套处理（子查询）

```xml
    <!--
    思路：
        1. 查询所有的学生信息
        2. 根据查询出来学生的tid，寻找对应的老师信息
    -->
    <select id="getStudent" resultMap="StudentTeacher">
        SELECT * FROM student
    </select>

    <resultMap id="StudentTeacher" type="Student">
        <result column="id" property="id"/>
        <result column="name" property="name"/>
        <!--复杂的属性，需要单独处理
            对象：association
            集合：collection
        -->
        <association column="tid" property="teacher" javaType="Teacher" select="getTeacher"/>
    </resultMap>

    <select id="getTeacher" resultType="Teacher">
        SELECT * FROM teacher WHERE id = #{id}
    </select>
```

#### 按照结果嵌套处理（联表查询）（推荐）

```xml
    <!--按照结果嵌套处理-->
    <select id="getStudent2" resultMap="StudentTeacher2">
        SELECT s.id sid, s.name sname, t.name tname
        FROM student s, teacher t
        WHERE s.tid = t.id
    </select>

    <resultMap id="StudentTeacher2" type="Student">
        <result property="id" column="sid"/>
        <result property="name" column="sname"/>
        <association property="teacher" javaType="Teacher">
            <result property="name" column="tname"/>
        </association>
    </resultMap>
```

回顾 MySQL 多对一查询方式：

- 子查询
- 联表查询

### 11. 一对多处理

比如：一个老师拥有多个学生，对于老师而言，就是一对多的关系！

搭建环境，同10

```java
@Data
public class Teacher {

    private int id;
    private String name;
    // 一个老师拥有多个学生
    private List<Student> students;
}

@Data
public class Student {

    private int id;
    private String name;
    private int tid;
}
```

#### 按照结果嵌套处理

```xml
    <!--按结果嵌套查询-->
    <select id="getTeacher" resultMap="TeacherStudent">
        SELECT s.id sid, s.name sname, t.name tname, t.id tid
        FROM student s, teacher t
        WHERE s.tid = t.id and t.id =#{tid}
    </select>

    <resultMap id="TeacherStudent" type="Teacher">
        <result property="id" column="tid"/>
        <result property="name" column="tname"/>
        <!--复杂属性需要单独处理，对象：association 集合：collection
        javaType="" 指定属性的类型
        集合中的泛型信息，使用 ofType 获取
        -->
        <collection property="students" ofType="Student">
            <result property="id" column="sid"/>
            <result property="name" column="sname"/>
            <result property="tid" column="tid"/>
        </collection>
    </resultMap>
```

#### 按照查询嵌套处理

```xml
    <!--按查询嵌套查询-->
    <select id="getTeacher2" resultMap="TeacherStudent2">
        SELECT * FROM teacher WHERE id = #{tid}
    </select>

    <resultMap id="TeacherStudent2" type="Teacher">
        <collection property="students" javaType="ArrayList" ofType="Student" select="getStudentByTeacherId" column="id"/>
    </resultMap>

    <select id="getStudentByTeacherId" resultType="Student">
        SELECT * FROM student WHERE tid = #{tid}
    </select>
```

#### 小结

1. 关联 - association 【多对一】
2. 集合 - collection 【一对多】
3. javaType  & ofType
   - javaType 用来指定实体类中属性的类型
   - ofType 用来指定映射到List或者集合中的 pojo 类型，泛型中的约束类型！

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200910235757923.png" alt="image-20200910235757923" style="zoom:40%;" />

**注意点**

- 保证 SQL 的可读性，尽量通俗易懂
- 注意一对多和多对一中，属性名和字段的问题
- 如果问题不好排查，使用日志（Log4j）

**面试高频**

- MySQL 引擎
- innoDB底层原理
- 索引
- 索引优化

### 12. 动态 SQL

**动态SQL：根据不同条件，生成不同的 SQL语句。**

> 动态 SQL 元素和 JSTL 或基于类似 XML 的文本处理器相似。在 MyBatis 之前的版本中，有很多元素需要花时间了解。
>
> 在 MyBatis3 大大精简了元素种类，只需要学习原来一半的元素即可。
>
> MyBatis 采用功能强大基于 OGNL 的表达式来淘汰其他大部分元素。
>
> - if
> - choose（when、otherwise）
> - trim（where、set）
> - foreach

#### 搭建环境

```sql
CREATE TABLE `blog`(
`id` VARCHAR(50) NOT NULL COMMENT '博客id',
`title` VARCHAR(100) NOT NULL COMMENT '博客标题',
`author` VARCHAR(30) NOT NULL COMMENT '博客作者',
`create_time` DATETIME NOT NULL COMMENT '创建时间',
`views` INT(30) NOT NULL COMMENT '浏览量'
)ENGINE=INNODB DEFAULT CHARSET=utf8
```

创建基础工程

1. 导包

2. 编写配置文件

3. 编写实体类

   ```java
   @Data
   public class Blog {
       private int id;
       private String title;
       private String author;
       private Date createTime;
       private int views;
   }
   ```

4. 编写实体类对应Mapper接口和Mapper.xml文件

#### IF

```xml
    <select id="queryBlogIF" parameterType="map" resultType="Blog">
        SELECT * FROM blog 
        <where>
            <if test="title != null">
                and title = #{title}
            </if>
            <if test="author != null">
                and author = #{author}
            </if>            
        </where>
    </select>
```

#### choose（when、otherwise）

```xml
    <select id="queryBlogChoose" parameterType="map" resultType="Blog">
        SELECT * FROM blog
        <where>
            <choose>
                <when test="title != null">
                    title = #{title}
                </when>
                <when test="author != null">
                    author = #{author}
                </when>
                <otherwise>
                    views = #{views}
                </otherwise>
            </choose>
        </where>
    </select>
```

#### trime（where、set）

```xml
    <update id="updateBlog" parameterType="map">
        UPDATE blog
        <set>
            <if test="title != null">
                title = #{title}
            </if>
            <if test="author != null">
                author = #{author}
            </if>
        </set>
        WHERE id = #{id}
    </update>
```

#### 小结

**所谓的动态 SQL，本质还是 SQL 语句，只是可以在 SQL 层面执行一些逻辑代码**

