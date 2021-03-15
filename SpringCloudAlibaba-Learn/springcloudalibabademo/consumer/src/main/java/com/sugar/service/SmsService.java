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
