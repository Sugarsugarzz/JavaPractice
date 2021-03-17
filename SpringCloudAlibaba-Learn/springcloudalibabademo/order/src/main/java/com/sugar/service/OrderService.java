package com.sugar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save() {
        this.jdbcTemplate.update("insert into `order`(username) values ('张三')");
    }
}
