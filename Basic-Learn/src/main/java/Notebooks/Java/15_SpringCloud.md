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

#### 5.3 写一个Eureka服务器（注册中心）

新建项目 **springcloud-eureka-7001**

1. 导入Eureka依赖

   ```xml
   <dependency>
     <groupId>org.springframework.cloud</groupId>
     <artifactId>spring-cloud-starter-eureka-server</artifactId>
     <version>1.4.6.RELEASE</version>
   </dependency>
   ```

2. 在application.yml配置 Eureka

   ```yml
   server:
     port: 7001
   
   # Eureka配置
   eureka:
     instance:
       hostname: localhost  # Eureka服务端的实例名称
     client:
       register-with-eureka: false  # 表示是否向eureka注册中心注册自己
       fetch-registry: false  # 如果为false，表示自己为注册中心
       service-url:  # 监控页面，与eureka交互地址
         defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka
   ```

3. 注解启动EurekaServer服务

   ```java
   @SpringBootApplication
   @EnableEurekaServer  // @EnableEurekaServer  服务端的启动类，可以接收服务注册进来
   public class EurekaServer_7001 {
       public static void main(String[] args) {
           SpringApplication.run(EurekaServer_7001.class, args);
       }
   }
   ```

4. 访问 localhost:7001 即可来到 Eureka 监控页面

#### 5.4 将服务注册到Eureka中

将 **8001** 项目注册到 **7001** Eureka服务器中。

1. 导入依赖

   ```xml
   <!--Eureka-->
   <dependency>
     <groupId>org.springframework.cloud</groupId>
     <artifactId>spring-cloud-starter-eureka</artifactId>
     <version>1.4.6.RELEASE</version>
   </dependency>
   ```

2. 在application.yml 配置Eureka

   ```yml
   # Eureka配置，配置服务注册到哪里
   eureka:
     client:
       service-url:
         defaultZone: http://localhost:7001/eureka/
       instance:
         instance-id: springcloud-provider-dept:8001 # 修改eureka上的默认描述信息
   ```

3. 在启动类注册启动EurekaClient

   ```java
   @SpringBootApplication
   @EnableEurekaClient  // 在服务启动后自动注册到Eureka中
   public class DeptProvider_8001 {
       public static void main(String[] args) {
           SpringApplication.run(DeptProvider_8001.class, args);
       }
   }
   ```

4. 到Eureka监控页面查看注册情况

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201030180905362.png" alt="image-20201030180905362" style="zoom:30%;" />

5. 完成监控信息，导入依赖

   ```xml
   <!--actuator完善监控信息-->
   <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-actuator</artifactId>
   </dependency>
   ```

6. 在application.yml配置监控信息

   ```yml
   # 配置监控信息
   info:
     app.name: sugar-springcloud
     company.name: casia
   ```

7. 在监控页面进入实例的info页面，即可查看对应信息，可以写一下对服务功能的描述之类

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201030182614041.png" alt="image-20201030182614041" style="zoom:30%;" />


##### 自我保护机制：好死不如赖活着

<span style="color:red">EMERGENCY! EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEY'RE NOT. RENEWALS ARE LESSER THAN THRESHOLD AND HENCE THE INSTANCES ARE NOT BEING EXPIRED JUST TO BE SAFE.</span>

一句话：某时刻某一个微服务不能使用了，eureka不会立刻清理，依旧会对微服务的信息进行保存！

- 默认情况下，如果EurekaServer在一定时间内没有接收到某个微服务实例的心跳，EurekaServer将会注销该实例（默认90秒）。但是当网络分区鼓掌发生时，微服务与Eureka之间无法正常通信，以上行为可能变得非常危险。因为微服务本身是健康的，**此时不应该注销这个服务**。Eureka通过**自我保护机制**来解决这个问题，当RurekaServer节点在短时间内丢失过多客户端时，（可能网络分区鼓掌），那么这个节点就会进入自我保护模式，一旦进入该模式，EurekaServer就会保护服务注册表中的信息，不再删除服务注册表中的数据（不会注销任何微服务）。当网络故障恢复时，该EurekaServer节点会自动退出自我保护欧式。
- 在自我保护模式中，EurekaServer会保护服务注册表中的信息，不再注销任何服务实例。当它收到的心跳数重新恢复到阈值以上时，该EurekaServer节点就会自动退出自我保护模式。其设计哲学就是宁可保留错误的服务注册信息，也不盲目注销任何可能健康的服务实例。
- 综上，自我保护模式是一种应对网络异常的安全保护措施。它的架构哲学是宁可同时保留所有微服务（健康的和不健康的），也不盲目注销任何健康的微服务，使用自我保护模式，可以让Eureka集群更加健壮稳定。
- 在SpringCloud中，可以使用 `eureka.server.enable-self-preservation = false`禁用自我保护模式。【不推荐】

#### 5.5 在Client端从注册中心获取微服务信息

1. 修改Controller，获取注册中心信息

   ```java
   @RestController
   public class DeptController {
   
       // 获取一些注册中心的微服务配置信息，得到具体的微服务
       @Autowired
       private DiscoveryClient client;
   
       // 注册进来的微服务，获取一些信息
       @RequestMapping("/dept/discovery")
       public Object discovery() {
           // 获取微服务列表的清单
           List<String> service = client.getServices();
           System.out.println("discovery => services" + service);
   
           // 得到一个具体的微服务信息，通过具体的微服务id，applicationName
           List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
           for (ServiceInstance instance : instances) {
               System.out.println(
                       instance.getHost() + "\t" +
                       instance.getPort() + "\t" +
                       instance.getUri() + "\t" +
                       instance.getServiceId()
               );
           }
           return this.client;
       }
   }
   ```

2. 在启动类启动服务发现注解

   ```java
   @SpringBootApplication
   @EnableEurekaClient  // 在服务启动后自动注册到Eureka中
   @EnableDiscoveryClient // 服务发现
   public class DeptProvider_8001 {
       public static void main(String[] args) {
           SpringApplication.run(DeptProvider_8001.class, args);
       }
   }
   ```

#### 5.6 Eureka集群配置

注：hostname相同对会被认为是同一注册中心，导致集群搭建不成功。

​	新建 **springcloud-eureka-7002** 和 **springcloud-eureka-7003** 项目，配置 pom.xml、application.yml和主启动类.

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201101110323848.png" alt="image-20201101110323848" style="zoom:30%;" />

  1. 配置Eureka服务器，配置applicationyml，修改服务器实例名称 **hostname** ，挂载其他eureka服务器，修改 **defaultZone**

     ```yml
     server:
       port: 7001
     
     # Eureka配置
     eureka:
       instance:
         hostname: eureka7001.com  # Eureka服务端的实例名称
       client:
         register-with-eureka: false  # 表示是否向eureka注册中心注册自己
         fetch-registry: false  # 如果为false，表示自己为注册中心
         service-url:  # 监控页面，与eureka交互地址
           # 单机：http://${eureka.instance.hostname}:${server.port}/eureka
           # 集群（关联）：挂载7002和7003
           defaultZone: http://eureka7002.com:7002/eureka
     ```

  2. 配置Eureka客户端的application.yml

     修改发布地址，发布到三个服务器上

     ```yml
     eureka:
       client:
         service-url:
           defaultZone: http://localhost:7001/eureka/, http://localhost:7002/eureka/, http://localhost:7003/eureka/
     ```

#### 5.7 对比Zookeeper

##### 回顾CAP原则

RDBMS（MySQl、Oracle、SqlServer）===> ACID

NoSQL（Redis、MongoDB）===> CAP

##### ACID

- A：原子性
- C：一致性
- I：隔离性
- D：持久性

##### CAP

- C（Consistency）：强一致性
- A（Availability）：可用性
- P（Partition tolerance）：分区容错性

CAP的三进二：CA、AP、CP

##### CAP理论的核心

- 一个分布式系统不可能同时很好的满足一致性、可用性和分区容错性这三个需求
- 根据CAP原则，将NoSQL数据库分成了满足CA原则、CP原则和AP原则三大类：
  - CA：单点集群，满足一致性，可用性的系统，通常可扩展性较差
  - CP：满足一致性，分区容错性的系统，通常性能不是特别高
  - AP：满足可用性和分区容错性的系统，通常对一致性要求低一些

##### 作为服务注册中心，Eureka比Zookeeper好在哪里

由于分区容错性P在分布式系统中是必须要保证的，因此只能在A和C之间进行权衡。

- Zookeeper保证的是CP
- Eureka保证的是AP

##### Zookeeper保证的是CP

当向注册中心查询服务列表时，可以容忍注册中心返回的是几分钟以前的注册信息，但不能接收服务直接down掉不可用。即服务注册功能对可用性的要求要高于一致性。但Zookeeper会出现这样一种情况，当master节点因为网络故障与其他节点失去联系时，剩余节点会重新进行leader选举。问题在于，选举leader的时间太长了，30~120s，且选举期间整个zk集群都是不可用的，导致在选举期间注册服务瘫痪。在云部署的环境下，因为网络问题使得zk集群失去master节点是较大概率会发生的时间，虽然服务最后能够恢复，但是漫长的选举时间导致注册长期不可用是不能容忍的。

##### Eureka保证的是AP

Eureka看明白了这一点，设计时优先保证了可用性。**Eureka各个节点都是平等的**，几个节点挂掉不会影响正常节点的工作，剩余的节点依然可以提供注册和查询服务。而Eureka的客户端在向某个Eureka注册时，如果发现连接失败，则自动切换至其他节点，只要有一台Eureka还在，就能保住注册服务的可用性，只不过查到的信息可能不是最新的。除此之外，Eureka还有一种自我保护机制，如果15分钟内超过85%的节点都没有正常的心跳，那么Eureka就认为客户端与注册中心出现了网络故障，此时会出现以下几种情况：

	1. Eureka不再从注册列表中移除因为长时间没收到心跳而应该过期的服务
 	2. Eureka仍然能够接受新服务的注册和查询请求，但是不会被同步打其他节点上（即保证当前节点依然可用）
 	3. 当网络稳定时，当前实例新的注册信息会被同步到其他节点中

**因此，Eureka可以很好的应对因网络故障导致部分节点失去联系的情况，而不会像zk那样使整个注册服务瘫痪。**

### 6. Ribbon客户端负载均衡

#### 6.1 Ribbon

##### ribbon是什么

- Spring Cloud Ribbon是基于Netflix Ribbon实现的一套**客户端负载均衡工具**
- 在客户端项目中做配置（**80项目**）
- Ribbon是Netflix发布的开源项目，主要功能是提供客户端的软件负载均衡算法，将Netflix的中间层服务连接在一次。Ribbon的客户端组件提供一系列完整的配置项，如：连接超时、重试等等。就是在配置文件中累出LoadBalancer（简称LB：负载均衡）后面所有的机器，Ribbon会自动的帮助你基于某种规则（轮询、随机连接等）去连接这些机器，也可以很容易使用Ribbon实现自定义的负载均衡算法。

##### ribbon能干嘛

- LB，在微服务或分布式集群中经常用的一种应用
- 负载均衡简单的说，就是将用户的请求平摊分配到多个服务上，从而达到系统的HA（高可用）。
- 常见负载均衡软件有 **Nginx**、**Lvx** 等等。
- Dubbo、SpringCloud中均提供了负载均衡，**SpringCloud的负载均衡算法可以自定义**
- 负载均衡简单分类：
  - 集中式LB
    - 即在服务的消费方和提供方之间使用独立的LB设施，如Nginx：反向代理服务器！，由该设施负责把访问请求通过某种策略转发至服务的提供方
  - 进程式LB
    - 将LB逻辑集成到消费方，消费方从服务注册中心获知有哪些地址可用，然后自己再从这些地址中选出一个合适的服务器
    - **Ribbon就属于进程内LB**，它只是一个类库，集成于消费方进程，消费方通过它来获取服务提供方的地址

#### 6.2 客户端集成Ribbon

在 **springcloud-consumer-dept-80** 项目配置Ribbon。

1. 导入依赖

   ```xml
           <!--Ribbon-->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-ribbon</artifactId>
               <version>1.4.6.RELEASE</version>
           </dependency>
           <!--Eureka客户端-->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-eureka</artifactId>
               <version>1.4.6.RELEASE</version>
           </dependency>
   ```

2. 编写application.yml，配置Eureka和Ribbon

   ```yml
   server:
     port: 80
   
   # Eureka配置
   eureka:
     client:
       register-with-eureka: false  # 不注册自己
       service-url:
       	# 配置注册中心所在地址
         defaultZone: http://localhost:7001/eureka,http://localhost:7002/eureka,http://localhost:7003/eureka
   ```

3. 启动类Enbale Eureka

   ```java
   // Ribbon和Eureka整合以后，客户端可以直接调用，而不用关心端口号
   @SpringBootApplication
   @EnableEurekaClient
   public class DeptConsumer_80 {
       public static void main(String[] args) {
           SpringApplication.run(DeptConsumer_80.class, args);
       }
   }
   ```

4. 添加负载均衡注解  @LoadBalance

   ```java
   @Configuration
   public class ConfigBean { // @Configuration - spring applicationContext.xml
       // 配置负载均衡实现RestTemplate
       @Bean
       @LoadBalanced  // Ribbon
       public RestTemplate getRestTemplate() {
           return new RestTemplate();
       }
   }
   ```

5. 在Controller修改访问前缀，使用服务名访问

   ```java
   @RestController
   public class DeptConsumerController {
   
       @Autowired
       private RestTemplate restTemplate;
   
       // Ribbon，这里的地址应该是一个变量，通过服务名来访问
   //    private static final String REST_URL_PREFIX = "http://localhost:8001";
       private static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";
   
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

#### 6.2 搭建多服务环境

复制 **db01.dept** 表 到 **db02** 和 **db03**。

复制项目 **springcloud-provider-dept-8001** 到 **springcloud-provider-dept-8002** 和 **springcloud-provider-dept-8003**。

三个项目的 spring.application.name 一致，修改连接的数据库和eureka instance_id即可。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201101134041659.png" alt="image-20201101134041659" style="zoom:40%;" />

1. 启动EurekaServer注册中心
2. 启动Eureka服务，注册到注册中心
3. 启动客户端，请求接口，可见默认是**轮询**实现负载均衡。

#### 6.3 客户端实现自定义负载均衡算法

1. 在主启动类指定Ribbon对应的服务和负载均衡策略

   ```java
   @SpringBootApplication
   @EnableEurekaClient
   @RibbonClient(name = "SPRINGCLOUD-PROVIDER-DEPT", configuration = SugarRule.class)
   // 在微服务启动的时候，就能去加载自定义的Ribbon类
   public class DeptConsumer_80 {
       public static void main(String[] args) {
           SpringApplication.run(DeptConsumer_80.class, args);
       }
   }
   ```

2. 自定义负载均衡算法 SugarRule.class（注：不能被扫描到，所以不能放在springcloud目录下）

   ```java
   package com.sugar.myrule;
   
   import com.netflix.client.config.IClientConfig;
   import com.netflix.loadbalancer.AbstractLoadBalancerRule;
   import com.netflix.loadbalancer.ILoadBalancer;
   import com.netflix.loadbalancer.Server;
   import java.util.List;
   import java.util.concurrent.ThreadLocalRandom;
   
   public class SugarRule extends AbstractLoadBalancerRule {
   
       // 每个机器访问5次，换下一个服务（3个）
       // total=0，如果为5则指向下一个服务节点
       // index=0，如果total=5，index++，如果index>3，total和index置为0
   
       private int total = 0;  // 调用次数
       private int currentIndex = 0;  // 当前提供服务的
   
       @SuppressWarnings({"RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE"})
       public Server choose(ILoadBalancer lb, Object key) {
           if (lb == null) {
               return null;
           } else {
               Server server = null;
   
               while(server == null) {
                   if (Thread.interrupted()) {
                       return null;
                   }
   
                   List<Server> upList = lb.getReachableServers();  // 获得还活着的服务
                   List<Server> allList = lb.getAllServers();  // 获得全部的fuwu
                   int serverCount = allList.size();
                   if (serverCount == 0) {
                       return null;
                   }
   
   //                int index = this.chooseRandomInt(serverCount);  // 生成区间随机数
   //                server = (Server)upList.get(index);
   
                   // 自定义算法部分
                   // ================================================
                   if (total < 5) {
                        server = upList.get(currentIndex);
                        total++;
                   } else {
                       total = 0;
                       currentIndex++;
                       if (currentIndex > upList.size()) {
                           currentIndex = 0;
                       }
                       server = upList.get(currentIndex);
                   }
                   // ================================================
                   if (server == null) {
                       Thread.yield();
                   } else {
                       if (server.isAlive()) {
                           return server;
                       }
   
                       server = null;
                       Thread.yield();
                   }
               }
   
               return server;
           }
       }
   
       public Server choose(Object key) {
           return this.choose(this.getLoadBalancer(), key);
       }
   
   }
   ```

### 7. Feign负载均衡

#### 7.1 Feign

##### 简介

feign是声明式的web service客户端，让微服务之间的调用更简单。类似Controller调用Service。SpringCloud继承了Ribbon和Eureka，可在使用Feign时提供负载均衡的http客户端。

只需要创建一个接口，然后添加注解即可！

Feign主要是社区，大家都习惯面向接口片成。调用微服务访问的两种方法：

- 微服务名字【Ribbon】
- 接口和注解【Feign】

##### Feign能干什么

- Feign旨在编写 Java Http客户端变得更容易。
- 前面在使用Ribbon + RestTemplate时，利用RestTemplate对Http请求的封装处理，形成了一套模板化的调用方法。但在实际开发中，由于对服务依赖的调用可能不止一处，往往一个接口会被多处调用，所以通常都会针对每个微服务自行封装一些客户端类来包装这些依赖服务的调用。所以，Feign在此基础上做了进一步封装，由它来帮助定义和实现依赖服务接口的定义，在Feign的实现下，开发者只需要创建一个接口并使用注解的方式来配置它（类似在Dao接口上标注Mapper注解，现在是一个微服务接口上面标注一个Feign注解即可），即可完成对服务提供方的接口绑定，简化了使用SpringCloud Ribbon时，自动封装服务调用客户端的开发量。

##### Feign集成了Ribbon

- 利用Ribbon维护了MicroServiceCloud-Dept的服务列表信息，并且通过轮询实现了客户端的负载均衡，而与Ribbon不同的是，通过Feign只需要定义服务绑定接口且以声明式的方法，优雅而且简单的实现了服务调用。

#### 7.2 Feign实现步骤

新建 **springcloud-consumer-dept-feign** 项目

1. 导入依赖

   ```xml
           <!--Feign-->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-feign</artifactId>
               <version>1.4.6.RELEASE</version>
           </dependency>
   ```

2. 编写service接口，写注解 **@FeignClient**，对应上Eureka中的服务名

   ```java
   package com.sugar.springcloud.service;
   
   import com.sugar.springcloud.pojo.Dept;
   import org.springframework.cloud.openfeign.FeignClient;
   import org.springframework.stereotype.Component;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.PathVariable;
   import org.springframework.web.bind.annotation.PostMapping;
   
   @Component
   @FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT")
   public interface DeptClientService {
   
       @GetMapping("/dept/get/{id}")
       public Dept queryById(@PathVariable("id") Long id);
   
       @GetMapping("/dept/list")
       public Dept queryAll();
   
       @PostMapping("/dept/add")
       public Dept addDept(Dept dept);
   }
   ```

3. 重新编写客户端Controller，在客户端调用

   ```java
   package com.sugar.springcloud.controller;
   
   import com.sugar.springcloud.pojo.Dept;
   import com.sugar.springcloud.service.DeptClientService;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.web.bind.annotation.PathVariable;
   import org.springframework.web.bind.annotation.RequestMapping;
   import org.springframework.web.bind.annotation.RestController;
   import org.springframework.web.client.RestTemplate;
   
   import java.util.List;
   
   @RestController
   public class DeptConsumerController {
   
       @Autowired
       private DeptClientService service = null;
   
       @RequestMapping("/consumer/dept/add")
       public Boolean add(Dept dept) {
           return this.service.addDept(dept);
       }
   
       @RequestMapping("/consumer/dept/get/{id}")
       public Dept get(@PathVariable("id") Long id) {
           return this.service.queryById(id);
       }
   
       @RequestMapping("/consumer/dept/list")
       public List<Dept> list() {
           return this.service.queryAll();
       }
   }
   ```

4. 在启动类注解启动 @EnableFeign

   ```java
   @SpringBootApplication
   @EnableEurekaClient
   @EnableFeignClients(basePackages = {"com.sugar.springcloud"})
   public class FeignDeptConsumer_80 {
       public static void main(String[] args) {
           SpringApplication.run(FeignDeptConsumer_80.class, args);
       }
   }
   ```

### 8. Hystrix 服务熔断

##### 分布式系统面临的问题

复杂分布式体系结构中的应用程序有数十个依赖关系，每个依赖关系在某些时候将不可避免的失败。

##### 服务雪崩

多个微服务之间调用的时候，假设微服务A调用微服务B和微服务C，微服务B和微服务C又调用其他的微服务，这就是所谓的“扇出”，如果扇出的链路上某个微服务的调用响应事件过长或者不可用，对微服务A的调用就会占用越来越多的系统资源，进而引起系统崩溃，所谓的“雪崩效应”。

对于高流量的应用来说，单一的后端依赖可能会导致所有服务器上的所有资源都在几秒中内饱和。比失败更糟糕的是，这些应用程序还可能导致服务器之间的延迟增加，备份队列，线程和其他系统资源紧张，导致整个系统发生更多的级联故障，这些都表示需要对故障和延迟进行隔离和管理，以便单个依赖关系的失败，不能取消整个应用程序或系统。

即为 **弃车保帅**。

#### 8.1 Hystrix

##### 什么是Hystrix

Hystrix是一个用于处理分布式系统的延迟和容错的开源库，在分布式系统里，许多依赖不可避免的会调用失败，比如超时、异常等，Hystrix能够保证在一个依赖出问题的情况下，不会导致整体服务失败，避免级联故障，以提高分布式系统的弹性。

“断路器”本身是一种开关装置，当某个服务单元发生故障之后，通过断路器的故障监控（类似熔断保险丝），**向调用方返回一个服务预期的，可处理的备选响应（FallBack），而不是长时间的等待或者抛出调用方法无法处理的异常，这样就可以保证服务调用方的线程不会被长时间的、不必要的占用**，从而避免了故障在分布式系统中的蔓延，乃至雪崩。

##### 能做什么

- 服务降级
- 服务熔断
- 服务限流
- 接近实时的监控
- ...

##### 官网

https://github.com/Netfilx/Hystrix/wiki

#### 8.2 服务熔断

熔断机制是对应雪崩效应的一种微服务链路保护机制。

当扇出链路的某个微服务不可用或者响应时间太长时，会进行服务的降级，**进而熔断该节点微服务的调用，快速返回错误的响应信息**。当检测到该节点微服务调用响应正常后恢复调用链路。在SpringCloud框架里熔断机制遇过Hystrix实现。Hystrix会监控微服务间调用的状况，当失效的调用到一定阈值，**缺省是5秒内20词调用失败**就会启动熔断机制。熔断机制的注解是 **@HystrixCommand**。

#### 8.3 Hystrix服务熔断实现步骤（在服务端实现）

1. 导入依赖

   ```xml
   <!--Hystrix-->
   <dependency>
     <groupId>org.springframework.cloud</groupId>
     <artifactId>spring-cloud-starter-hystrix</artifactId>
     <version>1.4.6.RELEASE</version>
   </dependency>
   ```

2. 搭建项目 **springcloud-provider-dept-hystrix-8001**，同之前的8001项目，修改Controller，编写熔断方法

   ```java
   @RestController
   public class DeptController {
   
       @Autowired
       private DeptService deptService;
   
       @GetMapping("/dept/get/{id}")
       @HystrixCommand(fallbackMethod = "hystrixGet")
       public Dept get(@PathVariable("id") Long id) {
           Dept dept = deptService.queryById(id);
           if (dept == null) {
               throw new RuntimeException("id=>" + id + ". 不存在该用户.");
           }
           return dept;
       }
   
       // 备选方法
       public Dept hystrixGet(@PathVariable("id") Long id) {
           return new Dept()
                   .setDeptno(id)
                   .setDname("id=>" + id + ". 不存在该用户.  @Hystrix")
                   .setDb_source("no this database");
       }
   }
   ```

3. 在启动类注解启动 Hystrix

   ```java
   @SpringBootApplication
   @EnableEurekaClient  // 在服务启动后自动注册到Eureka中
   @EnableDiscoveryClient // 服务发现
   @EnableCircuitBreaker // 添加对熔断的支持
   public class DeptHystrixProvider_8001 {
       public static void main(String[] args) {
           SpringApplication.run(DeptHystrixProvider_8001.class, args);
       }
   }
   ```

#### 8.4 服务降级实现步骤（在客户端实现）

在客户端实现 Feign

在服务被关闭后，客户仍然能够访问，得到的Fallback的消息，不会直接报错。

1. 在客户端编写服务降级处理类，继承Fallback接口

   ```java
   // 服务降级~
   @Component
   public class DeptClientServiceFallbackFactory implements FallbackFactory {
   
       public DeptClientService create(Throwable throwable) {
           return new DeptClientService() {
               public Dept queryById(Long id) {
                   return new Dept()
                           .setDeptno(id)
                           .setDname("没有对应的信息，客户端提供了降级的信息，这个服务现在已经被关闭")
                           .setDb_source("no database");
               }
   
               public List<Dept> queryAll() {
                   return null;
               }
   
               public boolean addDept(Dept dept) {
                   return false;
               }
           };
       }
   }
   ```

2. 在Service为Feign添加Fallback处理  fallbackFactory

   ```java
   @Component
   @FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT", fallbackFactory = DeptClientServiceFallbackFactory.class)
   public interface DeptClientService {
   
       @GetMapping("/dept/get/{id}")
       public Dept queryById(@PathVariable("id") Long id);
   
       @GetMapping("/dept/list")
       public List<Dept> queryAll();
   
       @PostMapping("/dept/add")
       public boolean addDept(Dept dept);
   }
   ```

#### 8.5 服务熔断与服务降级

**服务熔断**：服务器~ 某个服务超时或者异常，引起熔断~

**服务降级**：客户端~ 从整体网站请求负载考虑，当某个服务熔断或者关闭之后，服务将不再被调用~ 此时在客户端准备一个 FallbackFactory，返回一个缺省值，整体的服务下降了，但是请求还能用

#### 8.6 Dashboard流监控

创建 **springcloud-consumer-hystrix-dashboard** 项目，完成监控页面

1. 添加依赖

   ```xml
           <!--Hystrix-->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-hystrix</artifactId>
               <version>1.4.6.RELEASE</version>
           </dependency>
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-hystrix-dashboard</artifactId>
               <version>1.4.6.RELEASE</version>
           </dependency>
   ```

2. 配置 application.yml，设置启动端口

   ```yml
   server:
     port: 9001
   hystrix:
     dashboard:
       proxy-stream-allow-list: "*"
   ```

3. 启动类，配置开启

   ```java
   @SpringBootApplication
   @EnableHystrixDashboard  // 开启
   public class DeptConsumerDashboard_9001 {
       public static void main(String[] args) {
           SpringApplication.run(DeptConsumerDashboard_9001.class, args);
       }
   }
   ```

4. 然后开始配置服务端，保证服务端有监控依赖

   ```xml
           <!--Hystrix-->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-hystrix</artifactId>
               <version>1.4.6.RELEASE</version>
           </dependency>
           <!--actuator完善监控信息-->
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-actuator</artifactId>
           </dependency>
   ```

5. 在服务端8001的启动类增加一个Servlet，并开启熔断，配合监控使用

   ```java
   @SpringBootApplication
   @EnableEurekaClient  // 在服务启动后自动注册到Eureka中
   @EnableDiscoveryClient // 服务发现
   @EnableCircuitBreaker  // 添加熔断，以支持监控
   public class DeptProvider_8001 {
       public static void main(String[] args) {
           SpringApplication.run(DeptProvider_8001.class, args);
       }
   
       // 增加一个Servlet
       @Bean
       public ServletRegistrationBean hystrixMetricsStreamServlet() {
           ServletRegistrationBean registrationBean = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
           registrationBean.addUrlMappings("/actuator/hystrix.stream");
           return registrationBean;
       }
   }
   ```

6. 在服务端8001的Controller，需要被监控的方法上，添加 @HystrixCommand 注解，需要写一下fallback方法，不然请求失败页面会为空

   ```java
       @GetMapping("/dept/get/{id}")
       @HystrixCommand
       public Dept get(@PathVariable("id") Long id) {
           return deptService.queryById(id);
       }
   ```

7. 启动7001，8001和9001，访问localhost:9001 监控页面，访问 http://localhost:8001/actuator/hystrix.stream，查看监控

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201111192520885.png" alt="image-20201111192520885" style="zoom:30%;" />

**Hystrix监控图解**

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201111192610966.png" alt="image-20201111192610966" style="zoom:30%;" />

### 9. Zuul路由网关

#### 9.1 概述

Zuul包含了**请求**和**过滤**两个最主要的功能。

其中路由功能负责将外部请求转发到具体的微服务实例上，是实现外部访问统一入口的基础，而过滤器功能则负责对请求的处理过程进行干预，是实现请求校验、服务聚合等功能的基础。Zuul和Eureka进行整合，将Zuul自身注册为Eureka服务助理下的应用，同时从Eureka中获得其他微服务的消息，也即以后访问的微服务都通过Zuul跳转后获得。

**注意**：Zuul服务最终还是会注册进Eureka

**提供**：代理+路由+过滤 三大功能！

**官网文档**：https://github.om/Netflix/zuul

#### 9.2 Zuul路由实现步骤

新建 **springcloud-zuul-9527** 项目

1. 添加依赖（主要是Zuul和Eureka）

   ```xml
           <!--Zuul-->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-zuul</artifactId>
               <version>1.4.6.RELEASE</version>
           </dependency>
   ```

2. 配置 application.yml，注册到Eureka

   ```yml
   server:
     port: 9527
   
   spring:
     application:
       name: springcloud-zuul
   
   # eureka
   eureka:
     client:
       service-url:
         defaultZone: http://localhost:7001/eureka/,http://localhost:7002/eureka/,http://localhost:7003/eureka/
     instance:
       instance-id: zuul9527.com
       prefer-ip-address: true
   
   info:
     app.name: sugar-springcloud
     company.name: isi
   ```

3. 启动类，开启Zuul

   ```java
   @SpringBootApplication
   @EnableZuulProxy
   public class ZuulApplication_9527 {
       public static void main(String[] args) {
           SpringApplication.run(ZuulApplication_9527.class, args);
       }
   }
   ```

4. 通过Zuul，访问Eureka中的微服务。

   隐藏了微服务的**真实IP和端口**，但依然会显示**服务名**。

   http://localhost:9527/springcloud-provider-dept/dept/get/1

5. 为了进一步隐藏服务名，修改 application.yml，使其只能用新定义的服务名访问。

   ```yml
   # zuul 隐藏服务名
   zuul:
     routes:
       mydept.serviceId: springcloud-provider-dept
       mydept.path: /mydept/**
     ignored-services: "*"  # 隐藏全部的真实服务名
     prefix: /sugar  # 统一的访问前缀
   ```

6. 用定义的微服务名，再次访问。

   http://localhost:9527/mydept/dept/get/1

### 10. Config分布式配置

#### 10.1 概述

##### 分布式系统面临的问题：配置文件过多

微服务意味着将单体应用中的业务拆分成一个个子服务，每个服务的粒度相对较小，因此系统中会出现大量的服务，由于每个服务都需要必要的配置信息才能运行，所以一套集中式的、动态的配置管理设施必不可少。SpringCloud提供ConfigServer来解决这个问题。

##### SpringCloud Config分布式配置中心

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201111202745057.png" alt="image-20201111202745057" style="zoom:30%;" />

Spring Cloud Config 为微服务架构中的微服务提供集中化的外部配置支持，配置服务器为**各个不同微服务应用**的所有环节提供了一个**中心化的外部配置**。

Spring Cloud Config 分为 **服务端** 和 **客户端** 两部分：

**服务端**也称为**分布式配置中心**，它是一个独立的微服务应用，用来连接配置服务器并为客户端提供获取配置信息、加密、解密信息等访问接口。

**客户端**则是通过指定的配置中心来管理应用资源，以及与业务相关的配置内容，并在启动的时候从配置中心获取和加载配置信息。配置服务器默认采用git来存储配置信息，这样有助于对环境配置进行版本管理，并且可以通过git客户端工具来方便的管理和访问配置内容。

##### 能做什么

- 集中管理配置文件
- 不同环境，不同配置，动态化的配置更新，分环境部署，比如dev、test、prod、releease
- 运行期间动态调整配置，不再需要在每个服务部署的机器上编写配置文件，服务会向配置中心统一拉取配置自己的信息
- 当配置发生变动时，服务不需要重启，即可感知到配置的变化，并应用新的配置
- 将配置信息以REST接口的形式暴露

##### SpringCloud Config分布式配置中心与Github整合

由于SpringCloud Config默认使用Git来存储配置文件（也支持SVN和本地文件），但最推荐的还是Git，而且使用的是 http /https 访问的形式。

#### 10.2 搭建Git环境

1. 注册码云账号

2. 创建一个 springcloud-config 项目

3. 使用 git clone https://... 克隆到本地

4. 创建 application.yml 文件，编写配置

   ```yml
   spring:
     profiles:
       active: dev
   
   ---
   spring:
     profiles: dev
     application:
       name: springcloud-config-dev
   
   ---
   spring:
     profiles: test
     application:
       name: springcloud-config-test
   ```

5. 将application.yml文件提交到git上

   ```cmd
   cd path
   git add .
   git commit -m "first commit"
   git push origin master
   ```


#### 10.3 创建服务端连接Git配置

创建 **springcloud-config-server-3344** 项目

1. 导入依赖

   ```xml
           <!--config-->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-config-server</artifactId>
           </dependency>
           <!--Eureka-->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-eureka</artifactId>
               <version>1.4.6.RELEASE</version>
           </dependency>
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-web</artifactId>
           </dependency>
   ```

2. 编写 application.yml 配置

   ```yml
   server:
     port: 3344
   spring:
     application:
       name: springcloud-config-server
     # 连接远程Git仓库
     cloud:
       config:
         server:
           git:
             uri: https://gitee.com/sugarbabyzzz/springcloud-config.git  # https
   
   # 通过 config-server 可以连接到git，访问其中的资源以及配置
   ```

3. 主启动类开启 注解 @EnableConfigServer

   ```java
   @SpringBootApplication
   @EnableConfigServer  // 开启
   public class Config_Server_3344 {
       public static void main(String[] args) {
           SpringApplication.run(Config_Server_3344.class, args);
       }
   }
   ```

4. 访问 http://localhost:3344/application-dev.yml，得到 dev 的配置

   通过以下几种方式来访问资源

   label是分支

   ```.
   /{application}/{profile}/{label}   # /application/dev/master
   /{application}-{profille}.yml     # /application-dev.yml
   /{label}/{application}-{profile}.yml   # /master/application-dev.yml
   /{application}-{profile}.properties   # 
   /{label}/{application}-{profile}.properties
   ```

#### 10.4 创建客户端连接服务端，访问远程配置

创建  项目。

1. 先给 **springcloud-config** 项目添加配置文件 config-client.yml，push到git上

   ```yml
   spring:
     profiles:
       active: dev
   
   ---
   server:
     port: 8201
   
   # spring配置
   spring:
     profiles: dev
     application:
       name: springcloud-provider-dept
   
   # Eureka配置，配置服务注册到哪里
   eureka:
     client:
       service-url:
         defaultZone: http://localhost:7001/eureka/
   
   ---
   server:
     port: 8202
   
   # spring配置
   spring:
     profiles: test
     application:
       name: springcloud-provider-dept
   
   # Eureka配置，配置服务注册到哪里
   eureka:
     client:
       service-url:
         defaultZone: http://localhost:7001/eureka/
   ```

2. 创建 **springcloud-config-client-3355** 项目

3. 导入依赖

   ```xml
           <!--config-->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-config</artifactId>
           </dependency>
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-web</artifactId>
           </dependency>
   ```

4. 编写配置文件 application.yml

   ```yml
   # 用户级别的配置
   spring:
     application:
       name: springcloud-config-client-3355
   ```

5. 编写配置文件 bootstrap.yml，连接 Config Server

   ```yml
   # application  项目级配置
   # bootstrap  系统级配置
   
   # 连接 Config Server
   spring:
     cloud:
       config:
         name: config-client  # 从git上需要读取的资源名称，不需要后缀
         uri: http://localhost:3344
         profile: dev
         label: master
   ```

6. 编写测试 Controller，查看配置文件信息

   ```java
   @RestController
   public class ConfigClientController {
   
       @Value("${spring.application.name}")
       private String applicationName;
   
       @Value("${eureka.client.service-url.defaultZone}")
       private String eurekaServer;
   
       @Value("${server.port}")
       private String port;
   
       @RequestMapping("/config")
       public String getConfig() {
           return "applicationName:" + applicationName + "\n" +
                   "eurekaServer:" + eurekaServer + "\n" +
                   "port:" + port;
       }
   }
   ```

7. 3355项目启动后，可以看到，其启动到了远程配置文件的 8201 端口，继承了远程配置文件。

8. 修改 profile，可以换到对应的环境配置。

#### 10.5 结论

通过SpringCloud Config分布中心可以完成这样一个架构：

- 写多个客户端，将配置文件统一放到GitHub上。
- 通过 Config Server 去连接 GitHub。

#### 10.6 Config整合实战

1. 给 **springcloud-config** 项目添加配置文件 **config-eureka.yml**，放到Git上

   ```yml
   spring:
     profiles:
       active: dev
   
   ---
   server:
     port: 7001
   
   # spring配置
   spring:
     profiles: dev
     application:
       name: springcloud-provider-eureka
   
   # Eureka配置
   eureka:
     instance:
       hostname: localhost  # Eureka服务端的实例名称
     client:
       register-with-eureka: false  # 表示是否向eureka注册中心注册自己
       fetch-registry: false  # 如果为false，表示自己为注册中心
       service-url:  # 监控页面，与eureka交互地址
         defaultZone: http://localhost:7002/eureka,http://localhost:7003/eureka
   
   ---
   server:
     port: 7001
   
   # spring配置
   spring:
     profiles: dev
     application:
       name: springcloud-provider-eureka
   
   # Eureka配置
   eureka:
     instance:
       hostname: localhost  # Eureka服务端的实例名称
     client:
       register-with-eureka: false  # 表示是否向eureka注册中心注册自己
       fetch-registry: false  # 如果为false，表示自己为注册中心
       service-url:  # 监控页面，与eureka交互地址
         defaultZone: http://localhost:7002/eureka,http://localhost:7003/eureka
   ```

2. 再添加配置文件 config-dept.yml，放到Git上

   ```yml
   spring:
     profiles:
       active: dev
   
   ---
   server:
     port: 8001
   
   # mybatis配置
   mybatis:
     type-aliases-package: com.sugar.springcloud.pojo
     mapper-locations: classpath:mapper/*.xml
     config-location: classpath:mybatis-config.xml
   
   # spring配置
   spring:
     profiles: dev
     application:
       name: springcloud-config-dept
     datasource:
       type: com.alibaba.druid.pool.DruidDataSource
       driver-class-name: com.mysql.cj.jdbc.Driver
       url: jdbc:mysql://localhost:3306/db01?useUnicode=true&characterEncoding=utf-8
       username: root
       password: 123456
   
   # Eureka配置，配置服务注册到哪里
   eureka:
     client:
       service-url:
         defaultZone: http://localhost:7001/eureka/,http://localhost:7002/eureka/,http://localhost:7003/eureka/
     instance:
       instance-id: springcloud-provider-dept:8001 # 修改eureka上的默认描述信息
   
   # 配置监控信息
   info:
     app.name: sugar-springcloud
     company.name: casia
   
   ---
   server:
     port: 8001
   
   # mybatis配置
   mybatis:
     type-aliases-package: com.sugar.springcloud.pojo
     mapper-locations: classpath:mapper/*.xml
     config-location: classpath:mybatis-config.xml
   
   # spring配置
   spring:
     profiles: test
     application:
       name: springcloud-config-dept
     datasource:
       type: com.alibaba.druid.pool.DruidDataSource
       driver-class-name: com.mysql.cj.jdbc.Driver
       url: jdbc:mysql://localhost:3306/db02?useUnicode=true&characterEncoding=utf-8
       username: root
       password: 123456
   
   # Eureka配置，配置服务注册到哪里
   eureka:
     client:
       service-url:
         defaultZone: http://localhost:7001/eureka/,http://localhost:7002/eureka/,http://localhost:7003/eureka/
     instance:
       instance-id: springcloud-provider-dept:8001 # 修改eureka上的默认描述信息
   
   # 配置监控信息
   info:
     app.name: sugar-springcloud
     company.name: casia
   ```

3. 新建一个Eureka项目 **springcloud-eureka-config-7001**，不再需要本地的 application.yml

4. 导入依赖

   ```xml
           <!--config-->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-config</artifactId>
           </dependency>
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-eureka-server</artifactId>
               <version>1.4.6.RELEASE</version>
           </dependency>
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-devtools</artifactId>
           </dependency>
   ```

5. 编写 bootstrap.yml，连接远程配置文件

   ```yml
   spring:
     cloud:
       config:
         name: config-eureka
         label: master
         profile: dev
         uri: http://localhost:3344
   ```

6. **测试：启动 Config Server（3344项目），再启动 eureka（config eureka 7001项目），如果能访问到 localhost:7001，则说明读取远程配置文件成功。**

7. 新建一个 dept项目 **springcloud-provider-dept-config-80011** ，copy之前的dept-8001项目，不再需要本地的 application.yml。

8. 同样需要导入config依赖，编写 bootstrap.yml，连接远程配置文件。

   ```yml
   spring:
     cloud:
       config:
         name: config-dept
         label: master
         profile: dev
         uri: http://localhost:3344
   ```

**注意：如果要实现编辑Git配置文件，程序同步更新配置，给配置加上热部署即可！**

### 11. 总结

- 微服务和微服务架构
- 基础工程 RestTemplate
- Eureka AP
  - 集群配置
  - 对比Zookeeper   CP
- Ribbon
  - IRule
- Feign
  - 接口，社区要求，更加面向接口编程
- Hystrix
  - 熔断
  - 降级
  - Dashboard
- Zuul
- Spring Cloud Config
  - C  -  S  -  GIT

**通用步骤**

1. 导入依赖
2. 编写配置
3. @Enablexxx