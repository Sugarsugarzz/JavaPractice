### 1 简介

#### 1.1 什么是 Spring Cloud

是一系列分布式框架的集合，基于 SpringBoot 开发，是一种**规范**。

将不同公司生产的不同组件进行集成，以 Spring Boot 的风格进行集成。无需关注底层的整合实现，开箱即用，需要哪个组件就用 Spring Boot 整合起来。

#### 1.2 什么是 Spring Cloud Alibaba

Spring Cloud Netfilx 已停止维护（小更新，无大版本更新）

Spring Cloud Alibaba 一站式解决方案，目前活跃度最高。2.2.1 

**Spring Cloud Alibaba工程结构：**

Spring Boot => Spring Cloud => Spring Cloud Alibaba

版本说明：https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E

#### 1.3 代码

1、创建父工程，pom.xml（2.3.0 => Hoxton.SR3 => 2.2.1.RELEASE）

SpringBoot2.3以上可能会有一些Bug。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
   <modelVersion>4.0.0</modelVersion>
   <parent>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-parent</artifactId>
      <version>2.3.0.RELEASE</version>  
      <relativePath/> <!-- lookup parent from repository -->
   </parent>
   <groupId>com.sugar</groupId>
   <artifactId>springcloudalibabademo</artifactId>
   <version>0.0.1-SNAPSHOT</version>
   <name>springcloudalibabademo</name>
   <description>Demo project for Spring Boot</description>
   <properties>
      <java.version>1.8</java.version>
   </properties>
   <dependencies>
      <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-web</artifactId>
      </dependency>

      <dependency>
         <groupId>org.projectlombok</groupId>
         <artifactId>lombok</artifactId>
         <optional>true</optional>
      </dependency>
      <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-test</artifactId>
         <scope>test</scope>
      </dependency>
   </dependencies>

   <dependencyManagement>
      <dependencies>
         <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>Hoxton.SR3</version>
         </dependency>
         <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-alibaba-dependencies</artifactId>
            <version>2.2.1.RELEASE</version>
         </dependency>
      </dependencies>
   </dependencyManagement>

   <build>
      <plugins>
         <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
               <excludes>
                  <exclude>
                     <groupId>org.projectlombok</groupId>
                     <artifactId>lombok</artifactId>
                  </exclude>
               </excludes>
            </configuration>
         </plugin>
      </plugins>
   </build>

</project>
```

### 2 Nacos 服务治理

服务注册 + 服务发现

Nacos文档：https://nacos.io/zh-cn/docs/quick-start.html

相比eureka还需要创建工程去启动注册中心，nacos更方便一些。

Nacos下载地址：https://github.com/alibaba/nacos/releases

下载 `nacos-server-1.2.1-zip`，解压后在 `bin` 目录下启动 `startup.sh`

启动/结束命令：`sh startup.sh -m standalone` 和 `sh shutdonw.sh`

**注意：启动遇到脚本错误**

```
» sh startup.sh -m standalone                                                             tanghuang@bogon
: command not found
: command not found
: command not found:
'tartup.sh: line 19: syntax error near unexpected token `in
'tartup.sh: line 19: `case `uname` in
```

解决：因为是在windows编写的脚本文件，在Linux中无法识别格式，用 vi 打开 startup.sh 文件，在底线命令模式输入 `:set ff=unix`

进入可视化界面，账号密码 nacos/nacos，默认端口 8848：http://127.0.0.1:8848/nacos/

> 服务注册

创建 `provider` 项目注册服务

1. 创建 provider 项目，修改 pom.xml

   ```xml
   # 继承父项目的依赖配置
   <parent>
     <groupId>com.sugar</groupId>
     <artifactId>springcloudalibabademo</artifactId>
     <version>0.0.1-SNAPSHOT</version>
   </parent>
   
   # 添加 nacos依赖
   <dependency>
     <groupId>com.alibaba.cloud</groupId>
     <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
     <version>2.2.1.RELEASE</version>
   </dependency>
   ```

2. 修改配置文件 application.yml

   ```yml
   spring:
     cloud:
       nacos:
         discovery:
           server-addr: localhost:8848
   
     application:
       name: provider
   ```

3. 修改启动配置，允许多个实例启动

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210314154620008.png" alt="image-20210314154620008" style="zoom:50%;" />

4. 修改 `server.port`，再启动两个实例

5. 在 nacos 查看服务状态
   可以看到三个实例，不同端口。

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210314154825065.png" alt="image-20210314154825065" style="zoom:50%;" />

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210314154828593.png" alt="image-20210314154828593" style="zoom:50%;" />



> 服务发现

创建 `consumer` 项目发现服务

1. 创建 consumer 项目，修改 pom.xml，父项目继承和nacos依赖

2. 修改配置文件 application.yml

   ```yml
   server:
     port: 8180
   ```

3. 编写 ConsumerController，发现 nacos 中的服务

   ```java
   package com.sugar.controller;
   
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.cloud.client.ServiceInstance;
   import org.springframework.cloud.client.discovery.DiscoveryClient;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.RestController;
   
   import java.util.List;
   
   @RestController
   public class ConsumerController {
   
       @Autowired
       private DiscoveryClient discoveryClient;
   
       @GetMapping("/instances")
       public List<ServiceInstance> instances() {
           List<ServiceInstance> providers = this.discoveryClient.getInstances("provider");
           return providers;
       }
   
   }
   ```

4. 访问 localhost:8180/instances，得到服务实例的情况

   ```json
   [
   	{
       serviceId: "provider",
       host: "192.168.0.101",
       port: 8080,
       secure: false,
       metadata: {
         nacos.instanceId: "192.168.0.101#8080#DEFAULT#DEFAULT_GROUP@@provider",
         nacos.weight: "1.0",
         nacos.cluster: "DEFAULT",
         nacos.healthy: "true",
         preserved.register.source: "SPRING_CLOUD"
       },
       uri: "http://192.168.0.101:8080",
       instanceId: null,
       scheme: null
     },
     {
       serviceId: "provider",
       host: "192.168.0.101",
       port: 8082,
       secure: false,
       metadata: {
         nacos.instanceId: "192.168.0.101#8082#DEFAULT#DEFAULT_GROUP@@provider",
         nacos.weight: "1.0",
         nacos.cluster: "DEFAULT",
         nacos.healthy: "true",
         preserved.register.source: "SPRING_CLOUD"
       },
       uri: "http://192.168.0.101:8082",
       instanceId: null,
       scheme: null
     },
     {
       serviceId: "provider",
       host: "192.168.0.101",
       port: 8081,
       secure: false,
       metadata: {
         nacos.instanceId: "192.168.0.101#8081#DEFAULT#DEFAULT_GROUP@@provider",
         nacos.weight: "1.0",
         nacos.cluster: "DEFAULT",
         nacos.healthy: "true",
         preserved.register.source: "SPRING_CLOUD"
       },
       uri: "http://192.168.0.101:8081",
       instanceId: null,
       scheme: null
     }
   ]
   ```



### 3 RestTemplate/Ribbon 服务调用负载均衡

> 服务调用

1. 给 provider 添加一个服务

   ```java
   package com.sugar.controller;
   
   import org.springframework.beans.factory.annotation.Value;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.RestController;
   
   @RestController
   public class ProviderController {
       
       @Value("${server.port}")
       private String port;
       
       @GetMapping("/index")
       public String index() {
           return this.port;
       }
   }
   ```

2. 给 consumer 添加一个随机服务调用方法

   ```java
   package com.sugar.controller;
   
   import lombok.extern.slf4j.Slf4j;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.cloud.client.ServiceInstance;
   import org.springframework.cloud.client.discovery.DiscoveryClient;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.RestController;
   import org.springframework.web.client.RestTemplate;
   
   import java.util.List;
   import java.util.concurrent.ThreadLocalRandom;
   
   @RestController
   @Slf4j
   public class ConsumerController {
   
       @Autowired
       private DiscoveryClient discoveryClient;
       @Autowired
       private RestTemplate restTemplate;  // 不会自动装载
   
       @GetMapping("/instances")
       public List<ServiceInstance> instances() {
           List<ServiceInstance> providers = this.discoveryClient.getInstances("provider");
           return providers;
       }
   
       @GetMapping("/index")
       public String index() {
           // 调用 provider服务中的 index方法
           // 1. 找到 provider实例
           List<ServiceInstance> list = this.discoveryClient.getInstances("provider");
           int index = ThreadLocalRandom.current().nextInt(list.size());
           ServiceInstance serviceInstance = list.get(index);
           String url = serviceInstance.getUri() + "/index";
           // 2. 调用
           log.info("调用的端口是：{}", serviceInstance.getPort());
           return "调用了端口为：" + serviceInstance.getPort() + "的服务，返回结果是：" + this.restTemplate.getForObject(url, String.class);
       }
   }
   ```

> Ribbon 简化服务调用

Ribbon 不是 SpringCloudAlibaba 的组件，而是 Netfilx 提供的。

默认使用 **轮询算法**。

1. RestTemplate 增加注解 `@LoadBalance`

   ```java
   @Bean
   @LoadBalanced
   public RestTemplate restTemplate() {
     return new RestTemplate();
   }
   ```

2. 重写 index() 服务调用方法

   ```java
   @GetMapping("/index")
   public String index() {
     // 如果不使用 Ribbon，provider地址是无法直接解析到的
     return this.restTemplate.getForObject("http://provider/index", String.class);
   }
   ```



**随机算法**

修改 application.yml

```yml
# 服务负载均衡
provider:
  ribbon:
    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule
```



**基于权重算法**

1. 自定义负载均衡类

   ```java
   package com.sugar.config;
   
   import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
   import com.alibaba.cloud.nacos.ribbon.NacosServer;
   import com.alibaba.nacos.api.exception.NacosException;
   import com.alibaba.nacos.api.naming.NamingService;
   import com.alibaba.nacos.api.naming.pojo.Instance;
   import com.netflix.client.config.IClientConfig;
   import com.netflix.loadbalancer.AbstractLoadBalancerRule;
   import com.netflix.loadbalancer.BaseLoadBalancer;
   import com.netflix.loadbalancer.ILoadBalancer;
   import com.netflix.loadbalancer.Server;
   import lombok.extern.slf4j.Slf4j;
   import org.springframework.beans.factory.annotation.Autowired;
   
   @Slf4j
   public class NacosWeightRule extends AbstractLoadBalancerRule {
   
       @Autowired
       private NacosDiscoveryProperties nacosDiscoveryProperties;
   
       @Override
       public void initWithNiwsConfig(IClientConfig iClientConfig) {
           // 读取配置文件
       }
   
       @Override
       public Server choose(Object o) {
           ILoadBalancer loadBalancer = this.getLoadBalancer();
           BaseLoadBalancer baseLoadBalancer = (BaseLoadBalancer) loadBalancer;
           // 获取要请求的微服务名称
           String name = baseLoadBalancer.getName();
           // 后去服务发现的相关API
           NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();
           try {
               Instance instance = namingService.selectOneHealthyInstance(name);
               log.info("选择的实例是port={}, instance={}", instance.getPort(), instance);
               return new NacosServer(instance);
           } catch (NacosException e) {
               e.printStackTrace();
               return null;
           }
       }
   }
   ```

2. 修改 application.yml

   ```yml
   provider:
     ribbon:
       NFLoadBalancerRuleClassName: com.sugar.config.NacosWeightRule
   ```

3. 修改 nacos 注册服务的权重值，默认平权时，就是随机算法

   

### 4 Sentinel 服务限流降级

雪崩效应：一个服务崩溃，导致其他服务连环崩溃

解决方案：

1. 设置线程超时
2. 设置限流（某个服务访问到达上限，其他服务就无法访问该服务了）
3. 熔断器（微服务主流方案，**Sentinel**，Hystrix，如果某个微服务发生异常，就将其阻断，让其他服务不再调用该服务）

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210314185951391.png" alt="image-20210314185951391" style="zoom:50%;" />

相关概念

- 降级：系统将某些功能降级，只提供部分功能，而非全部功能（应对自身故障）
- 限流：系统只接受系统能够承载的访问量，如果超出，则抛出异常（应对自身故障）
- 熔断：调用别的服务，别的服务出现异常故障，则熔断（应对Web故障）



#### 4.1 Sentinel 流控规则