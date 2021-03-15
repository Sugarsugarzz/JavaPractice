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
