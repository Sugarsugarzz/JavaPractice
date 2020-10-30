前提知识点：

- 数据库
- MyBatis
- Spring
- SpringMVC
- SpringBoot
- Dubbo、Zookeeper、分布式基础
- Maven、Git
- Ajax、JSON

分布式阶段学习

```
三层架构 + MVC

框架：
	Spring IOC AOP
	SpringBoot，新一代JavaEE开发框架，自动装配，约定大于配置
	all in one -> 模块化~  

微服务四个核心问题：
	1. 服务很多，客户端如何访问？
	2. 这么多服务，服务之间如何通信？  RPC框架
	3. 这么多服务，如何治理？  注册中心
	4. 服务挂了怎么办？

解决方案：
	SpringCloud 生态。
	
	1. spring cloud netfilx （停更） 一站式解决方案
		api网关、zuul组件
		Feign  --HttpClient -- Http通信方式，同步，阻塞
		服务注册发现：Eureka
		熔断机制：Hystrix
		
	2. Apache Dubboe Zookeeper  半自动，需要整合别人的
		API：无，用第三方组件或自己实现
		Dubbo
		Zookeeper
		无，借助Hystrix
		
	3. Spring Cloud Alibaba  一站式解决方案
		
		
新概念：服务网格 - Server Mesh
		istio
		
1. API
2. HTTP，RPC
3. 注册和发现
4. 熔断机制
```

### 1. 面试题

1. 什么是微服务？
2. 微服务之间如何独立通讯？
3. SpringCloud和Dubbo有哪些区别？
4. SpringBoot和SpringCloud，谈谈理解。
5. 什么是服务熔断？什么是服务降级？
6. 微服务的优缺点分别是什么？项目中遇到的坑？
7. 微服务技术栈有哪些？
8. eureka和zookeeper都可以提供服务注册与发现功能，区别是什么？

### 2. 微服务

#### 2.1 微服务

将**传统的一站式应用**，根据业务**拆分成一个一个的服务**，彻底地**去耦合**，每一个微服务提供单个业务功能的服务，一个服务做一件事情，从技术角度看就是一种小而独立的处理过程，类似进行的概念，能够自行单独启动或销毁，拥有自己独立的数据库。

#### 2.2 微服务与微服务架构

##### 微服务

狭义上看，就是使用Springboot开发的一个小模块。

##### 微服务架构

将单一应用划分成一组小的服务，服务之间互相协调配合，每个服务运行在独立进程中，围绕具体的业务进行构建，能够被独立的部署到生成环境中，避免统一的、集中式的服务管理机制。

### 2.3 微服务优缺点

##### 优点

- 单一职责原则，松耦合，易于集成

- **只有业务逻辑代码。不会和HTML、CSS等界面混合**

- **每个微服务有自己的存储能力，有自己的数据库**

##### 缺点

- 分布式复杂性
- 通信成本
- 数据一致性
- 运维成本
- ....

#### 2.4 微服务技术栈

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201030131754089.png" alt="image-20201030131754089" style="zoom:35%;" />

##### 2.5 SpringCloud选型原因

##### 选型一句

- 整体解决方案
- 社区热度
- 可维护性
- 学习曲线

##### 各大IT公司的微服务架构

- 阿里：Dubbo + HFS
- 京东：JSF
- 新浪：Motan
- 当当：DubboX
- ...

### 3. SpringCloud入门

#### 3.1 SpringCloud概述

Spring官网：https://spring.io/

SpringCloud基于SpringBoot提供了一套微服务解决方案，包括服务注册与发现、配置中心、全链路监控、服务网关、负载均衡、熔断器等组件，还有一些选型中立的开源组件。

巧妙简化了分布式系统基础设施开发，提供快速构建分布式系统的一些工具，包括**配置管理、服务发现、熔断器、路由、微代理、事件总线、全局锁、决策竞选、分布式会话等等**。可以用SpringBoot开发风格做到一键启动和部署。

SpringCloud是分布式微服务架构下的一站式解决方案，是各个微服务架构落地技术的集合体，俗称微服务全家桶。

#### 3.2 SpringCloud和SpringBoot关系

- SpringBoot专注于快速方便的开发单个个体微服务。（jar包）
- SpringCloud是关注全局的微服务协调整理治理框架，将SpringBoot开发的一个个单体微服务整合并管理起来，为各个微服务之间提供：配置管理、断路器、路由、微代理、事件总线、全局锁、决策竞选、分布式会话等等继承服务。
- SpringBoot可以离开SpringCloud独立使用，开发项目，但SpringClouds离不开SpringBoot，属于依赖关系。
- **SpringBoot专注于快速、方便的开发单个个体微服务，SpringCloud关注全局的服务治理框架**

#### 3.3 Dubbo和SpringCloud技术选型

##### 1. 分布式+服务治理Dubbo

目前成熟的互联网架构：应用服务化拆分 + 消息中间件

![image-20201030135913178](/Users/sugar/Library/Application Support/typora-user-images/image-20201030135913178.png)

##### 2. Dubbo和SpringCloud对比

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201030140005855.png" alt="image-20201030140005855" style="zoom:30%;" />

**最大的区别：SpringCloud抛弃了Dubbo的RPC通信，采用的基于HTTP的REST方式。**

**解决的问题不一样：Dubbo的定位是一款RPC框架，SpringCloud的目标是微服务架构下的一站式解决方案。**

#### 3.4 SpringCloud功能

- 分布式/版本控制配置
- 服务注册与发现
- 路由
- 服务到服务的调用
- 负载均衡配置
- 断路器
- 分布式消息管理
- ...

#### 3.5 SpringCloud官网

官网：http://projects.spring.io/spring-cloud/

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201030140628457.png" alt="image-20201030140628457" style="zoom:30%;" />

SpringCLoud是由众多独立子项目组成的大型综合项目，每个子项目有不同的发行节奏。

SpringCloud通过一个资源清单BOM来管理每个版本的子项目清单，为了避免与子项目的发布号混淆，所以没有采用版本号的方式，而是通过命名的方式。

这些版本名称命名方式采用伦敦地铁站的名称，同时根据字母表的顺序来对应版本时间顺序，比如：最早的Release版本：Angel，第二个Release版本：Brixton，然后是Camden、Dalston、Edgware，**最新的是Finchley版本**。

##### 参考资料：

- https://springcloud.cc/spring-cloud-netflix.html
- 中文API文档：https://springcloud.cc/spring-cloud-dalston.html
- SpringCloud中国社区：http://springcloud.cn/
- SpringCloud中文网：https://springcloud.cc/

### 4. RestFUL实现远程调用

**springcloud-api** 项目，提供一个 Dept 实体类。

数据库 db01 加入几条测试数据。

#### 4.1 服务提供者

**springcloud-provider-dept-8001** 项目

编写 mapper、service、controller层，提供接口服务

```java
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;


    @PostMapping("/dept/add")
    public boolean addDept(Dept dept) {
        return deptService.addDept(dept);
    }

    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return deptService.queryById(id);
    }

    @GetMapping("/dept/list")
    public List<Dept> queryAll() {
        return deptService.queryAll();
    }

}
```

#### 4.2 服务消费者

**springcloud-consumer-dept-80** 项目

虽然项目中没有mapper、service层，但可以直接远程调用8001端口的接口。

1. 注册 RestTemplate 到 Spring

   **提供便捷方式访问远程http服务**

   ```java
   @Configuration
   public class ConfigBean { // @Configuration - spring applicationContext.xml
       @Bean
       public RestTemplate getRestTemplate() {
           return new RestTemplate();
       }
   }
   ```

2. 编写接口调用Controller

   调用8001端口提供的接口，实现相同功能的接口。

   ```java
   @RestController
   public class DeptConsumerController {
   
       // 消费者，不应该有service层
       // RestTemplate ... 可以直接调用，注册到Spring
       // (url, 实体：Map, Class<T> responseType)
       @Autowired
       private RestTemplate restTemplate;  // 提供多种便捷访问远程http服务的方法，简单的restful服务模板
   
       private static final String REST_URL_PREFIX = "http://localhost:8001";
   
       // http://localhost:8001/dept/list
   
       @RequestMapping("/consumer/dept/add")
       public Boolean add(Dept dept) {
           return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
       }
   
       @RequestMapping("/consumer/dept/get/{id}")
       public Dept get(@PathVariable("id") Long id) {
           return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
       }
   
       @RequestMapping("/consumer/dept/list")
       public List<Dept> list() {
           return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
       }
   }
   ```

### 5. Eureka服务注册与发现

RestFUL方式，是消费者直接从服务提供者调用服务。

而有了注册中心，服务提供者先在**注册中心**注册其服务，配置注册中心地址；消费者从注册中心获取服务。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201030170059311.png" alt="image-20201030170059311" style="zoom:30%;" />

#### 5.1 什么是Eureka

- 遵循**AP原则**
- Eureka是一个基于REST的服务，用于定位服务，以实现云端中间层服务发现和故障转移，服务注册与发现对于微服务来说非常重要，有了服务发现与注册，只需要使用服务的标识符，就可以访问到服务，而不需要修改服务调用的配置文件了，**功能类似于Dubbo的注册中心**，比如ZooKeeper。

#### 5.2 原理详解

- Eureka基本架构
  - SpringCloud封装了NetFilx公司开发的Eureka模块实现服务注册和发现（对比Zookeeper）
  - Eureka采用了C-S的架构设计，EurekaServer作为服务注册功能的服务器，是服务注册中心
  - 系统中的其他微服务，使用Eureka的客户端连接到EurekaServer并维持心跳连接。系统维护人员就可以通过EurekaServer来监控系统中各个微服务是否正常运行，SpringCloud的一些其他模块（比如Zuul）就可以通过EurekaServer来发现系统中的其他微服务，并执行相关的逻辑。
  - Eureka包含两个组件：**Eureka Server** 和 **Eureka Client**
  - Eureka Server提供服务注册服务，各个节点启动后，会在EurekaServer中进行注册，这样Eureka Server中的服务注册表中将会出现所有可用服务节点的信息，服务节点的信息可以在界面中直观的看到
  - Eureka Client是一个Java客户端，用于简化EurekaServer的交互，客户端同时也具备一个内置的，使用轮询负载算法的负载均衡器。在应用启动后，将会向EurekaServer发送心跳（默认周期为30秒）。如果EurekaServer在多个心跳周期内没有接受到某个节点的心跳，EurekaServer将会从服务注册表中把这个服务节点移除掉（默认周期为90秒）。
- 三大角色
  - **Eureka Server**：提供服务的注册与发现。（同ZooKeeper）
  - **Service Provider**：将自身服务注册到Eureka中，从而使消费方能够找到。
  - **Service Consumer**：服务消费方从Eureka中获取注册服务列表，从而找到消费服务。