package com.sugar.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

// 启动之后，访问 http://localhost:7001/ 监控页面
@SpringBootApplication
@EnableEurekaServer  // @EnableEurekaServer  服务端的启动类，可以接收服务注册进来
public class ConfigEurekaServer_7001 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigEurekaServer_7001.class, args);
    }
}
