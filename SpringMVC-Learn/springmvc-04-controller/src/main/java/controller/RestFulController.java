package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RestFulController {

    // 原来的风格： http://localhost:8080/add?a=1&b=3
    // RestFul：http://localhost:8080/add/a/b

//    @RequestMapping(value="/add/{a}/{b}", method= RequestMethod.GET)
    @GetMapping("/add/{a}/{b}")
    public String test1(@PathVariable int a, @PathVariable int b, Model model) {

        int res = a + b;
        model.addAttribute("msg", "结果" + res);

        return "test";
    }

    @PostMapping("/add/{a}/{b}")
    public String test2(@PathVariable int a, @PathVariable int b, Model model) {

        int res = a + b;
        model.addAttribute("msg", "结果2  " + res);

        return "test";
    }
}
