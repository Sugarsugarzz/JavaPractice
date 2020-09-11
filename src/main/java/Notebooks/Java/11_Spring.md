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

1. UserDao接口
2. UserDaoImlp实现类
3. UserService业务接口
4. UserServiceImpl业务实现类





