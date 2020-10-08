package com.sugar.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

// Zookeeper：服务注册与发现
// 使用了Dubbo后，尽量不使用@Service注解注册到Spring容器（防止与Spring容器注册的@Service弄混）
// @Service 使其可以被Dubbo扫描到，在项目已启动就自动注册到Zookeeper注册中心
@Service
@Component
public class TicketServiceImpl implements TicketService{
    @Override
    public String getTicket() {
        return "Sugar";
    }
}
