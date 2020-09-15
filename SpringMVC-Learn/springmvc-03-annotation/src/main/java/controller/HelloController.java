package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {

    // 真实访问地址：项目名/hello/h1
    @RequestMapping("/h1")
    public String hello(Model model) {
        // 向模型中封装数据，添加属性msg与值，可以在JSP页面中直接取出并渲染
        model.addAttribute("msg", "Hello,SpringMVC Annotation!");
        return "hello";  // 会被视图解析器处理  /WEB-INF/jsp/hello.jsp
    }
}
