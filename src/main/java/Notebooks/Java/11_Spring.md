# Spring

### 1.1 简介

- 2002年，首次推出 Spring 框架的雏形：interface21框架
- Spring 框架以 interface21 框架为基础，经过重新设计并丰富内涵，于2004年3月24日发布了1.0正式版。
- **Rod Johnson**，Spring Framework 创始者，悉尼大学音乐学博士（震惊）。
- Spring理念：**使现有的技术更加容易使用**，本身是一个大杂烩，整合了现有的技术框架。



- SSH：Struct2 + Spring + Hibernate
- SSM：SpringMVC + Spring + MyBatis



官网：https://spring.io/projects/spring-framework#overview

官方下载地址：http://repo.spring.io/release/org/springframework/spring

GitHub：https://github.com/spring-projects/spring-framework

Maven：导一个最大的，maven会自动将其他的导入。

```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.2.0.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.2.0.RELEASE</version>
</dependency>

```

#### 1.2 优点

- Spring 是一个开源、免费的框架（容器）。
- 轻量级、非入侵式（无需修改代码）的框架。
- **控制反转（IOC），面向切面编程（AOP）**。
- 支持h事务的处理，对框架整合的支持。

总结：**Spring 就是一个轻量级的控制反转和面向切面编程的框架。**

#### 1.3 组成

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200911123521147.png" alt="image-20200911123521147" style="zoom:35%;" />

#### 1.4 拓展

在 Spring 的官网有这个介绍：现代化的 Java 开发，就是基于 Spring 的开发。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200911123837881.png" alt="image-20200911123837881" style="zoom:30%;" />

- Spring Boot
  - 快速开发的脚手架
  - 基于 SpringBoot 可以快速的开发单个微服务
  - **约定大于配置**
- Spring Cloud
  - SpringCloud 是基于 SpringBoot 实现的

**学习 SpringBoot 的前提，需要完全掌握 Spring 及 SpringMVC（承上启下）。**

**弊端**：Spring 发展太久后，违背了原来的理念！配置繁琐，”配置地域“！

### 2. IOC 理论推导

之前：

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200911151707944.png" alt="image-20200911151707944" style="zoom:20%;" />

IOC：

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200911151729551.png" alt="image-20200911151729551" style="zoom:30%;" />

见 **spring-01-ic1** Demo，理解 IOC 的思想。
手动在 Service 创建多个 UserDaoXXX 对象，不如被动接收对象，交控制权交给用户。

1. UserDao接口
2. UserDaoImlp实现类
3. UserService业务接口
4. UserServiceImpl业务实现类

在之前的业务中，用户的需求可能会影响原来的代码，需要根据用户的需求去修改源代码。
如果程序代码量十分大，修改一次的成本十分昂贵。

使用一个 Set 接口实现。（思想上，已经发生革命性变化！）

```java
private UserDao userDao;

// 利用set进行动态实现指导注入！
public void setUserDao(UserDao userDao) {
  this.userDao = userDao;
}
```

- 之前，程序是主动创建对象。控制权在程序员手上。
- 使用了 Set 注入后，程序不再具有主动性，而是变成了被动的接受对象！

IOC（控制反转） 这种思想，从本质上解决了问题，程序员不再去管理对象的创建，系统的耦合性大大降低，可以更加专注在业务的实现上。（这是 IOC 的原型）

#### IOC 本质

控制反转IoC（Inversion of Control），是一种设计思想，DI（依赖注入）是实现 IoC 的一种方法，也有人认为 DI 只是 IoC 的另一种说法。没有 IoC 的程序中，使用面向对象编程，对象的创建与对象间的依赖关系完全硬编码在陈谷中，对象的创建由程序自己控制，控制反转后将对象的创建转移给第三方。所以个人理解，控制反转就是：**获得依赖对象的方式反转了**。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200911152228756.png" alt="image-20200911152228756" style="zoom:50%;" />

采用 XML 方式配置 Bean 的时候，Bean 的定义信息是和实现分离的，而采用注解的方式可以把两者合为一体，Bean 的定义信息直接以注解的形式定义在实现类中，从而达到零配置的目的。

**控制反转是一种通过描述（XML或注释），并通过第三方去产生或获取特定对象的方式。在 Spring 中实现控制反转的是 IoC 容器，其实现方法是依赖注入（Dependency Injaction， DI）。**

### 3. HelloSpring

1. 创建实体类

   ```java
   package pojo;
   
   public class Hello {
       private String str;
   
       public String getStr() {
           return str;
       }
   
       public void setStr(String str) {
           this.str = str;
       }
   
       @Override
       public String toString() {
           return "Hello{" +
                   "str='" + str + '\'' +
                   '}';
       }
   }
   ```

2. 创建 Spring 配置文件 beans.xml（applicationContext.xml），注册实体类

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
                           https://www.springframework.org/schema/beans/spring-beans.xsd">
   
       <!--使用 Spring 来创建对象，在 Spring 中这些都称为 Bean
           类型 变量名 = new 类型();
           Hello hello = new Hello();
       Spring中：
           bean = 对象   ：   new Hello();
           id：变量名
           class：new 的对象
           property：给对象中的属性设置一个值
               value：基本类型或String
               ref：对象
       -->
       <bean id="hello" class="pojo.Hello">
           <property name="str" value="Spring"/>
       </bean>
       <!--    -->
       <bean id="mysqlImpl" class="dao.UserDaoMysqlImpl" />
       <bean id="oracleImpl" class="dao.UserDaoOracleImpl" />
       <bean id="UserServiceImpl" class="service.UserServiceImpl">
           <property name="userDao" ref="mysqlImpl"/>
       </bean>
   
   </beans>
   ```

3. 测试

   ```java
   public static void main(String[] args) {
     // 获取 Spring 上下文对象
     ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
     // 对象都在 Spring 中管理了，使用直接从里面取出即可！
     Hello hello = (Hello) context.getBean("hello");
     System.out.println(hello.toString());
   }
   ```

#### 思考问题

- Hello 对象是由谁创建的？

  hello 对象是由 Spring 创建的。

- Hello 对象的属性是怎么设置的？

  hello 对象的属性是由 Spring 容器设置的。

这个过程就叫**控制反转**。

**控制**：谁来控制对象的创建，传统应用程序的对象是由程序本身控制创建的，使用 Spring 后，对象是由 Spring 来创建的。

**反转**：程序本身不创建对象，而变成被动的接收对象。

**依赖注入**：就是利用实体类的 set 方法进行注入的。

IOC 是一种编程思想，由主动的编程编程被动的接收。

可以通过 **newClassPathXmlApplicationContext** 浏览一下底层源码。

**所以到目前为止，就彻底地不用再到程序中去改动了，要实现不同的操作，只需要在 xml 配置文件中进行修改。**

**所谓的 IoC：对象由 Spring 来创建，管理，装配！**

### 4. IOC 创建对象的方式

测试实体类 User：

```java
package pojo;

public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("name=" + name);
    }
}
```

**无参和有参的创建方式：**

1. Spring 使用无参构造方法创建对象（默认）

2. 如果要让 Spring 使用有参构造创建对象

   1. 下标赋值

      ```xml
      <!--第一种：下标赋值-->
      <bean id="user" class="pojo.User">
      	  <constructor-arg index="0" value="Sugar"/>
      </bean>
      ```

   2. 根据参数类型创建（不推荐！）

      ```xml
      <!--第二种：根据参数类型创建（多个相同的类型的难搞，不建议使用！）-->
      <bean id="user" class="pojo.User">
      	  <constructor-arg type="java.lang.String" value="Sugar" />
      </bean>
      ```

   3. 直接通过参数名（建议）

      ```xml
      <!--第三种：直接通过参数名（最直观）-->
      <bean id="user" class="pojo.User">
      	  <constructor-arg name="name" value="Sugar"/>
      </bean>
      ```

**总结**：在配置文件 ApplicationContext.xml 加载的时候，容器中管理的对象就已经被初始化了！且这些对象都属于同一个对象！

### 5. Spring 配置

#### 5.1 alias 别名

```xml
<!--别名：添加了别名，也可以使用别名获取到对象-->
<alias name="user" alias="userAlias"/>
```

#### 5.2 Bean 的配置

```xml
<!--
    id：bean 的唯一标识符，相当于对象名
    class：bean 对象所对应的全限定名
    userT：别名，同 alias，但name属性可以取多个别名，以 , 或空格或分号分开
    -->
<bean id="user2" class="pojo.User2" name="userT,ut">
	  <property name="name" value="Sugar"/>
</bean>
```

#### 5.3 import

一般用于团队开发使用，可以将多个配置文件，导入合并为一个。

假设，项目中有多个人开发，这三个人负责不同的类开发，不同的类需要注册在不同的 bean 中，可以利用 import 将所有的 beans 合并为一个总的。

- beans1.xml

- beans2.xml

- beans3.xml

- applicationContext.xml

  ```xml
  <import resource="beans2.xml"/>
  <import resource="beans2.xml"/>
  <import resource="beans3.xml"/>
  ```

使用的时候，直接使用 applicationContext.xml 即可。（**需要避免重名**）

### 6.  依赖注入（DI）

#### 6.1 构造器注入

上面已经写过了。

#### 6.2 Set方式注入【重点】

- 依赖注入：Set注入！
  - 依赖：Bean对象的创建都依赖于容器
  - 注入：Bean对象的所有属性，由容器啦注入！

【环境搭建】

1. 

#### 6.3 拓展方式注入