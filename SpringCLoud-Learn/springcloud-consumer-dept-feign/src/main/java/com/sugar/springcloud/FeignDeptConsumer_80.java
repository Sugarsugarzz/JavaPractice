package com.sugar.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

// Ribbon和Eureka整合以后，客户端可以直接调用，而不用关心端口号
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.sugar.springcloud"})
// 在微服务启动的时候，就能去加载自定义的Ribbon类
public class FeignDeptConsumer_80 {
    public static void main(String[] args) {
        SpringApplication.run(FeignDeptConsumer_80.class, args);
    }
}
