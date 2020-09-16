package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pojo.User;
import utils.JsonUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
//@RestController  // 让该类下所有方法都不走视图解析器，相当于给每个方法注解 @ResponseBody
public class UserController {

//    @RequestMapping(value = "/j1", produces = "application/json;charset=utf-8")  // 原生处理JSON乱码
    @RequestMapping("/j1")
    @ResponseBody  // 它就不会走视图解析器，会直接返回一个字符串（感觉可以用作接口）
    public String json1() throws JsonProcessingException {
        // Jackson  ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        // 对象转JSON
        User user = new User("Sugar", 12, "男");
        String str = mapper.writeValueAsString(user);

        return str;
        /*
        {"name":"Sugar","age":12,"sex":"男"}
         */
    }

    @RequestMapping("/j2")
    @ResponseBody
    public String json2() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        List<User> userList = new ArrayList<User>();

        // 多个对象转JSON
        User user1 = new User("Sugar", 12, "男");
        User user2 = new User("Sugar", 12, "男");
        User user3 = new User("Sugar", 12, "男");
        User user4 = new User("Sugar", 12, "男");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        String str = mapper.writeValueAsString(userList);
        return str;
        /*
        [{"name":"Sugar","age":12,"sex":"男"},{"name":"Sugar","age":12,"sex":"男"},{"name":"Sugar","age":12,"sex":"男"},{"name":"Sugar","age":12,"sex":"男"}]
         */
    }

    @RequestMapping("/j3")
    @ResponseBody
    public String json3() throws JsonProcessingException {

        Date date = new Date();

        // ObjectMapper，时间解析后的默认格式为 Timestamp 时间戳
//        return new ObjectMapper().writeValueAsString(date);
        /*
        1600232137591
         */

        // 自定义日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return new ObjectMapper().writeValueAsString(sdf.format(date));
        /*
        "2020-09-16 12:58:11"
         */

        // 使用 ObjectMapper 来格式化输出
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setDateFormat(sdf);
//        return mapper.writeValueAsString(date);
        /*
        "2020-09-16 13:01:12"
         */

        // 实现JSON工具类
        return JsonUtils.getJson(date, "yyyy-MM-dd HH:mm:ss");

    }

    @RequestMapping("/j4")
    @ResponseBody
    public String json4() {

        List<User> userList = new ArrayList<User>();
        User user1 = new User("Sugar", 12, "男");
        User user2 = new User("Sugar", 12, "男");
        User user3 = new User("Sugar", 12, "男");
        User user4 = new User("Sugar", 12, "男");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        return JSON.toJSONString(userList);
        /*
        [{"age":12,"name":"Sugar","sex":"男"},{"age":12,"name":"Sugar","sex":"男"},{"age":12,"name":"Sugar","sex":"男"},{"age":12,"name":"Sugar","sex":"男"}]
         */
    }

}
