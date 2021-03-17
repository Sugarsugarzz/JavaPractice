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
//        int i = 10/0;  // 人为设置异常，模拟分布式事务异常
        // 支付
        this.restTemplate.getForObject("http://localhost:8020/save", String.class);
        return "success";
    }
}
