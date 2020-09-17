package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.User;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AjaxController {

    @RequestMapping("/t1")
    public String test() {
        return "hello";
    }

    @RequestMapping("/a1")
    public String a1(String name) {
        if ("sugar".equals(name)) {
            return "true";
        } else {
            return  "false";
        }
    }

    @RequestMapping("/a2")
    public List<User> a2() {
        List<User> users = new ArrayList<User>();
        // 添加数据
        users.add(new User("Sugar", 13, "男"));
        users.add(new User("Sugar2", 1, "男"));
        users.add(new User("Sugar3", 2, "男"));

        return users;
    }

    @RequestMapping("/a3")
    public String a3(String name, String pwd) {
        String msg = "";

        if (name != null) {
            // admin 在数据库中查
            if ("admin".equals(name)) {
                msg = "ok";
            } else {
                msg = "用户名有误";
            }
        }
        if (pwd != null) {
            if ("123456".equals(pwd)) {
                msg = "ok";
            } else {
                msg = "密码有误";
            }
        }
        return msg;

    }
}
