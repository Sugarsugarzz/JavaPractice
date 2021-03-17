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

在 `provider` 项目测试

1. 在 pom.xml 导入 sentinel依赖

   把provider中的服务开放出来，让actuator进行接收，接收到后让sentinel进行流量控制

   ```xml
   <!--Sentinel-->
   <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
       <version>2.2.1.RELEASE</version>
   </dependency>
   <!--Actuator-->
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-actuator</artifactId>
   </dependency>
   ```

2. 修改 application.yml

   ```yml
   spring:
     cloud:
       nacos:
         discovery:
           server-addr: localhost:8848
       # 配置sentinel的dashboard
       sentinel:
         transport:
           dashboard: localhost:8080
   
     application:
       name: provider
   
   server:
     port: 8083
   
   # 将所有请求开放出来
   management:
     endpoints:
       web:
         exposure:
           include: '*'
   ```

3. 启动 sentinel-dashboard（java -jar /Library/Enviroments/sentinel-dashboard/sentinel-dashboard-1.7.2.jar）

4. 访问 http://localhost:8080，默认登录账号/密码：sentinel/sentinel

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210314231552663.png" alt="image-20210314231552663" style="zoom:20%;" />

5. 在 `簇点链路` 下点击 `流控`，设置QPS阈值，并点击新增

   高级选项中

   流控模式：直接（直接对index限流），关联（比如设置关联资源 /list，如果 /list超过阈值，则 /index 不可用），

   流控效果：

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210314231920689.png" alt="image-20210314231920689" style="zoom:20%;" />

6. 超过阈值的请求将返回如下

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210314232001640.png" alt="image-20210314232001640" style="zoom:30%;" />



**流控规则中的高级选项**

流控模式：直接、关联、链路

> 直接模式（默认）

就是直接对 /index 限流

> 关联模式

比如设置关联资源 /list，当 /list 的 QPS超过阈值时，则 /index 不可用

> 链路模式

对某一个资源进行限流，能够更细粒度的限流。

坑：版本问题，当前版本高，1.6.3是默认收敛所有的URL入口的，这样链路限流是不生效的，需要手动关闭。

1. 导入依赖

   ```xml
   <dependency>
       <groupId>com.alibaba.csp</groupId>
       <artifactId>sentinel-core</artifactId>
       <version>1.7.1</version>
   </dependency>
   <dependency>
       <groupId>com.alibaba.csp</groupId>
       <artifactId>sentinel-web-servlet</artifactId>
       <version>1.7.1</version>
   </dependency>
   ```

2. 修改 application.yml

   ```yml
   spring:
     cloud:
       sentinel:
         filter:
           enabled: false
   ```

3. 新增过滤器类 FilterConfiguration

   ```java
   package com.sugar.config;
   
   import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
   import org.springframework.boot.web.servlet.FilterRegistrationBean;
   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Configuration;
   
   @Configuration
   public class FilterConfig {
       
       @Bean
       public FilterRegistrationBean registrationBean() {
           FilterRegistrationBean registrationBean = new FilterRegistrationBean();
           registrationBean.setFilter(new CommonFilter());
           registrationBean.addUrlPatterns("/*");
           registrationBean.addInitParameter(CommonFilter.WEB_CONTEXT_UNIFY, "false");
           registrationBean.setName("sentinelFilter");
           return registrationBean;
       }
   }
   ```

4. 新增 ProviderService，对需要监控的方法加上 `@SentinelResource`注释

   ```java
   package com.sugar.service;
   
   import com.alibaba.csp.sentinel.annotation.SentinelResource;
   import org.springframework.stereotype.Service;
   
   @Service
   public class ProviderService {
   
       // sentinel一般只能入侵到controller，加了注解后，能够对service进行保护
       @SentinelResource("test")
       public void test() {
           System.out.println("test");
       }
   }
   ```

5. 在 Controller 调用 Service

   ```java
   @Autowired
   private ProviderService providerService;
   
   @GetMapping("/test1")
   public String test1() {
       this.providerService.test();
       return "test1";
   }
   
   @GetMapping("/test2")
   public String test2() {
       this.providerService.test();
       return "test2";
   }
   ```

6. 在 Sentinel 中，显示了到 Service 层的完整链路

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210314233832734.png" alt="image-20210314233832734" style="zoom:30%;" />

7. 这里对 Service层的 test限流，**入口资源**就是调用其的 Controller层入口。

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210314233928146.png" alt="image-20210314233928146" style="zoom:30%;" />

8. 并发请求 /test1 出现异常，但 /test2 正常。

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210314234019520.png" alt="image-20210314234019520" style="zoom:30%;" />

**流控规则中的流控效果**

流控效果：快速失败、Warm Up、排队等待

> 快速失败

直接抛出异常

> Warm Up

预热期流量较少，即 QPS阈值为设定阈值的**三分之一**，预热时间一到，就恢复到设定的阈值。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210314234615363.png" alt="image-20210314234615363" style="zoom:20%;" />



> 排队等待

请求调用失败后，不会立刻抛异常，等待后会再次调一下，如果还是不通则报异常。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210314235206586.png" alt="image-20210314235206586" style="zoom:20%;" />

#### 4.2 Sentinel 降级规则

降级策略：RT、异常比例、异常数

> RT

单个请求的响应事件超过阈值，则进入准降级状态，接下来 **1秒** 内连续 **5个** 请求响应事件均超过**RT阈值**，则进行降级，持续事件为**时间窗口的值**。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210314235600641.png" alt="image-20210314235600641" style="zoom:20%;" />

如下图测试，前五个请求正常，但都超过了设定的阈值 0.1，所以对后续请求进行了降级，在时间窗口内，所有请求都会降级，在时间窗口后恢复。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210315000213898.png" alt="image-20210315000213898" style="zoom:20%;" />

> 异常比例

**每秒**异常数量占通过量的比例**大于阈值**，就进行降级处理，持续事件为**时间窗口的值**。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210315000505165.png" alt="image-20210315000505165" style="zoom:20%;" />



> 异常数

**1分钟内**的异常数超过阈值就进行降级处理，**时间窗口的值要大于 60秒**，否则刚结束熔断又进入下一次熔断了。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210315000729385.png" alt="image-20210315000729385" style="zoom:20%;" />

#### 4.3 Sentinel 热点规则

热点规则是流控规则的耕细粒度操作，可以具体到对某个热点参数的限流，设置限流之后，如果带着限流参数的请求量超过阈值，则进行限流，时间为统计窗口时长。

必须要添加 `@SentinelResource`，即对资源进行流控。

```java
@GetMapping("/hot")
@SentinelResource("hot")
public String hot(
        @RequestParam(value = "num1", required = false) Integer num1,
        @RequestParam(value = "num2", required = false) Integer num2) {
    return num1 + "-" + num2;
}
```

这里对 num1 进行限流

如果带 num1 的请求，超过阈值则限流，不带 num1 则不限流

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210315001304539.png" alt="image-20210315001304539" style="zoom:20%;" />

高级选项：但如果 num1 为 int类型，则数值为10，则限流阈值为 1000

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210315001454788.png" alt="image-20210315001454788" style="zoom:20%;" />



#### 4.4 Sentinel 授权规则

给指定的资源设置流控应用（追加参数），可以对流控应用进行访问权限的设置，具体就是添加白名单和黑名单。

如何给请求指定流控应用，通过实现  RequestOriginParser 接口来完成，代码如下。

```java
package com.sugar.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class RequestOriginParserDefinition implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        // 请求中，需要带 name 参数
        String name = httpServletRequest.getParameter("name");
        if (StringUtils.isEmpty(name)) {
            throw new RuntimeException("name is null");
        }
        return name;
    }
}
```

要让 RequestOriginParserDefinition 生效，需要在配置类中进行配置

```java
package com.sugar.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class SentinelConfig {
    
    @PostConstruct
    public void init() {
        WebCallbackManager.setRequestOriginParser(new RequestOriginParserDefinition());
    }
}
```

设置白名单

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210315002304978.png" alt="image-20210315002304978" style="zoom:20%;" />

请求测试，admin可访问，b不行

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210315002403178.png" alt="image-20210315002403178" style="zoom:40%;" />

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210315002351907.png" alt="image-20210315002351907" style="zoom:40%;" />



### 5 整合 RocketMQ

#### 5.1 安装 RocketMQ

1. 解压 `rocketmq-all-4.7.1-bin-release.zip`

   `unzip rocketmq-all-4.7.1-bin-release.zip`

2. 启动 NameServer

   `nohup ./bin/mqnamesrv &`

3. 检查是否启动成功

   `netstat -an | grep 9876`

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210315193648281.png" alt="image-20210315193648281" style="zoom:70%;" />

4. 启动 Broker（相当于 MQ 的引擎）

   启动之前需要配置编辑文件，修改 JVM 内存设置，默认给的内存 4GB，超过了 JVM

   `cd bin`

   `vim runserver.sh`

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210315193928541.png" alt="image-20210315193928541" style="zoom:40%;" />

   `vim runbroker.sh`

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210315193950495.png" alt="image-20210315193950495" style="zoom:60%;" />

   启动 Broker

   `nohup ./mqbroker -n localhost:9876 -c ../conf/broker.conf &`

   可以查看日志

   `tail -f ~/logs/rocketmqlogs/broker.log`

   出现 boot success 即启动成功.

   **注意**：这里 Broker 是将自己的内网地址给了 NameSrv，某些情况下会出错，需要显式配置.

   Broker 配置文件：conf/broker.conf

   相关博文：https://blog.csdn.net/jpf254/article/details/80748021

   ```xml
   brokerClusterName = DefaultCluster
   brokerName = broker-a
   brokerId = 0
   deleteWhen = 04
   fileReservedTime = 48
   brokerRole = ASYNC_MASTER
   flushDiskType = ASYNC_FLUSH
   #  ======添加的部分======
   # brokerIP1和brokerIP2默认获取本地ip地址，在云服务器上会获取内网ip地址，因此必须显式设置
   brokerIP1=127.0.0.1
   brokerIP2=127.0.0.1
   # 显式配置 NameSrvAddr
   namesrvAddr=127.0.0.1:9876
   ```

5. 测试 RocketMQ

   消息发送

   ```
   cd bin
   export NAMESRV_ADDR=localhost:9876
   ./tools.sh org.apache.rocketmq.example.quickstart.Producer
   ```

   消息接收

   ```
   cd bin
   export NAMESRV_ADDR=localhost:9876
   ./tools.sh org.apache.rocketmq.example.quickstart.Consumer
   ```

6. 关闭 RocketMQ

   ```
   cd bin
   ./myshutdown broker
   ./mqshutdown namesrv
   ```

#### 5.2 安装 RocketMQ 控制台

1. 解压 `rocketmq-externals-rocketmq-console-1.0.0.zip`，修改启动端口和NameSrv地址，打包

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210315200850151.png" alt="image-20210315200850151" style="zoom:90%;" />

   打包命令：`mvn clean package -Dmaven.test.skip=true`

   在 target 目录下得到 jar 包

   启动：`java -jar rocketmq-console-ng-1.0.0.jar`

   访问控制台：http://localhost:9877/

   **注意**：Caused by: org.apache.rocketmq.remoting.exception.RemotingConnectException: connect to <2.0.1.20:10909> failed

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210315201720047.png" alt="image-20210315201720047" style="zoom:50%;" />

   RocketMQ 和 控制台 没有安装在同一台机子上，Linux端需要开放端口才能访问。

   开放 10909 、10911 和 9876 端口

   ```shell
   firewall-cmd --zone=public --add-port=10909/tcp --permanent
   firewall-cmd --zone=public --add-port=10911/tcp --permanent
   firewall-cmd --zone=public --add-port=9876/tcp --permanent
   systemctl restart firewalld.service
   firewall-cmd --reload
   ```

   重新启动控制台项目

#### 5.3 Java 实现消息发送

1. 引入依赖

   ```xml
   <!--rocketmq-->
   <dependency>
     <groupId>org.apache.rocketmq</groupId>
     <artifactId>rocketmq-spring-boot-starter</artifactId>
     <version>2.1.0</version>
   </dependency>
   ```

2. 生产消息

   ```java
   package com.sugar.mq;
   
   import org.apache.rocketmq.client.producer.DefaultMQProducer;
   import org.apache.rocketmq.client.producer.SendResult;
   import org.apache.rocketmq.common.message.Message;
   
   /**
    * MQ 原生调用
    * Producer
    */
   public class Test {
       public static void main(String[] args) throws Exception{
           // 创建消费生产者
           DefaultMQProducer producer = new DefaultMQProducer("myproducer-group");
           // 设置NameServer
           producer.setNamesrvAddr("localhost:9876");
           // 启动生产者
           producer.start();
           // 构建消息对象
           Message message = new Message("myTopic", "myTag", ("Test MQ").getBytes());
           // 发送消息
           SendResult result = producer.send(message, 1000);
           System.out.println(result);
           // 关闭生产者
           producer.shutdown();
       }
   }
   ```

3. 执行后，在控制台查看到该消息

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210315204848249.png" alt="image-20210315204848249" style="zoom:50%;" />

4. 如果报错 `sendDefaultImpl call timeout`，需要开放10911 端口

#### 5.4 Java 实现消息消费

`Provider`是非阻塞的，运行结束即停止；`Consumer`是阻塞执行的，运行不会停止，当消息队列中有新的消息，会立刻消费。

基本功能：实现流量解耦、异步削峰

A需要调用B，但A要做高并发处理，B不需要。可以在A和B之间加一个MQ，将A的消息存入MQ，B从MQ取出，实现解耦过程。

```java
package com.sugar.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * MQ 原生调用
 * Consumer
 */
@Slf4j
public class ConsumerTest {
    public static void main(String[] args) throws Exception{
        // 创建消息消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("myconsumer-group");
        // 设置NameServer
        consumer.setNamesrvAddr("127.0.0.1:9876");
        // 指定订阅的主题和标签
        consumer.subscribe("myTopic", "*");
        // 回调函数
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                log.info("Message=>{}", list);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 启动消费者
        consumer.start();
    }
}
```

#### 5.5 SpringBoot 整合 RocketMQ

> Provider

在 `provider` 项目

1. 引入依赖

   ```xml
   <!--rocketmq-->
   <dependency>
     <groupId>org.apache.rocketmq</groupId>
     <artifactId>rocketmq-spring-boot-starter</artifactId>
     <version>2.1.0</version>
   </dependency>
   <!--rocketmq-client-->
   <dependency>
     <groupId>org.apache.rocketmq</groupId>
     <artifactId>rocketmq-client</artifactId>
     <version>4.7.0</version>
   </dependency>
   ```

2. application.yml

   ```yml
   # RocketMQ
   rocketmq:
     name-server: 127.0.0.1:9876
     producer:
       group: myprovider
   ```

3. Order（生产订单）

   ```java
   package com.sugar.entity;
   
   import lombok.AllArgsConstructor;
   import lombok.Data;
   import lombok.NoArgsConstructor;
   
   import java.util.Date;
   
   /**
    * 订单类
    */
   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   public class Order {
       private Integer id;
       private String buyerName;
       private String buyerTel;
       private String address;
       private Date createDate;
   }
   ```

4. Controller

   ```java
   package com.sugar.controller;
   
   import com.sugar.entity.Order;
   import lombok.extern.slf4j.Slf4j;
   import org.apache.rocketmq.spring.core.RocketMQTemplate;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.RestController;
   
   import java.util.Date;
   
   /**
    * RocketMQ Provider
    */
   @RestController
   @Slf4j
   public class MqController {
   
       @Autowired
       private RocketMQTemplate rocketMQTemplate;
   
       @GetMapping("/create")
       public Order create() {
           Order order = new Order(1, "张三", "1300123", "软件园", new Date());
           this.rocketMQTemplate.convertAndSend("orderTopic", order);
           return order;
       }
   }
   ```

> Consumer

在 `consumer` 项目

1. 引入依赖

   ```xml
   <!--rocketmq-->
   <dependency>
     <groupId>org.apache.rocketmq</groupId>
     <artifactId>rocketmq-spring-boot-starter</artifactId>
     <version>2.1.0</version>
   </dependency>
   <!--rocketmq-client-->
   <dependency>
     <groupId>org.apache.rocketmq</groupId>
     <artifactId>rocketmq-client</artifactId>
     <version>4.7.0</version>
   </dependency>
   ```

2. application.yml

   ```yml
   # RocketMQ
   rocketmq:
     name-server: 127.0.0.1:9876
   ```

3. Service

   ```java
   package com.sugar.service;
   
   import com.sugar.entity.Order;
   import lombok.extern.slf4j.Slf4j;
   import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
   import org.apache.rocketmq.spring.core.RocketMQListener;
   import org.springframework.stereotype.Service;
   
   /**
    * RocketMQ Consumer
    * 由于IOC自动注入，项目启动后就会自动监听执行
    */
   @Slf4j
   @Service
   @RocketMQMessageListener(consumerGroup = "myConsuer", topic = "orderTopic")
   public class SmsService implements RocketMQListener<Order> {
       @Override
       public void onMessage(Order order) {
           log.info("新订单{}, 发短信", order);
       }
   }
   ```

效果：只要调用 Provider create接口，Consumer就能即时从 MQ 获取消息。



### 6 Gateway 服务网关

分布式架构下，会对服务进行拆分，不同的服务负责各自的功能，实现架构层面的解耦合。

拆分后，如果微服务过多，是不利于开发的。比如每个服务都有自己的URL，此时另有一个服务需要调用这些服务，就需要记这所有的URL。 ==>> API网关！

第一代网关是 Zuul，由 Netfilx 开发。

Gateway 性能更加强大，是 Spring Cloud 官方提供的网关。

**Gateway 是基于 Netty，而不是 Servlet，所以与 Servlet 不兼容，工程中不能出现 Servlet 组件！**

网关中不需要写业务，只需要写网关映射。

#### 6.1 Gateway 原生

1. pom.xml

   **注意**：一定不能出现 spring web 的依赖，因为 Gateway 与 Servlet 不兼容。

   ```xml
   # 一定不能出现的依赖！
   <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-web</artifactId>
   </dependency>
   ```

   ```xml
   # Gateway的依赖
   <dependency>
     <groupId>org.springframework.cloud</groupId>
     <artifactId>spring-cloud-starter-gateway</artifactId>
     <version>2.2.5.RELEASE</version>
   </dependency>
   ```

2. application.yml

   ```yml
   server:
     port: 8181
   spring:
     application:
       name: gateway
     # Gateway 配置
     cloud:
       gateway:
         discovery:
           locator:
             enabled: true
         # 原生写法，与Nacos无关
         routes:
           - id: provider_route  # 后续限流需要用，自定义id
             uri: http://localhost:8081  # 网关需要映射的地址
             predicates:  # 如何去映射
               - Path=/provider/**
             # 去掉第一个前缀 provider，否则映射的地址为 http://localhost:8081/provider/list，实际需要 http://localhost:8081/list
             filters:
               - StripPrefix=1
               
        # 去Nacos发现服务，只要将以上原生写法注释掉即可
   ```

   此时，访问 http://localhost:8181/provider/list 相当于访问 http://localhost:8081/list

   出现 404 记得检查是否设置 filters.

#### 6.2 Gateway 配合 Nacos

以上的配置其实没有用到 nacos，现在让 Gateway 直接去 nacos 中发现服务

1. pom.xml 引入 nacos

   ```xml
   <!--Nacos-->
   <dependency>
     <groupId>com.alibaba.cloud</groupId>
     <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
     <version>2.2.1.RELEASE</version>
   </dependency>
   ```

2. application.yml

   ```yml
   server:
     port: 8181
   spring:
     application:
       name: gateway
     # Gateway 配置
     cloud:
       gateway:
         discovery:
           locator:
             enabled: true
     # 自动从Nacos发现服务，无需配置routes
   ```

3. 启动项目后，查看 Nacos 中 gateway 是否注册成功

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210316123010606.png" alt="image-20210316123010606" style="zoom:50%;" />

4. 访问：http://localhost:8181/provider/list，依然能够访问通。 

   格式：http://GatewayHost:Port/NacosServiceName/*

#### 6.3 Gateway 限流（使用 Sentinel Gateway Adater，不使用 Nacos）

> 基于 路由 限流

1. 先去掉 pom.xml 中的 Nacos依赖，取消 application.yml 中的注释。

2. pom.xml 加入 Sentinel Gateway Adapter 依赖

   ```xml
   <!--Sentinel + Gateway Adapter-->
   <dependency>
     <groupId>com.alibaba.csp</groupId>
     <artifactId>sentinel-spring-cloud-gateway-adapter</artifactId>
     <version>1.8.0</version>
   </dependency>
   <!--Gateway-->
   <dependency>
     <groupId>org.springframework.cloud</groupId>
     <artifactId>spring-cloud-starter-gateway</artifactId>
     <version>2.2.5.RELEASE</version>
   </dependency>
   ```

3. application.yml

   ```yml
   server:
     port: 8181
   spring:
     application:
       name: gateway
     # Gateway 配置
     cloud:
       gateway:
         discovery:
           locator:
             enabled: true
         # 原生写法，与Nacos无关
         routes:
           - id: provider_route  # 后续限流需要用，自定义id
             uri: http://localhost:8081  # 网关需要映射的地址
             predicates:  # 如何去映射
               - Path=/provider/**
             filters:
               - StripPrefix=1
   ```

4. 路由限流配置类

   ```java
   package com.sugar.config;
   
   import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
   import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
   import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
   import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
   import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
   import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
   import org.springframework.beans.factory.ObjectProvider;
   import org.springframework.cloud.gateway.filter.GlobalFilter;
   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Configuration;
   import org.springframework.core.Ordered;
   import org.springframework.core.annotation.Order;
   import org.springframework.http.HttpStatus;
   import org.springframework.http.MediaType;
   import org.springframework.http.codec.ServerCodecConfigurer;
   import org.springframework.web.reactive.function.BodyInserters;
   import org.springframework.web.reactive.function.server.ServerResponse;
   import org.springframework.web.reactive.result.view.ViewResolver;
   import org.springframework.web.server.ServerWebExchange;
   import reactor.core.publisher.Mono;
   
   import javax.annotation.PostConstruct;
   import java.util.*;
   
   @Configuration
   public class GatewayConfig {
   
       private final List<ViewResolver> viewResolvers;
       private final ServerCodecConfigurer serverCodecConfigurer;
   
       public GatewayConfig(ObjectProvider<List<ViewResolver>> viewResolversProvider, ServerCodecConfigurer serverCodecConfigurer) {
           this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
           this.serverCodecConfigurer = serverCodecConfigurer;
       }
   
       // 配置限流的异常处理
       @Bean
       @Order(Ordered.HIGHEST_PRECEDENCE)
       public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
           return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
       }
   
       // 配置初始化的限流参数（重要）
       // 基于路由限流
       @PostConstruct
       public void initGatewayRules() {
           Set<GatewayFlowRule> rules = new HashSet<>();
           rules.add(
                   new GatewayFlowRule("provider_route")  // 与需要限流的 ${spring.cloud.routes.id} 一致
                           .setCount(1)  // 阈值（个）
                           .setIntervalSec(1)  // 时间窗口（秒）
           );
           GatewayRuleManager.loadRules(rules);
       }
   
       // 初始化限流过滤器
       @Bean
       @Order(Ordered.HIGHEST_PRECEDENCE)
       public GlobalFilter sentinelGatewayFilter() {
           return new SentinelGatewayFilter();
       }
   
       // 自定义限流异常页面（重点）
       // 客户端限流后返回的内容
       @PostConstruct
       public void initBlockHandlers() {
           BlockRequestHandler blockRequestHandler = new BlockRequestHandler() {
               @Override
               public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
                   Map<String, Object> map = new HashMap<>();
                   map.put("code", 0);
                   map.put("msg", "被限流了");
                   return ServerResponse.status(HttpStatus.OK)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromObject(map));
               }
           };
           GatewayCallbackManager.setBlockHandler(blockRequestHandler);
       }
   }
   ```

5. 启动测试，请求超过设定 QPS 后，返回数据

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210316125321656.png" alt="image-20210316125321656" style="zoom:60%;" />

6. 目前配置的限流是针对网关的，如果直接访问 http://localhost:8081/list 是不限流的。



> 基于 API 分组限流
>
> ​	对 API 进行分组，分组配置限流，提高效率

1. 修改限流配置类，添加基于 API 分组限流的方法，修改初始化限流参数。

2. 在 `provider` 项目中，添加 GatewayController，分组

   ```java
   package com.sugar.controller;
   
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.RestController;
   
   @RestController
   public class GatewayController {
   
       @GetMapping("/api1/demo1")
       public String demo1() {
           return "demo";
       }
   
       @GetMapping("/api1/demo2")
       public String demo2() {
           return "demo";
       }
   
       @GetMapping("/api2/demo1")
       public String demo3() {
           return "demo";
       }
   
       @GetMapping("/api2/demo2")
       public String demo4() {
           return "demo";
       }
   }
   ```

3. 在 `gateway` 项目中，定义 API分支配置类

   ```java
   package com.sugar.config;
   
   import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
   import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
   import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
   import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
   import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
   import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
   import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
   import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
   import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
   import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
   import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
   import org.springframework.beans.factory.ObjectProvider;
   import org.springframework.cloud.gateway.filter.GlobalFilter;
   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Configuration;
   import org.springframework.core.Ordered;
   import org.springframework.core.annotation.Order;
   import org.springframework.http.HttpStatus;
   import org.springframework.http.MediaType;
   import org.springframework.http.codec.ServerCodecConfigurer;
   import org.springframework.web.reactive.function.BodyInserters;
   import org.springframework.web.reactive.function.server.ServerResponse;
   import org.springframework.web.reactive.result.view.ViewResolver;
   import org.springframework.web.server.ServerWebExchange;
   import reactor.core.publisher.Mono;
   
   import javax.annotation.PostConstruct;
   import java.util.*;
   
   @Configuration
   public class GatewayConfigFenzu {
   
       private final List<ViewResolver> viewResolvers;
       private final ServerCodecConfigurer serverCodecConfigurer;
   
       public GatewayConfigFenzu(ObjectProvider<List<ViewResolver>> viewResolversProvider, ServerCodecConfigurer serverCodecConfigurer) {
           this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
           this.serverCodecConfigurer = serverCodecConfigurer;
       }
   
       // 配置限流的异常处理
       @Bean
       @Order(Ordered.HIGHEST_PRECEDENCE)
       public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
           return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
       }
   
       // 配置初始化的限流参数（重要）
       // 基于 API分组 限流
       @PostConstruct
       public void initGatewayRules() {
           Set<GatewayFlowRule> rules = new HashSet<>();
           rules.add(new GatewayFlowRule("provider_api1").setCount(1).setIntervalSec(1));
           rules.add(new GatewayFlowRule("provider_api2").setCount(1).setIntervalSec(1));
           GatewayRuleManager.loadRules(rules);
       }
   
       // 初始化限流过滤器
       @Bean
       @Order(Ordered.HIGHEST_PRECEDENCE)
       public GlobalFilter sentinelGatewayFilter() {
           return new SentinelGatewayFilter();
       }
   
       // 自定义限流异常页面（重点）
       // 客户端限流后返回的内容
       @PostConstruct
       public void initBlockHandlers() {
           BlockRequestHandler blockRequestHandler = new BlockRequestHandler() {
               @Override
               public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
                   Map<String, Object> map = new HashMap<>();
                   map.put("code", 0);
                   map.put("msg", "被限流了");
                   return ServerResponse.status(HttpStatus.OK)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromObject(map));
               }
           };
           GatewayCallbackManager.setBlockHandler(blockRequestHandler);
       }
       
       // 自定义API分组
       @PostConstruct
       private void initCustomizedApis() {
           Set<ApiDefinition> definitions = new HashSet<>();
           ApiDefinition api1 = new ApiDefinition("provider_api1")
                   .setPredicateItems(new HashSet<ApiPredicateItem>() {{
                       add(new ApiPathPredicateItem().setPattern("/provider/api1/**")
                               .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
                   }});
           ApiDefinition api2 = new ApiDefinition("provider_api2")
                   .setPredicateItems(new HashSet<ApiPredicateItem>() {{
                       add(new ApiPathPredicateItem().setPattern("/provider/api2/demo1"));
                   }});
           definitions.add(api1);
           definitions.add(api2);
           GatewayApiDefinitionManager.loadApiDefinitions(definitions);
       }
   }
   ```

4. 对 http://localhost:8181/provider/api1/** 和 http://localhost:8181/provider/api2/demo1 限流！

#### 6.4 Gateway 限流（使用 Sentinel Gateway Adapter 和 Nacos）

1. 开启 Nacos 依赖

   ```xml
   <dependency>
     <groupId>com.alibaba.cloud</groupId>
     <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
     <version>2.2.1.RELEASE</version>
   </dependency>
   ```

2. 去掉 application.yml 的 routes

   ```yml
   server:
     port: 8181
   spring:
     application:
       name: gateway
     # Gateway 配置
     cloud:
       gateway:
         discovery:
           locator:
             enabled: true
         # 原生写法，与Nacos无关
   #      routes:
   #        - id: provider_route  # 后续限流需要用，自定义id
   #          uri: http://localhost:8081  # 网关需要映射的地址
   #          predicates:  # 如何去映射
   #            - Path=/provider/**
   #          # 去掉第一个前缀 provider，否则映射的地址为 http://localhost:8081/provider/list，实际需要 http://localhost:8081/list
   #          filters:
   #            - StripPrefix=1
   ```

3. API 分组配置类中，**需要将 provider 改为 nacos discovery 中的服务名**

   ```java
   ApiDefinition api2 = new ApiDefinition("provider_api2")
     .setPredicateItems(new HashSet<ApiPredicateItem>() {{
       add(new ApiPathPredicateItem().setPattern("/provider/api2/demo1"));
     }});
   ```



### 7 分布式事务

#### 7.1 模拟分布式事务异常

多个服务对应不同的数据库。

Seata 对业务零入侵，高性能解决分布式事务问题。

**三个组件：**

- 事务协调器：维护全局事务的运行状态，负责全局事务的提交和回滚
- 事务管理器（TransactionManager）：负责开启、发起全局事务的提交和回滚
- 资源管理器（ResourceManager）：本地事务

由全局事务通知本地事务进行回滚。全局事务都会写在Log中，由Log生成事务回滚指令。

**两段式提交：**

- 第一阶段：使用 Seata JDBC 的代理数据源通过对业务的 SQL 解析，利用本地事务的特性，将业务数据更新、回滚的日志写到同一个本地事务中进行提交，同时把每次的业务操作进行存储，分支的本地事务可以在全局事务的第一阶段进行提交，并且马上释放本地事务锁定的资源。
- 第二阶段：最后操作全局事务，分支事务已完成提交，不需要协同处理了，只需要异步请求回滚日志即可，如果操作的是全局回滚，采用协调器来进行回滚操作，生成相应的SQL，完成

**小结：**

Seata 就是将各个微服务事务进行统一管理，在注册中心 Nacos 注册一个全局事务的服务，如果分布式调用需要回滚，由全局事务分别通知每个微服务的事务进行本地回滚。

> 代码模拟分布式事务异常

创建两个项目 `order`，`pay`

1. 引入依赖 pom.xml

   ```xml
   <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-jdbc</artifactId>
   </dependency>
   <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-web</artifactId>
   </dependency>
   
   <dependency>
     <groupId>mysql</groupId>
     <artifactId>mysql-connector-java</artifactId>
     <scope>runtime</scope>
   </dependency>
   <dependency>
     <groupId>org.projectlombok</groupId>
     <artifactId>lombok</artifactId>
     <optional>true</optional>
   </dependency>
   ```

2. 建两个数据库 `order`、`pay`，供两个微服务进行访问

   创建 同名数据库同名表，添加 `id`和`username`两个字段

3. 两个服务的 application.yml

   ```yml
   server:
     port: 8010
   spring:
     application:
       name: order
     datasource:
       driver-class-name: com.mysql.cj.jdbc.Driver
       username: root
       password: 123456
       url: jdbc:mysql://localhost:3306/order
   ```

   ```yml
   server:
     port: 8020
   spring:
     application:
       name: order
     datasource:
       driver-class-name: com.mysql.cj.jdbc.Driver
       username: root
       password: 123456
       url: jdbc:mysql://localhost:3306/pay
   ```

4. 两个服务的 Service

   ```java
   package com.sugar.service;
   
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.jdbc.core.JdbcTemplate;
   import org.springframework.stereotype.Service;
   
   @Service
   public class OrderService {
       
       @Autowired
       private JdbcTemplate jdbcTemplate;
       
       public void save() {
           this.jdbcTemplate.update("insert into order(username) values ('张三')");
       }
   }
   ```

   ```java
   package com.sugar.service;
   
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.jdbc.core.JdbcTemplate;
   import org.springframework.stereotype.Service;
   
   @Service
   public class PayService {
       
       @Autowired
       JdbcTemplate jdbcTemplate;
       
       public void save() {
           this.jdbcTemplate.update("insert into pay(username) values ('张三')");
       }
   }
   ```

5. 由 Order 调用 Pay，将两个数据同步存入

   由 Order 通过 RestTemplate 调用 Pay 的服务

6. 两个服务的 Controller

   ```java
   package com.sugar.controller;
   
   import com.sugar.service.OrderService;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.RestController;
   import org.springframework.web.client.RestTemplate;
   
   @RestController
   public class OrderController {
   
       @Autowired
       private OrderService orderService;
       @Autowired
       private RestTemplate restTemplate;
   
       @GetMapping("/save")
       public String save() {
           // 定案
           this.orderService.save();
         	int i = 10/0;  // 人为设置异常，模拟分布式事务异常
           // 支付
           this.restTemplate.getForObject("http://localhost:8020/save", String.class);
           return "success";
       }
   }
   ```

   ```java
   package com.sugar.controller;
   
   import com.sugar.service.PayService;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.RestController;
   
   @RestController
   public class PayController {
   
       @Autowired
       private PayService payService;
   
       @GetMapping("/save")
       public String save() {
           this.payService.save();
           return "success";
       }
   }
   ```

7. 测试调用 http://localhost:8081/save，order 存入了数据，但 pay 中没有存入。

#### 7.2 Seata 解决分布式事务异常

配置 Seata

1. 下载解压 `seata-server-0.9.0.zip`

2. 修改两个文件 `conf/register.conf` 和 `nacos-config.txt`

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210316192426551.png" alt="image-20210316192426551" style="zoom:50%;" />

   register.conf

   修改 `register - type` 和 `config - type`，选择 Seata 的注册方式，保留一个即可。

   ```conf
   registry {
     # file 、nacos 、eureka、redis、zk、consul、etcd3、sofa
     type = "nacos"
     nacos {
       serverAddr = "localhost"
       namespace = "public"
       cluster = "default"
     }
   }
   
   config {
     # file、nacos 、apollo、zk、consul、etcd3
     type = "nacos"
     nacos {
       serverAddr = "localhost"
       namespace = "public"
       cluster = "default"
     }
   }
   ```

   nacos-config.txt

   删除一行，添加两行。即删除默认的测试服务，添加两个需要同步事务的新服务。

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210316192849595.png" alt="image-20210316192849595" style="zoom:50%;" />

3. 启动 Nacos，运行 nacos-config.sh 将 Seata 配置导入 Nacos

   进入 conf 目录，终端输入以下，实质上是将 Seata 配置（nacos-config.txt）写入到 Nacos 配置管理中

   ```shell
   cd conf
   sh nacos-config.sh 127.0.0.1
   ```

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210316193536921.png" alt="image-20210316193536921" style="zoom:80%;" />

   执行成功，刷新 Nacos 配置列表

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210316193832553.png" alt="image-20210316193832553" style="zoom:30%;" />

   自定义 nacos-confgi.txt 配置生效

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210316194010747.png" alt="image-20210316194010747" style="zoom:50%;" />

4. 启动 Seata Server   **JDK 8 以上环境无法启动！**

   ```shell
   cd bin
   seata-server.sh -p 8090 -m file  # -m file 通过文件模式启动
   ```

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210316194613415.png" alt="image-20210316194613415" style="zoom:40%;" />

   

5. 检查是否启动成功

   在 Nacos 服务列表中存在 serverAddr，则启动成功.

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210316202255458.png" alt="image-20210316202255458" style="zoom:40%;" />

Seata 服务环境搭建完毕，接下来去应用中添加。

1. 初始化数据库，在两个数据库中添加事务记录表，SQL Seata在 Seata 安装包中已经提供。

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210316202557031.png" alt="image-20210316202557031" style="zoom:50%;" />

2. 直接在两个数据库`order`和`pay`运行脚本。

   ```sql
   -- the table to store seata xid data
   -- 0.7.0+ add context
   -- you must to init this sql for you business databese. the seata server not need it.
   -- 此脚本必须初始化在你当前的业务数据库中，用于AT 模式XID记录。与server端无关（注：业务数据库）
   -- 注意此处0.3.0+ 增加唯一索引 ux_undo_log
   drop table `undo_log`;
   CREATE TABLE `undo_log` (
     `id` bigint(20) NOT NULL AUTO_INCREMENT,
     `branch_id` bigint(20) NOT NULL,
     `xid` varchar(100) NOT NULL,
     `context` varchar(128) NOT NULL,
     `rollback_info` longblob NOT NULL,
     `log_status` int(11) NOT NULL,
     `log_created` datetime NOT NULL,
     `log_modified` datetime NOT NULL,
     `ext` varchar(100) DEFAULT NULL,
     PRIMARY KEY (`id`),
     UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
   ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
   ```

3. `pay`和`order`项目的 pom.xml 添加 Seata 组件和 Nacos Config 组件

   ```xml
   <!--Seata-->
   <dependency>
     <groupId>com.alibaba.cloud</groupId>
     <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
     <version>2.1.1.RELEASE</version>
   </dependency>
   <!--Nacos Config-->
   <dependency>
     <groupId>com.alibaba.cloud</groupId>
     <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
     <version>2.2.1.RELEASE</version>
   </dependency>
   ```

4. 给 JDBCTemplate 添加代理数据源

   **注意：**这里 DataSourceProxy 用 seata 包的，而不是 druid 的。

   ```java
   package com.sugar;
   
   import io.seata.rm.datasource.DataSourceProxy;
   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;
   import org.springframework.context.annotation.Bean;
   import org.springframework.jdbc.core.JdbcTemplate;
   import org.springframework.web.client.RestTemplate;
   
   import javax.sql.DataSource;
   
   @SpringBootApplication
   public class OrderApplication {
   
       public static void main(String[] args) {
           SpringApplication.run(OrderApplication.class, args);
       }
   
       @Bean
       public RestTemplate restTemplate() {
           return new RestTemplate();
       }
   
       @Bean
       public JdbcTemplate jdbcTemplate(DataSource dataSource) {
           return new JdbcTemplate(new DataSourceProxy(dataSource));
       }
   }
   ```

   ```java
   package com.sugar;
   
   import io.seata.rm.datasource.DataSourceProxy;
   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;
   import org.springframework.context.annotation.Bean;
   import org.springframework.jdbc.core.JdbcTemplate;
   
   import javax.sql.DataSource;
   
   @SpringBootApplication
   public class PayApplication {
   
       public static void main(String[] args) {
           SpringApplication.run(PayApplication.class, args);
       }
   
       @Bean
       public JdbcTemplate jdbcTemplate(DataSource dataSource) {
           return new JdbcTemplate(new DataSourceProxy(dataSource));
       }
   }
   ```

5. 将 `conf/register.conf` 复制到两个项目的 `resources`目录下

6. 给两个项目添加 `bootstrap.yml` 读取 Nacos 配置

   ```yml
   spring:
     application:
       name: order  # 一定要与 nacos-config.txt 中配置的相对应
     cloud:
       nacos:
         config:
           server-addr: localhost:8848
           namespace: public
           group: SEATA_GROUP
       alibaba:
         seata:
           tx-service-group: ${spring.application.name}
   ```

   ```yml
   spring:
     application:
       name: pay  # 一定要与 nacos-config.txt 中配置的相对应
     cloud:
       nacos:
         config:
           server-addr: localhost:8848
           namespace: public
           group: SEATA_GROUP
       alibaba:
         seata:
           tx-service-group: ${spring.application.name}
   ```

   tx-service-group 需要和 Nacos 配置中的名称一致

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210316205742898.png" alt="image-20210316205742898" style="zoom:80%;" />

7. 在 Order 调用 Pay 处添加注解 `@GlobalTransactional`

   ```java
   package com.sugar.controller;
   
   import com.sugar.service.OrderService;
   import io.seata.spring.annotation.GlobalTransactional;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.RestController;
   import org.springframework.web.client.RestTemplate;
   
   @RestController
   public class OrderController {
   
       @Autowired
       private OrderService orderService;
       @Autowired
       private RestTemplate restTemplate;
   
       @GetMapping("/save")
       @GlobalTransactional
       public String save() {
           // 定案
           this.orderService.save();
           int i = 10/0;  // 人为设置异常，模拟分布式事务异常
           // 支付
           this.restTemplate.getForObject("http://localhost:8020/save", String.class);
           return "success";
       }
   }
   ```

8. 测试，出现分布式异常后，实现全局回滚，两个数据库都不会写入数据

