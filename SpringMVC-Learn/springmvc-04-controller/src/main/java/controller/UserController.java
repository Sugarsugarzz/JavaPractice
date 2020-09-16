package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.User;

@Controller
@RequestMapping("/user")
public class UserController {

    // localhost:8080/user/t1?name=xxx;
    @GetMapping("/t1")
    public String test1(@RequestParam("username") String name, Model model) {

        // 1.接收前端参数（请求参数名与方法参数名保持一致，前端传递过来的加上@RequestParam，比较规范）
        System.out.println("接收到前端参数 " + name);
        // 2.将返回的结果传递给前端
        model.addAttribute("msg", name);
        // 3.视图跳转
        return "test";
    }

    // 前端接收的是一个对象，id，name，age
    /*
        1. 接收前端用户传递的参数，判断参数的名字，假设名字直接在方法上，可以直接使用
        2. 假设传递的是一个对象User，就会匹配User对象中的属性字段名，如果名字一致则OK，否则匹配不到
     */
    @GetMapping("/t2")
    public String test2(User user) {

        System.out.println(user);

        return "test";
    }

    // ModelMap
    @GetMapping("/t3")
    public String test3(ModelMap modelMap) {
        modelMap.addAttribute("msg", "hello");
        return "test";
    }
}
