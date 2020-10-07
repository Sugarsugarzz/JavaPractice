package com.sugar.controller;

import com.sugar.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Hello控制类")
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    // 只要接口中，返回值中存在实体类，就会被扫描到swagger中
    @GetMapping("/user")
    public User user() {
        return new User();
    }

    // Operation放在方法上
    @ApiOperation("Post测试类")
    @PostMapping("/postt")
    public User postt(@ApiParam("用户") User user) {
        return user;
    }
}
