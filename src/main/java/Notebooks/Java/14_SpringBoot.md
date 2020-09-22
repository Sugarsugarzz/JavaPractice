# 微服务阶段

JavaSE：OOP

MySQL：持久化

html+css+js+jquery+框架：视图、框架不熟练，css不好

JavaWeb：独立开发MVC三层架构的网站（原始）

SSM：框架，简化了开发流程，配置也开始较为复杂

**war：tomcat运行**

Spring再简化：SpringBoot  **jar：内嵌tomcat**；微服务架构！

服务越来越多：SpringCloud。

未来的服务架构：**服务网格**！

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200921234401725.png" alt="image-20200921234401725" style="zoom:50%;" />

### 1. 概述

#### 1.1 SpringBoot

核心思想：**约定大于配置**

主要优点：

- 为所有 Spring开发更快入门
- **开箱即用**，提供各种默认配置来简化项目配置
- 内嵌式容器简化Web项目
- 没有冗余代码生成和XML配置的要求

#### 1.2 微服务

##### 微服务

一种架构风格，要求在开发一个应用时，这个应用必须构建成一系列小服务的组合，可以通过http的方式进行互通。

##### 单体应用架构

把所有的功能单元放在一个应用里面，然后将整个应用部署到服务器上。如果负载能力不行，将整个应用进行水平复制，进行扩展，在洪湖再负载均衡。

好处：易于开发，方便部署，扩展时只需要将war复制多份，放在多个服务器上，再做个负载均衡即可。

缺点：哪怕修改一个非常小的地方，都需要停掉整个服务，重新打包，部署这个应用war包。对于大型应用，也不可能把所有内容放在一个应用中，如何维护、分工都是问题。

##### 微服务架构

打破all in one的架构方式，将每个功能元素独立出来，将独立出来的功能元素动态组合，需要的功能元素才拿来组合，需要多一些时可以整合多个功能元素。所以微服务架构是对功能元素进行复制，而没有对整个应用进行复制。

好处：节省了调用资源，每个功能元素的服务都是一个可替换的、可独立升级的软件代码。

##### 如何构建微服务架构

- SpringBoot：构建一个个功能独立的微服务应用单元
- SpringCloud：大型分布式网络服务的调用，实现分布式
- SpringCloudDataFlow：在分布式中间，进行流式数据计算、批处理
- Spring实现了整个从刚开始构建应用到大型分布式应用全流程方案

#### 2. 第一个SpringBoot程序

官方：提供了一个快速生成的网站，IDEA提供了这种方式。

https://start.spring.io/

#### 2.1 Springboot自动装配原理

**pom.xm**l

- spring-boot-dependencies：核心依赖在该父工程中
- 我们在写或者引入一些SpringBoot依赖的时候，不需要指定版本，就因为有这些版本仓库

**启动器**

- ```xml
  <!--启动器-->
  <dependency>
  	  <groupId>org.springframework.boot</groupId>
    	<artifactId>spring-boot-starter</artifactId>
  </dependency>
  ```

- 就是SpringBoot的启动场景；

- 比如，Spring-boot-starter-web，就会帮助自动导入web环境所有的依赖！

  ```xml
  <!--web依赖-->
  <dependency>
  	  <groupId>org.springframework.boot</groupId>
    	<artifactId>spring-boot-starter-web</artifactId>
  	  <version>2.3.1.RELEASE</version>
  </dependency>
  ```

- SpringBoot会将所有的功能场景，都变成一个个的启动器

- 要使用什么功能，就只需要找到对应的启动器即可

**主程序**

```java
// @SpringBootApplication：标注这个类是一个Springboot的应用
@SpringBootApplication
public class Springboot01HelloworldApplication {
	public static void main(String[] args) {
		// 将Springboot应用启动
		SpringApplication.run(Springboot01HelloworldApplication.class, args);
	}
}
```

- 注解

  - ```java
    @SpringBootConfiguration：SpringBoot的配置
      	@Configuration：Spring配置类
      			@Component：说明这也是一个Spring的组件	
    
    @EnableAutoConfiguration：自动配置
      	@AutoConfigurationPackage：自动配置包
      			@Import({AutoConfigurationImportSelector.class})：导入选择器
    ```

  - 

- w