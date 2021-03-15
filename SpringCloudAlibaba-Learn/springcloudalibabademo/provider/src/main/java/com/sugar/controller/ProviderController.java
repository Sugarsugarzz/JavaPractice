package com.sugar.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.sugar.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/index")
    public String index() {
        return this.port;
    }

    @GetMapping("/list")
    public String list() {
        return "list";
    }

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

    @GetMapping("/hot")
    @SentinelResource("hot")
    public String hot(
            @RequestParam(value = "num1", required = false) Integer num1,
            @RequestParam(value = "num2", required = false) Integer num2) {
        return num1 + "-" + num2;
    }
}
