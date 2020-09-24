package com.sugar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

// 在templates目录下的所有页面，只能通过controller来跳转
// 需要模板引擎的支持  thymeleaf
@Controller
public class IndexController {

    @RequestMapping("/test")
    public String test(Model model) {
        model.addAttribute("msg", "<h1>hello 123</h1>");

        model.addAttribute("users", Arrays.asList("sugar", "java"));
        return "test";
    }
}
