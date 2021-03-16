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
