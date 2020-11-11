package com.sugar.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sugar.springcloud.pojo.Dept;

import com.sugar.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 提供Restful服务
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
