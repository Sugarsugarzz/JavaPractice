## SpringMVC

### BootStrap可视化布局：https://www.bootcss.com/p/layoutit

重点：**SpringMVC的执行流程**

重点实践：**SSM框架整合**

MVC：模型（dao、service）  视图（jsp）  控制器（Servlet）

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200914151408723.png" alt="image-20200914151408723" style="zoom:30%;" />

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200914151652586.png" alt="image-20200914151652586" style="zoom:40%;" />

### 1. SpringMVC执行流程

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200914202319955.png" alt="image-20200914202319955" style="zoom:40%;" />

1. **DispatcherServlet**表示前置控制器，是整个 SpringMVC 的控制中心。用户发出请求，DispatcherServlet 接收请求并拦截请求。
   - 假设请求的 url 为：http://localhost:8080/projectname/hello
     - 服务器域名：http://localhost:8080
     - 部署在服务器上的web站点项目名：projectname
     - 控制器：hello
     - 通过分析，如上url表示为：请求位于服务器localhost:8080上projectname站点的hello控制器
2. **HandlerMapping** 为处理器映射。DispathcerServlet 调用 HandlerMapping.HandlerMapping 根据请求 url 查找 Handler。
3. **HandlerExecution** 表示具体的 Handler，其主要作用是根据 url 查找控制器，如上url被找到控制器为：hello。
4. HandlerExecution 将解析后的信息传递给 DispatcherServlet，如解析控制器映射等。
5. **HandlerAdapter** 表示处理器适配器，其按照特定的规则去执行 Handler。
6. Handler 让具体的 Controller 执行。
7. **Controller** 将具体的执行信息返回给 HandlerAdapter，如 **ModelAndView**、
8. HandlerAdapter 将视图逻辑名或模型传递给 DispatcherServlet。
9. DispatcherServlet 调用视图解析器（**ViewResolver**）来解析 HandlerAdapter 传递的逻辑视图名。
10. 视图解析器将解析的逻辑视图名传递给 DispatcherServlet。
11. DIspatcherServlet 根据视图解析器的视图结果，调用具体的视图。
12. 最终视图呈现给用户。

#### 遇到的问题

SpringMVC项目配置好以后，访问路径，报如下错误：

```
java.lang.ClassNotFoundException: org.springframework.web.servlet.DispatcherServlet
```

**解决方法**：

在 Project Structure > Artifacts 中点击项目，在 Output Layout > WEB-INF 目录下，新建 lib 包，然后点击加号 > Library Files，添加依赖。

### 2. SpringMVC 实现方法

#### 实现方式一：配置文件实现

**web.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!--1. 配置 DispatcherServlet：SpringMVC的核心，请求分发器（前端控制器）-->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!--DispatcherServlet需要绑定SpringMVC的配置文件-->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc-servlet.xml</param-value>
        </init-param>
        <!--启动级别：1（服务器启动，SpringMVC即启动）-->
        <load-on-startup>1</load-on-startup>
    </servlet>

    <!--
    在 SpringMVC 中，/  和  /*  的区别：
    /：只匹配所有的请求，不会匹配jsp页面
    /*：匹配所有的请求，包括jsp页面
    -->
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

</web-app>

```

**springmvc-servlet.xml**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--处理器映射器-->
    <bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>

    <!--处理器适配器-->
    <bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"/>

    <!--视图解析器：模板引擎 Thymeleaf  Freemarker...-->
    <!--视图解析器：DispatcherServlet 给它的 ModelAndView
    1. 获取了 ModelAndView 的数据
    2. 解析 ModelAndView 的视图
    3. 拼接视图名字，找到对应的视图  /WEB-INF/jsp/hello.jsp
    4. 将数据渲染到这个视图上
    -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="internalResourceViewResolver">
        <!--前缀-->
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <!--后缀-->
        <property name="suffix" value=".jsp"/>
    </bean>


    <!--BeanNameUrlHandlerMapping：bean   Handler-->
    <bean id="/hello" class="controller.HelloController"/>

</beans>
```

**HelloController.java**

```java
package controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        ModelAndView mv = new ModelAndView();

        // 业务代码
        String result = "HelloMVC";
        mv.addObject("msg", result);

        // 视图跳转
        mv.setViewName("test");
        return mv;
    }
}
```

#### 实现方式二：注解版

**步骤小结**：

1. 新建一个web项目
2. 导入相关jar包
3. 编写web.xml，注册DispatcherServlet
4. 编写springmvc配置文件
5. 创建对应的控制类Controller，添加注解
6. 完善前端视图与Controller之间的对应
7. 测试调试

使用 SpringMVC 必须配置的三大件：

**处理器映射器、处理器适配器、视图解析器**

通过，只需要**手动配置视图解析器**，而**处理器映射器**和**处理器适配器**只需要**开启注解驱动**即可，省去大段的xml配置。

**web.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!--1. 配置 DispatcherServlet：SpringMVC的核心，请求分发器（前端控制器）-->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc-servlet.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

</web-app>
```

**springmvc-servlet.xml**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                        https://www.springframework.org/schema/beans/spring-beans.xsd
                        http://www.springframework.org/schema/context
                        https://www.springframework.org/schema/context/spring-context.xsd
                        http://www.springframework.org/schema/mvc
                        https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!--自动扫描包，让指定包下的注解生效，由IOC容器统一管理-->
    <context:component-scan base-package="controller"/>
    <!--让 SpringMVC 不处理静态资源，如 .css .js .html .mp3 .mp4（视图拼接会有误）-->
    <mvc:default-servlet-handler/>
    <!--
    支持mvc注解驱动
        在Spring中一般采用 @RequestMapping 注解来完成映射关系
        要想使 @RequestMapping 注解生效
        必须向上下文注册 DefaultAnnotationHandlerMapping
        和一个 AnnotationMethodHandlerAdapter 实例
        这两个实例分别在类级别和方法级别处理
        而 annotation-driven 配置帮助我们自动完成上述两个实例的注入
    -->
    <mvc:annotation-driven/>


    <!--视图解析器-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="internalResourceViewResolver">
        <!--前缀-->
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <!--后缀-->
        <property name="suffix" value=".jsp"/>
    </bean>

</beans>
```

**HelloController.java**

```java
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
```

### 3. Controller

#### 实现方式一：实现 Controller接口

```java
// 只要实现了 Controller 接口的类，说明这就是一个控制器了
public class ControllerTest1 implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        ModelAndView mv = new ModelAndView();
			
        mv.addObject("msg", "ControllerTest1");
        mv.setViewName("test");

        return mv;
    }
}
```

#### 实现方式二：注解 @Controller

```java
// 代表这个类被Spring接管，被这个注解的类，中的所有方法，如果返回值是String，并且有具体的页面可以跳转，就会被视图解析器解析
@Controller  
public class ControllerTest2 {

    @RequestMapping("/t2")
    public String test2(Model model) {
        model.addAttribute("msg", "ControllerTest2");
        return "test";
    }

    @RequestMapping("/t3")
    public String test3(Model model) {
        model.addAttribute("msg", "ControllerTest3");
        return "test";
    }
}
```

#### @RequestMapping

@RequestMapping注解用于映射 url 到控制器类或一个特定的处理程序方法。可用于类或方法上，用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。

```java
@Controller
public class ControllerTest3 {

    // 不建议在类上加 @RequestMapping，直接在这里写好就行
    @RequestMapping("/c3/t1")
    public String test1(Model model) {
        model.addAttribute("msg", "ControllerTest3");
        return "test";
    }
}
```

### 4. RestFul

RestFul 是一种资源定位及资源操作的风格。不是标准也不是协议，只是一种风格。基于这个风格设计的软件可以更简介、更有层次、更易于实现缓存等机制。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200915200548348.png" alt="image-20200915200548348" style="zoom:40%;" />

在 SpringMVC 中可以使用 **@PathVariable** 注解，让方法参数的值对应绑定到一个 URI 模板变量上。

```java
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
```

SpringMVC 的 @RequestMapping 注解能够处理 HTTP 请求的方法，比如 GET、PUT、POST、DELETE 以及 PATCH。

**所有地址栏请求默认都会是 HTTP GET 类型的。**

方法界别的注解变体有如下几个：组合注解

```java
@GetMapping
@PostMapping
@PutMapping
@DeleteMapping
@PatchMapping
```

@GetMapping 组合注解，是 @RequestMapping(method= RequestMethod.GET) 的简写，会使用较多。

**优点：**

- 使路径更加简洁、可复用、高效安全
- 获得参数更加方便，框架会自动进行类型转换
- 通过路径变量的类型可以约束访问参数，如果类型不一样，则访问不到对应的请求方法。

### 5. 转发和重定向

#### ModelAndView

设置 ModelAndView 对象，根据 view 的名称，由视图解析器跳转到指定的页面。

**页面：【视图解析器前缀】+ viewName + 【视图解析器后缀】**



#### ServletAPI

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200915203758788.png" alt="image-20200915203758788" style="zoom:30%;" />

#### SpringMVC

有视图解析器的情况下，默认是转发，重定向加个 redirect: 即可。

```java
package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class ModelTest1 {

//    @RequestMapping("/m1/t1")
//    public String test1(HttpServletRequest request, HttpServletResponse response) {
//
//        HttpSession session = request.getSession();
//        System.out.println(session.getId());
//
//        return "test";
//    }

    @RequestMapping("/m1/t1")
    public String test1(Model model) {

        model.addAttribute("msg", "ModelTest1");
        // 转发
        return "test";
    }

    @RequestMapping("/m1/t2")
    public String test2(Model model) {
        // 重定向
        model.addAttribute("msg", "ModelTest1");
        return "redirect:/index.jsp";
    }
}
```

### 6. 数据处理

#### 6.1 处理提交的数据

##### 1. 提交的域名称和处理方法的参数名一致

提交数据：http://localhost:8080/hello?name=sugar

处理方法：

```java
@RequestMapping("/hello")
public String hello(String name) {
  	System.out.println(name);
	  return "hello";
}
```

##### 2. 提交的域名称和处理方法的参数名不一致

提交数据：http://localhost:8080/hello?username=sugar

处理方法：

```java
@RequestMapping("/hello")
public String hello(@RequestParam("username") String name) {
  	System.out.println(name);
	  return "hello";
}
```

##### 3. 提交的是一个对象

要求提交的表单域和对象的书明星一致，参数使用对象即可。

#### 6.2 数据显示到前端

**继承关系：LinkedHashmap -> ModelMap（继承了LinkedHashMap，拥有LinkedHashMap的全部功能） -> Model（精简版，大部分情况下都只使用Model）**

##### 1. 通过ModelAndView

```java
public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
	  ModelAndView mv = new ModelAndView();
  	mv.addObject("msg", "ControllerTest1");
	  mv.setViewName("test");
  	return mv;
}
```

##### 2. 通过ModelMap

```java
@GetMapping("/t3")
public String test3(ModelMap modelMap) {
	  modelMap.addAttribute("msg", "hello");
  	return "test";
}
```

##### 3. 通过Model

```java
@GetMapping("/t1")
public String test1(@RequestParam("username") String name, Model model) {
  System.out.println("接收到前端参数 " + name);
  model.addAttribute("msg", name);
  return "test";
}
```

#### 6.3 乱码问题

Get方法不乱码，Post乱码。

**复现测试步骤**：

1. 在首页编写一个提交表单

   ```html
   <form action="/e/t1" method="post">
       <input type="text" name="name">
       <input type="submit">
   </form>
   ```

2. 后台编写对应的数理类

   ```java
   @Controller
   public class EncodingController {
   
       @PostMapping("/e/t1")
       public String test1(String name, Model model) {
           model.addAttribute("msg", name);
           return "test";
       }
   }
   ```

3. 输入中文测试，发现乱码

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200916103429279.png" alt="image-20200916103429279" style="zoom:30%;" />

**Servlet级解决方法（并不能解决当前乱码，复习一下）：**

1. 编写过滤器类

   ```java
   package filter;
   
   
   import javax.servlet.*;
   import java.io.IOException;
   
   public class EncodingFilter implements Filter {
       public void init(FilterConfig filterConfig) throws ServletException {
   
       }
   
       public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
           request.setCharacterEncoding("utf-8");
           response.setCharacterEncoding("utf-8");
       }
   
       public void destroy() {
   
       }
   }
   ```

2. 在web.xml注册过滤器

   ```xml
   <!--2. 过滤器（处理中文乱码）-->
   <filter>
    	  <filter-name>encoding</filter-name>
   	  <filter-class>filter.EncodingFilter</filter-class>
   </filter>
   <filter-mapping>
   	  <filter-name>encoding</filter-name>
   	  <url-pattern>/</url-pattern>
   </filter-mapping>
   ```

##### SpringMVC提供的乱码过滤器（采用）：

1. 在web.xml注册过滤器

   ```xml
   <!--2. 过滤器（处理中文乱码，SpringMVC提供的乱码过滤器）-->
   <filter>
   	  <filter-name>encoding</filter-name>
     	<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
   	  <init-param>
     	 	 <param-name>encoding</param-name>
     	   <param-value>utf-8</param-value>
   	  </init-param>
   </filter>
   <filter-mapping>
     	<filter-name>encoding</filter-name>
   	  <url-pattern>/*</url-pattern>
   </filter-mapping>
   ```

#### 6.4 JSON

JSON（JavaScript Object Notation，JS对象标记）是一种轻量级的数据交换格式。

- 采用完全独立于编程语言的文本格式来存储和表示数据。
- 简洁和清晰的层次结构使得 JSON 称为理想的数据交换语言。
- 易于阅读和编写，也易于机器解析和生成，有效提升网络传输效率。

**JSON 键值对**是用来保存 JavaScript 对象的一种方式，键值对组合中的键名卸载前面并用双引号""包括，使用冒号：分隔，然后紧接着值。

- 对象表示为键值对，数据用逗号分隔
- 花括号保存对象
- 方括号保存数组

> 前端       <------>       后端

##### 1. JavaScript 对象与 JSON 互转

```html
    <script type="text/javascript">
        // 编写一个 JavaScript 对象
        var user = {
            name: "Sugar",
            age: 3,
            sex: "男"
        };
        // 将JS对象转换为JSON对象
        var json = JSON.stringify(user);
        // 将 JSON对象转换为 JS对象
        var obj = JSON.parse(json);

        console.log(obj);
        console.log(json);
        console.log(user);
    </script>
```

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200916110854545.png" alt="image-20200916110854545" style="zoom:50%;" />

##### 2. Jackson

```xml
<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.11.0</version>
</dependency>
```

```java
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
}
```

```java
public class JsonUtils {

    public static String getJson(Object obj, String dateFormat) {
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        mapper.setDateFormat(sdf);

        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
```

##### 原生解决乱码问题：

需要设置下编码格式为 utf-8，一级它返回的类型。

通过 @RequestMapping 的 produces 属性来实现.

```java
// produces：指定响应体返回类型和编码
@RequestMapping(value = "/j1", produces = "application/json;charset=utf-8")
```

##### SpringMVC 解决乱码问题：

在 springmvc-servlet.xml 中配置

```xml
    <!--JSON乱码问题（SpringMVC统一解决）-->
    <mvc:annotation-driven>
        <mvc:message-converters register-defaults="true">
            <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                <constructor-arg value="UTF-8"/>
            </bean>
            <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
                <property name="objectMapper">
                    <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                        <property name="failOnEmptyBeans" value="false"/>
                    </bean>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>
```

##### 3. Faskjson

```xml
<!-- https://mvnrepository.com/artifact/com.alibaba/fastjson -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.73</version>
</dependency>
```

```java
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
```

### 7. 整合 SSM（书籍管理系统）

#### 环境要求

- IDAE
- MySQL
- Tomcat
- Maven
- 掌握 MySQL、Spring、JavaWeb 以及 MyBatis 知识，简单前端知识。

#### 数据库环境

存放书籍数据的数据库表

```mysql
CREATE DATABASE ssmbuild;
USE ssmbuild;
CREATE TABLE `books`(
`bookID` INT NOT NULL AUTO_INCREMENT COMMENT '书id',
`bookName` VARCHAR(100) NOT NULL COMMENT '书名',
`bookCounts` INT NOT NULL COMMENT '数量',
`detail` VARCHAR(200) NOT NULL COMMENT '描述',
KEY `bookID`(`bookID`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

INSERT INTO `books`(`bookID`,`bookName`,`bookCounts`,`detail`)VALUES
(1,'Java',1,'从入门到放弃'),
(2,'MySQL',10,'从删库到跑路'),
(3,'Linux',5,'从进门到进牢')
```

#### 搭建基本环境

1. 新建 Maven 项目，添加 Web 支持。

2. 导入相关的 pom 依赖。

   ```xml
       <!--依赖：Junit、数据库驱动、连接池、servlet、jsp、mybatis、mybatis-spring、spring-->
       <dependencies>
           <!--Junit-->
           <dependency>
               <groupId>junit</groupId>
               <artifactId>junit</artifactId>
               <version>4.12</version>
           </dependency>
           <!--数据库驱动-->
           <dependency>
               <groupId>mysql</groupId>
               <artifactId>mysql-connector-java</artifactId>
               <version>[8.0.16,)</version>
           </dependency>
           <!--数据库连接池：c3p0-->
           <dependency>
               <groupId>com.mchange</groupId>
               <artifactId>c3p0</artifactId>
               <version>0.9.5.2</version>
           </dependency>
           <!--Servlet - JSP-->
           <dependency>
               <groupId>javax.servlet</groupId>
               <artifactId>servlet-api</artifactId>
               <version>2.5</version>
           </dependency>
           <dependency>
               <groupId>javax.servlet.jsp</groupId>
               <artifactId>jsp-api</artifactId>
               <version>2.2</version>
           </dependency>
           <dependency>
               <groupId>javax.servlet</groupId>
               <artifactId>jstl</artifactId>
               <version>1.2</version>
           </dependency>
   
           <!--MyBatis-->
           <dependency>
               <groupId>org.mybatis</groupId>
               <artifactId>mybatis</artifactId>
               <version>3.5.2</version>
           </dependency>
           <dependency>
               <groupId>org.mybatis</groupId>
               <artifactId>mybatis-spring</artifactId>
               <version>2.0.2</version>
           </dependency>
   
           <!--Spring-->
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-webmvc</artifactId>
               <version>5.2.7.RELEASE</version>
           </dependency>
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-jdbc</artifactId>
               <version>5.0.7.RELEASE</version>
           </dependency>
       </dependencies>
   ```

3. Maven 资源过滤设置。

   ```xml
       <!--静态资源导出问题-->
       <build>
           <resources>
               <resource>
                   <directory>src/main/java</directory>
                   <includes>
                       <include>**/*.properties</include>
                       <include>**/*.xml</include>
                   </includes>
                   <filtering>false</filtering>
               </resource>
               <resource>
                   <directory>src/main/resources</directory>
                   <includes>
                       <include>**/*.properties</include>
                       <include>**/*.xml</include>
                   </includes>
                   <filtering>false</filtering>
               </resource>
           </resources>
       </build>
   ```

4. 建立基本结构和配置框架

   - pojo

   - dao

   - service

   - controller

   - resources/mybatis-config.xml

     ```xml
     <?xml version="1.0" encoding="UTF-8"?>
     <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
     
     <configuration>
     
     </configuration>
     ```

   - applicationContext.xml

     ```xml
     <?xml version="1.0" encoding="UTF-8" ?>
     <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:aop="http://www.springframework.org/schema/aop"
            xmlns:context="http://www.springframework.org/schema/context"
            xsi:schemaLocation="http://www.springframework.org/schema/beans
                             https://www.springframework.org/schema/beans/spring-beans.xsd
                             http://www.springframework.org/schema/context
                             https://www.springframework.org/schema/context/spring-context.xsd
                             http://www.springframework.org/schema/aop
                             https://www.springframework.org/schema/aop/spring-aop.xsd">
         
     </beans>
     ```

#### Mybatis层编写

1. 数据库配置文件 db.properties

   ```properties
   driver=com.mysql.cj.jdbc.Driver
   # 使用MySQL8.0+，需要增加一个时区的配置 &serverTimezone=Asia/Beijing（加上报错了，还是不加了）
   url=jdbc:mysql://localhost:3306/ssmbuild?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true
   username=root
   password=123456
   ```

2. IDEA关联数据库

3. 编写MyBatis的核心配置文件

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
   
   <configuration>
       <!--配置数据源，交给Spring去做-->
       <!--别名-->
       <typeAliases>
           <package name="pojo"/>
       </typeAliases>
       <!--Mapper注册-->
       <mappers>
           <mapper class="dao.BookMapper"/>
       </mappers>
   </configuration>
   ```

4. 实体类 Books

   ```java
   package pojo;
   
   import lombok.AllArgsConstructor;
   import lombok.Data;
   import lombok.NoArgsConstructor;
   
   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   public class Books {
   
       private int bookID;
       private String bookName;
       private int bookCounts;
       private String detail;
   }
   ```

5. Dao层：Mapper接口  BookMapper

   ```java
   package dao;
   
   import org.apache.ibatis.annotations.Param;
   import pojo.Books;
   
   import java.util.List;
   
   public interface BookMapper {
   
       // 增加一本书
       int addBook(Books books);
   
       // 删除一本书
       int deleteBookById(@Param("id") int id);
   
       // 更新一本书
       int updateBook(Books books);
   
       // 查询一本书
       Books queryBookById(@Param("id") int id);
   
       // 查询全部书
       List<Books> queryAllBook();
   }
   ```

6. Dao层：Mapper接口实现  BookMapper.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
   
   <mapper namespace="dao.BookMapper">
   
       <insert id="addBook" parameterType="Books">
           INSERT INTO books (bookName, bookCounts, detail)
           VALUES (#{bookName}, #{bookCounts}, #{detail})
       </insert>
   
       <delete id="deleteBookById" parameterType="int">
           DELETE FROM books WHERE bookID = #{id}
       </delete>
   
       <update id="updateBook" parameterType="Books">
           UPDATE books SET bookName = #{bookName}, bookCounts = #{bookCounts}, detail = #{detail}
           WHERE bookID = #{bookID}
       </update>
   
       <select id="queryBookById" resultType="Books">
           SELECT * FROM books WHERE bookID = #{id}
       </select>
   
       <select id="queryAllBook" resultType="Books">
           SELECT * FROM books
       </select>
   </mapper>
   ```

7. Service层：Service接口  BookService

   ```java
   package service;
   
   import pojo.Books;
   
   import java.util.List;
   
   public interface BookService {
   
       // 增加一本书
       int addBook(Books books);
   
       // 删除一本书
       int deleteBookById(int id);
   
       // 更新一本书
       int updateBook(Books books);
   
       // 查询一本书
       Books queryBookById(int id);
   
       // 查询全部书
       List<Books> queryAllBook();
   }
   ```

8. Service层：Service接口实现  BookServiceImpl

   ```java
   package service;
   
   import dao.BookMapper;
   import pojo.Books;
   
   import java.util.List;
   
   public class BookServiceImpl implements BookService {
   
       // service层调dao层：组合Dao
       private BookMapper bookMapper;
   
       public void setBookMapper(BookMapper bookMapper) {
           this.bookMapper = bookMapper;
       }
   
       public int addBook(Books books) {
           return bookMapper.addBook(books);
       }
   
       public int deleteBookById(int id) {
           return bookMapper.deleteBookById(id);
       }
   
       public int updateBook(Books books) {
           return bookMapper.updateBook(books);
       }
   
       public Books queryBookById(int id) {
           return bookMapper.queryBookById(id);
       }
   
       public List<Books> queryAllBook() {
           return bookMapper.queryAllBook();
       }
   }
   ```

#### Spring层编写

1. 整合dao层  spring-dao.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:aop="http://www.springframework.org/schema/aop"
          xmlns:context="http://www.springframework.org/schema/context"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
                           https://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           https://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/aop
                           https://www.springframework.org/schema/aop/spring-aop.xsd">
   
       <!--1. Spring关联数据库配置文件-->
       <context:property-placeholder location="classpath:db.properties"/>
   
       <!--2. 连接池
           dbcp：半自动化操作，不能自动连接
           c3p0：自动化操作（自动化加载配置文件，并可以自动设置到对象中）
           druid： hikari：
       -->
       <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
           <property name="driverClass" value="${driver}" />
           <property name="jdbcUrl" value="${url}"/>
           <property name="user" value="${username}"/>
           <property name="password" value="${password}"/>
   
           <!--c3p0连接池的私有属性-->
           <property name="maxPoolSize" value="30"/>
           <property name="minPoolSize" value="10"/>
           <!--关闭连接后不自动commit-->
           <property name="autoCommitOnClose" value="false"/>
           <!--获取连接超时时间-->
           <property name="checkoutTimeout" value="10000"/>
           <!--当获取连接失败重试次数-->
           <property name="acquireRetryAttempts" value="2"/>
       </bean>
   
       <!--3. SqlSessionFactory-->
       <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
           <property name="dataSource" ref="dataSource"/>
           <!--绑定MyBatis配置文件-->
           <property name="configLocation" value="classpath:mybatis-config.xml"/>
       </bean>
   
       <!--配置Dao接口扫描包，动态的实现了Dao接口可以注入到Spring容器中！-->
       <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
           <!--注入SqlSessionFactory-->
           <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
           <!--要扫描的Dao包-->
           <property name="basePackage" value="dao"/>
       </bean>
   </beans>
   ```

2. 整合service层  spring-service.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:aop="http://www.springframework.org/schema/aop"
          xmlns:context="http://www.springframework.org/schema/context"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
                           https://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           https://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/aop
                           https://www.springframework.org/schema/aop/spring-aop.xsd">
   
       <!--1. 扫描service下面的包-->
       <context:component-scan base-package="service"/>
   
       <!--2. 将所有的业务类，注入到Spring，可以通过配置或者注解实现-->
       <!--也可以通过注解来实现  @Service  @Autowired-->
       <bean id="BookServiceImpl" class="service.BookServiceImpl">
           <property name="bookMapper" ref="bookMapper"/>
       </bean>
   
       <!--3. 声明式事务配置-->
       <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
           <!--注入数据源-->
           <property name="dataSource" ref="dataSource"/>
       </bean>
   
       <!--4. AOP事务支持-->
   </beans>
   ```

3. 整合到 applicationContext.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:aop="http://www.springframework.org/schema/aop"
          xmlns:context="http://www.springframework.org/schema/context"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
                           https://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           https://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/aop
                           https://www.springframework.org/schema/aop/spring-aop.xsd">
   
       <import resource="classpath:spring-dao.xml"/>
       <import resource="classpath:spring-service.xml"/>
   
   </beans>
   ```

#### SpringMVC层编写

1. add Web Support

2. 在 web.xml 配置 DispatcherServlet 和乱码过滤和Session

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
            version="4.0">
   
       <!--DispatcherServlet-->
       <servlet>
           <servlet-name>springmvc</servlet-name>
           <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
           <init-param>
               <param-name>contextConfigLocation</param-name>
               <param-value>classpath:applicationContext.xml</param-value>  // 这里注意绑定Spring配置文件，保证所有bean被注册
           </init-param>
           <load-on-startup>1</load-on-startup>
       </servlet>
       <servlet-mapping>
           <servlet-name>springmvc</servlet-name>
           <url-pattern>/</url-pattern>
       </servlet-mapping>
   
       <!--乱码过滤-->
       <filter>
           <filter-name>encodingFilter</filter-name>
           <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
           <init-param>
               <param-name>encoding</param-name>
               <param-value>utf-8</param-value>
           </init-param>
       </filter>
       <filter-mapping>
           <filter-name>encodingFilter</filter-name>
           <url-pattern>/*</url-pattern>
       </filter-mapping>
   
       <!--Session-->
       <session-config>
           <session-timeout>15</session-timeout>
       </session-config>
   </web-app>
   ```

3. 编写SpringMVC配置文件  spring-mvc.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:mvc="http://www.springframework.org/schema/mvc"
          xmlns:context="http://www.springframework.org/schema/context"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
                           https://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           https://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/mvc
                           https://www.springframework.org/schema/mvc/spring-mvc.xsd">
   
       <!--1. 注解驱动-->
       <mvc:annotation-driven/>
       <!--2. 静态资源过滤-->
       <mvc:default-servlet-handler/>
       <!--3. 扫描包：controller-->
       <context:component-scan base-package="controller"/>
       <!--4. 视图解析器-->
       <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
           <property name="prefix" value="/WEB-INF/jsp"/>
           <property name="suffix" value=".jsp"/>
       </bean>
   </beans>
   ```

#### 查询书籍功能

1. 编写 BookController

   ```java
   package controller;
   
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.beans.factory.annotation.Qualifier;
   import org.springframework.stereotype.Controller;
   import org.springframework.ui.Model;
   import org.springframework.web.bind.annotation.RequestMapping;
   import pojo.Books;
   import service.BookService;
   
   import java.util.List;
   
   @Controller
   @RequestMapping("/book")
   public class BookController {
   
       // controller层 调 service层
       @Autowired
       @Qualifier("BookServiceImpl")
       private BookService bookService;
   
       // 查询全部的书籍，并返回到一个书籍展示页面
       @RequestMapping("/allBook")
       public String list(Model model) {
   
           List<Books> books = bookService.queryAllBook();
   
           model.addAttribute("books", books);
           return "allBook";
       }
   }
   ```

2. 编写书籍展示页面  allBook.jsp

   ```jsp
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <html>
   <head>
       <title>书籍展示</title>
   
       <%--BootStrap美化界面--%>
       <script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
       <link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
       <script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
   </head>
   <body>
   
   <div class="container">
   
       <div class="row clearfix">
           <div class="col-md-12 column">
               <div class="page-header">
                   <h1>
                       <small>书籍列表  --  显示所有书籍</small>
                   </h1>
               </div>
           </div>
       </div>
   
       <div class="row clearfix">
           <div class="col-md-12 column">
               <table class="table table-hover table-striped">
                   <thead>
                       <th>书籍编号</th>
                       <th>书籍名称</th>
                       <th>书籍数量</th>
                       <th>书籍详情</th>
                   </thead>
                   <%--书籍从数据库中查询出来，从这个List中遍历出来：foreach--%>
                   <tbody>
                       <%--JSTL表达式--%>
                       <c:forEach var="book" items="${books}">
                           <tr>
                               <td>${book.bookID}</td>
                               <td>${book.bookName}</td>
                               <td>${book.bookCounts}</td>
                               <td>${book.detail}</td>
                           </tr>
                       </c:forEach>
                   </tbody>
               </table>
           </div>
       </div>
   
   </div>
   
   </body>
   </html>
   ```

3. 修改首页  index.jsp

   ```jsp
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <html>
   <head>
       <title>首页</title>
   
       <style>
           a {
               text-decoration: none;
               color: black;
               font-size: 18px;
           }
           h3 {
               width: 180px;
               height: 30px;
               margin: 100px auto;
               text-align: center;
               line-height: 38px;
               background: deepskyblue;
               border-radius: 5px;
           }
       </style>
   </head>
   <body>
   
   <h3>
     <a href="${pageContext.request.contextPath}/book/allBook">进入书籍页面</a>
   </h3>
   
   </body>
   </html>
   ```

   

##### 遇到问题：BookService bean不存在

排错步骤：

1. 查看bean是否注入成功  ok
2. Junit单元测试，看代码是否能够查询出结果  ok
3. 问题，一定不在底层，是Spring出了问题
4. SpringMVC整合的h时候，没有调用到service层的bean：
   - applicationContext.xml 没有注入 bean
   - web.xml 中，也绑定过配置文件！**发现问题，配置的是spring-mvc.xml，这里没有service层的bean**

解决方法：

在 web.xml 中 DispatcherServlet 需要绑定整合后的 Spring 配置文件，以保证所有 bean 都被注册。

```xml
    <!--DispatcherServlet-->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:applicationContext.xml</param-value>  // HERE！！！！！
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
```

#### 添加书籍功能

1. 修稿首页，增加添加书籍入口 index.jsp

2. 编写添加书籍页面  addBook.jsp

   ```jsp
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <html>
   <head>
       <title>添加书籍</title>
   
       <%--BootStrap美化界面--%>
       <script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
       <link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
       <script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
   </head>
   <body>
   
   <div class="container">
   
       <div class="row clearfix">
           <div class="col-md-12 column">
               <div class="page-header">
                   <h1>
                       <small>新增书籍</small>
                   </h1>
               </div>
           </div>
       </div>
   
       <form action="${pageContext.request.contextPath}/book/addBook" method="post">
           <div class="form-group">
               <label>书籍名称：</label>
               <input type="text" class="form-control" name="bookName" required>
           </div>
           <div class="form-group">
               <label>书籍数量：</label>
               <input type="text" class="form-control" name="bookCounts" required>
           </div>
           <div class="form-group">
               <label>书籍描述：</label>
               <input type="text" class="form-control" name="detail" required>
           </div>
   
           <div class="form-group">
               <input type="submit" class="form-control" value="添加">
           </div>
       </form>
   </div>
   </body>
   </html>
   ```

3. 编写  BookController

   ```java
   package controller;
   
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.beans.factory.annotation.Qualifier;
   import org.springframework.stereotype.Controller;
   import org.springframework.ui.Model;
   import org.springframework.web.bind.annotation.RequestMapping;
   import pojo.Books;
   import service.BookService;
   
   import java.util.List;
   
   @Controller
   @RequestMapping("/book")
   public class BookController {
   
       // controller层 调 service层
       @Autowired
       @Qualifier("BookServiceImpl")
       private BookService bookService;
   
       // 跳转到增加书籍页面
       @RequestMapping("/toAddBook")
       public String toAddBook() {
           return "addBook";
       }
   
       // 添加书籍的请求
       @RequestMapping("/addBook")
       public String addBook(Books books) {
           System.out.println("addBook " + books);
           bookService.addBook(books);
           return "redirect:/book/allBook";  // 重定向到 /allBook 请求
       }   
   }
   ```

#### 修改删除书籍功能

1. 修改首页，添加修改删除操作  index.jsp

2. 编写修改书籍页面  updateBook.jsp

   ```jsp
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <html>
   <head>
       <title>修改书籍</title>
   
       <%--BootStrap美化界面--%>
       <script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
       <link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
       <script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
   </head>
   <body>
   
   <div class="container">
   
       <div class="row clearfix">
           <div class="col-md-12 column">
               <div class="page-header">
                   <h1>
                       <small>修改书籍</small>
                   </h1>
               </div>
           </div>
       </div>
   
       <form action="${pageContext.request.contextPath}/book/updateBook" method="post">
           <%--出现问题：提交了修改到SQL的请求，但是修改失败，初次考虑是事务问题，配置事务后依旧失败--%>
           <%--然后查看SQL是否执行成功：SQL执行失败，id未传入--%>
           <%--前端传递隐藏域id--%>
           <input type="hidden" name="bookID" value="${books.bookID}">
           <div class="form-group">
               <label>书籍名称：</label>
               <input type="text" class="form-control" name="bookName" value="${books.bookName}" required>
           </div>
           <div class="form-group">
               <label>书籍数量：</label>
               <input type="text" class="form-control" name="bookCounts" value="${books.bookCounts}" required>
           </div>
           <div class="form-group">
               <label>书籍描述：</label>
               <input type="text" class="form-control" name="detail" value="${books.detail}" required>
           </div>
   
           <div class="form-group">
               <input type="submit" class="form-control" value="修改">
           </div>
       </form>
   </div>
   </body>
   </html>
   ```

3. 编写  BookController

   ```java
   package controller;
   
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.beans.factory.annotation.Qualifier;
   import org.springframework.stereotype.Controller;
   import org.springframework.ui.Model;
   import org.springframework.web.bind.annotation.PathVariable;
   import org.springframework.web.bind.annotation.RequestMapping;
   import pojo.Books;
   import service.BookService;
   
   import java.util.List;
   
   @Controller
   @RequestMapping("/book")
   public class BookController {
   
       // controller层 调 service层
       @Autowired
       @Qualifier("BookServiceImpl")
       private BookService bookService;
   
       // 跳转到书籍修改页面
       @RequestMapping("/toUpdateBook")
       public String toUpdateBook(int id, Model model) {
           Books books = bookService.queryBookById(id);
           model.addAttribute("books", books);
           return "updateBook";
       }
   
       // 修改书籍的请求
       @RequestMapping("/updateBook")
       public String updateBook(Books books) {
           bookService.updateBook(books);
           return "redirect:/book/allBook";  // 重定向到 /allBook 请求
       }
   
       // 删除书籍的请求
       // RestFul风格
       @RequestMapping("/deleteBook/{bookID}")
       public String deleteBook(@PathVariable("bookID") int id) {
           bookService.deleteBookById(id);
           return "redirect:/book/allBook";
       }
   }
   ```

#### 查询书籍功能

1. 编写首页，添加搜索栏   index.jsp

2. 编写Dao层查询功能

3. 编写Service查询功能

4. 编写 BookController

   ```java
   package controller;
   
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.beans.factory.annotation.Qualifier;
   import org.springframework.stereotype.Controller;
   import org.springframework.ui.Model;
   import org.springframework.web.bind.annotation.PathVariable;
   import org.springframework.web.bind.annotation.RequestMapping;
   import pojo.Books;
   import service.BookService;
   
   import java.util.ArrayList;
   import java.util.List;
   
   @Controller
   @RequestMapping("/book")
   public class BookController {
   
       // controller层 调 service层
       @Autowired
       @Qualifier("BookServiceImpl")
       private BookService bookService;
   
       // 查询书籍
       @RequestMapping("/queryBook")
       public String queryBook(String queryBookName, Model model) {
           Books book = bookService.queryBookByName(queryBookName);
           List<Books> books = new ArrayList<>();
           books.add(book);
   
           if (book == null) {
               books = bookService.queryAllBook();
               model.addAttribute("error", "未查到");
           }
           model.addAttribute("books", books);
           return "allBook";
       }
   }
   ```

### 8. Ajax

#### 8.1 简介

Ajax（Asynchronous JavaScript and XML，异步的JavaScript和XML），Ajax是一种在无需重新加载整个网页的情况下，能够更新部分网页的技术。

Ajax不是一种新的编程语言，而是一种用于创建更好更快以及交互性更强的Web应用程序的技术。

#### 8.2 伪造Ajax

1. 新建web项目，搭建SpringMVC框架

2. 编写一个 ajax-frame.html 使用 iframe

   ```html
   <!DOCTYPE html>
   <html lang="en">
   <head>
       <meta charset="UTF-8">
       <title>iframe测试体验页面无刷新</title>
   
       <script>
           function go() {
               let url = document.getElementById("url").value;
               document.getElementById("iframe1").src = url;
           }
       </script>
   </head>
   <body>
   
   <div>
       <p>请输入地址：</p>
       <p>
           <input type="text" id="url" value="https://www.baidu.com">
           <input type="button" value="提交" onclick="go()">
       </p>
   
   </div>
   
   <div>
       <iframe id="iframe1" style="width: 100%; height: 500px"></iframe>
   </div>
   
   </body>
   </html>
   ```

3. 测试

#### 8.3 jQuery.ajax

- 使用 jQuery 提供的 Ajax，核心是 XMLHttpRequesst 对象（XHR），XHR为向服务器发送请求和解析服务器响应提供了接口，能够以异步的方式从服务器获取新数据。

- 通过 jQuery Ajax方法，能够使用 HTTP Get 和 HTTP Post 从远程服务器上请求文本、HTML、XML或JSON。同时，能够将这些外部数据直接载入网页的被选元素中。

- jQuery 不是生产者，是大自然的搬运工。本质是 XMLHttpRequest，对它进行了封装，以便调用。

  <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200917121913996.png" alt="image-20200917121913996" style="zoom:50%;" />

1. 新建web项目，配置SpringMVC

2. 编写一个 AjaxController

   ```java
   package controller;
   import org.springframework.web.bind.annotation.RequestMapping;
   import org.springframework.web.bind.annotation.RestController;
   
   @RestController
   public class AjaxController {
   
       @RequestMapping("/a1")
       public String a1(String name) {
           if ("sugar".equals(name)) {
               return "true";
           } else {
               return  "false";
           }
       }
   }
   ```

3. 编写ajax测试页面（失去焦点即请求）  index.jsp

   ```jsp
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <html>
   <head>
     <title>$Title$</title>
     <script src="${pageContext.request.contextPath}/static/js/jquery-3.5.1.min.js"></script>
     <script>
       function a() {
         $.post({
           url: "${pageContext.request.contextPath}/a1",
           data: {"name": $("#username").val()},
           success: function (data) {
             alert(data);
           },
           error: function (){
   
           }
         })
       }
     </script>
   </head>
   <body>
   <%--失去焦点的时候，发起一个请求（携带信息）到后台--%>
   用户名：<input type="text" id="username" onblur="a()">
   </body>
   </html>
   ```

#### 8.4 Ajax异步加载数据

1. 编写 AjaxController

   ```java
   package controller;
   
   import org.springframework.web.bind.annotation.RequestMapping;
   import org.springframework.web.bind.annotation.RestController;
   import pojo.User;
   
   import java.util.ArrayList;
   import java.util.List;
   
   @RestController
   public class AjaxController {
   
       @RequestMapping("/a2")
       public List<User> a2() {
           List<User> users = new ArrayList<User>();
           // 添加数据
           users.add(new User("Sugar", 13, "男"));
           users.add(new User("Sugar2", 1, "男"));
           users.add(new User("Sugar3", 2, "男"));
   
           return users;
       }
   }
   ```

2. 编写 ajax 显示数据页面 test2.jsp

   ```jsp
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <html>
   <head>
       <title>Title</title>
       <script src="${pageContext.request.contextPath}/static/js/jquery-3.5.1.min.js"></script>
       <script>
           $(function () {
               $("#btn").click(function () {
                   $.post("${pageContext.request.contextPath}/a2", function (data) {
                       console.log(data);
                       let html = "";
   
                       for (let i = 0; i < data.length; i++) {
                           html += "<tr>" +
                               "<td>" + data[i].name + "</td>" +
                               "<td>" + data[i].age + "</td>" +
                               "<td>" + data[i].sex + "</td>" +
                               "</tr>"
                       }
   
                       $("#content").html(html);
                   })
               })
           });
   
       </script>
   </head>
   <body>
   
   <input type="button" value="加载数据" id="btn">
   <table>
       <tr>
           <td>姓名</td>
           <td>年龄</td>
           <td>性别</td>
       </tr>
       <tbody id="content">
           <%--数据：后台--%>
       </tbody>
   </table>
   
   </body>
   </html>
   ```

#### 8.5 Ajax验证用户名

1. 编写账号登录页面 login.jsp

   ```jsp
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <html>
   <head>
       <title>Title</title>
       <script src="${pageContext.request.contextPath}/static/js/jquery-3.5.1.min.js"></script>
   
       <script>
           function a1() {
               $.post({
                   url: "${pageContext.request.contextPath}/a3",
                   data: {"name": $("#name").val()},
                   success: function (data) {
                       if (data.toString() === "ok") {
                           $("#userInfo").css("color", "green");
                       } else {
                           $("#userInfo").css("color", "red");
                       }
                       $("#userInfo").html(data);
                   }
               })
           }
   
           function  a2() {
               $.post({
                   url: "${pageContext.request.contextPath}/a3",
                   data: {"pwd": $("#pwd").val()},
                   success: function (data) {
                       if (data.toString() === "ok") {
                           $("#pwdInfo").css("color", "green");
                       } else {
                           $("#pwdInfo").css("color", "red");
                       }
                       $("#pwdInfo").html(data);
                   }
               })
           }
       </script>
   </head>
   <body>
   
   <p>
       用户名：<input type="text" id="name" onblur="a1()">
       <span id="userInfo"></span>
   </p>
   
   <p>
       密码：<input type="text" id="pwd" onblur="a2()">
       <span id="pwdInfo"></span>
   </p>
   
   </body>
   </html>
   ```

2. 编写 AjaxController

   ```java
   @RestController
   public class AjaxController {
   
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
   ```

### 9. 拦截器

SpringMVC的处理器拦截器类似于Servlet开发中的过滤器 Filter，用于对处理器进行预处理和后处理。可以定义一些拦截器来实现特定的功能。

**过滤器与拦截器的区别：**拦截器是 AOP思想的具体应用。

**过滤器：**

- Servlet规范中的一部分，任何 java web 工程都可以使用。
- 在 url-pattern 中配置了 /* 之后，可以对所有要访问的资源进行拦截。

**拦截器：**

- 拦截器是 SpringMVC 框架自己的，只有使用了 SpringMVC 框架的工程才能使用。
- 拦截器只会拦截访问的控制器方法，如果访问的是 jsp/html/css/images/js 静态资源是不会进行拦截的。

### 9.1 自定义拦截器

自定义拦截器，必须实现 **HandlerInterceptor** 接口。

1. 新建web项目，添加web支持。

2. 配置 web.xml 和 SpringMVC。

3. 编写一个拦截器  MyInterceptor.java

   ```java
   package config;
   
   import org.springframework.web.servlet.HandlerInterceptor;
   import org.springframework.web.servlet.ModelAndView;
   
   import javax.servlet.http.HttpServletRequest;
   import javax.servlet.http.HttpServletResponse;
   
   public class MyInterceptor implements HandlerInterceptor {
   
       // return true；执行下一个拦截器，放行
       // return false；不执行下一个拦截器
       public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
   
           System.out.println("============ 处理前 ============");
   
           return true;
       }
   
       // 拦截日志
       public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
           System.out.println("============ 处理后 ============");
       }
   
       public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
           System.out.println("============ 清理后 ============");
       }
   }
   ```

### 9.2 登录判断验证

1. 编写 LoginInterceptor.java

   ```java
   public class LoginInterceptor implements HandlerInterceptor {
   
       public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
   
           HttpSession session = request.getSession();
           // 放行判断：已登录的情况
           if (session.getAttribute("userLoginInfo") != null) {
               return true;
           }
   
           // 直接访问登录请求也会放行
           if (request.getRequestURL().toString().contains("login")) {
               return true;
           }
   
           // 直接访问登录页面也会放行
           if (request.getRequestURL().toString().contains("goLogin")) {
               return true;
           }
   
           // 没有登录的情况
           request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
           return false;
       }
   }
   ```

2. 在 web.xml 注册拦截器

   ```xml
       <!--拦截器配置-->
       <mvc:interceptors>
           <mvc:interceptor>
               <!--/** 包括这个请求下面的所有请求-->
               <mvc:mapping path="/**"/>
               <bean class="config.MyInterceptor"/>
           </mvc:interceptor>
           <mvc:interceptor>
               <mvc:mapping path="/**"/>
               <bean class="config.LoginInterceptor"/>
           </mvc:interceptor>
       </mvc:interceptors>
   ```

3. 编写登录页  login.jsp

   ```jsp
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <html>
   <head>
       <title>Title</title>
   </head>
   <body>
   
   <%--在WEB-INF和下的所有页面或资源，只能通过controller或者servlet进行访问--%>
   <h1>登录页面</h1>
   
   <form action="${pageContext.request.contextPath}/user/login" method="post">
       用户名：<input type="text" name="username">
       密码：<input type="text" name="password">
       <input type="submit" value="提交">
   </form>
   
   </body>
   </html>
   ```

4. 编写 LoginController

   ```java
   @Controller
   @RequestMapping("/user")
   public class LoginController {
   
       @RequestMapping("/main")
       public String main() {
           return "main";
       }
   
       @RequestMapping("/goLogin")
       public String goLogin() {
           return "login";
       }
   
       @RequestMapping("/login")
       public String login(HttpSession session, String username, String password, Model model) {
           // 把用户信息存在session中
           session.setAttribute("userLoginInfo", username);
   
           return "main";
       }
   
       @RequestMapping("/logout")
       public String logout(HttpSession session) {
   
           session.removeAttribute("userLoginInfo");
           return "login";
       }
   }
   ```

### 10. 文件上传和下载

#### 10.1 文件上传

SpringMVC上下文中默认没有装配 MultipartResolver，**因此默认情况下不能处理文件上传工作。**

如果想要使用 Spring 的文件上传功能，需要在上下文中配置 **MultipartResolver**。

**前端表单要求**：为了能上传文件，必须将表单的 method 设置为 **POST**，并将 **enctype** 设置为 **multipart/form-data**。只有这种情况下，浏览器才会把用户选择的文件以二进制数据发送给服务器。

**对表单中 enctype 属性的详细说明：**

- **application/x-www=form-urlencoded**：默认方式，只处理表单域中的 value 属性值，采用这种编码方式的表单会将表单域中的值处理成 URL 编码方式。
- **multipart/form-data**：这种编码方式会以二进制流的方式来处理表单数据，这种编码方式会把文件域指定文件的内容也封装到请求参数中，不会对字符编码。
- **text/plain**：除了把空格转换为 “+”号外，其他字符都不做编码处理，这种方式适用直接通过表单发送邮件。

```html
<form action="" enctype="multipart/form-data" method="post">
  	<input type="file" name="file"/>
  	<input type="submit">
</form>
```

一旦设置了 enctype为 multipart/form-data，浏览器即会采用二进制流的方式来处理表单数据，而对于文件上传的处理则涉及在服务器端解析原始HTTP响应。

在2003年，Apache基金会发布开源的 **Commons FileUpload 组件**，称为Servlet/JSP 程序员上传文件的最佳选择。

- Servlet3.0规范已经提供方法来处理文件上传，但这种上传需要在Servlet中完成。
- SpringMVC提供了更简单的封装。
- SpringMVC 为文件撒很难过船提供了直接的支持，这种支持是用即插即用的 **MultipartResolver** 实现的。
- SpringMVC 使用 Apache Common FileUpload 技术实现了一个 MultipartResolver 实现类：**CommonMultipartResolver**。因此，**SpringMVC的文件上传还是需要依赖 Apache Common FileUpload的组件。**



流程：

1. 导入文件上传的jar包， commmons-fileupload

   ```xml
           <dependency>
               <groupId>commons-fileupload</groupId>
               <artifactId>commons-fileupload</artifactId>
               <version>1.3.3</version>
           </dependency>
           <dependency>
               <groupId>javax.servlet</groupId>
               <artifactId>javax.servlet-api</artifactId>
               <version>4.0.1</version>
           </dependency>
   ```

2. 配置bean：multipartResolver

   **注意：这个bean的id必须为：multipartResolver，否则上传文件会报400的错误！**

   CommonsMultipartFile 的常用方法：

   - String **getOriginalFilename()**：获取上传文件的原名
   - InputStream **getInputStream()**：获取文件流
   - void **transferTo(File dest)**：将上传文件保存到一个目录文件中

   ```xml
       <!--文件上传配置-->
       <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
           <!--请求的编码格式，必须和JSP的pageEncoding属性一致，以便正确读取表单的内容，默认为ISO-8859-1-->
           <property name="defaultEncoding" value="utf-8"/>
           <!--上传文件大小上限，单位为字节(10485760=10M)-->
           <property name="maxUploadSize" value="10485760"/>
           <property name="maxInMemorySize" value="40960"/>
       </bean>
   ```

3. 编写前端页面

   ```jsp
   <form action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data" method="post">
       <input type="file" name="file"/>
       <input type="submit" value="upload">
   </form>
   ```

4. 编写 FileController

##### 文件上传方式一（手写缓冲区）：

```java
    // @RequestMapping("/upload") 将name=file控件得到的文件封装成CommonsMultipartFile 对象
    // 批量上传CommonsMultipartFile则为数组即可
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {

        // 获取文件名：file.getOriginalFilename();
        String filename = file.getOriginalFilename();

        // 如果文件夹为空，直接回到首页！
        if ("".equals(filename)) {
            return "redirect:/index.jsp";
        }
        System.out.println("上传文件名为：" + filename);

        // 上传路径保存设置
        String path = request.getServletContext().getRealPath("/upload");
        System.out.println(path);

        // 如果路径不存在，则创建一个
        File filepath = new File(path);
        if (!filepath.exists()) {
            filepath.mkdir();
        }
        System.out.println("上传文件保存地址：" + filepath);

        InputStream is = file.getInputStream();  // 文件输入流
        OutputStream os = new FileOutputStream(new File(filepath, filename));  // 文件输出流

        // 读取写出
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
            os.flush();
        }

        os.close();
        is.close();

        return "redirect:/index.jsp";
    }
```

##### 文件上传方式二（file.transferTo）（推荐）：

```java
    // 采用 file.transferTo 来保存上传的文件 （推荐）
    @RequestMapping("/upload2")
    public String upload2(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {

        // 上传路径保存设置
        String path = request.getServletContext().getRealPath("/upload");
        File filepath = new File(path);


        // 上传文件地址
        System.out.println("上传文件保存地址：" + filepath);

        // 通过 CommonsMultipartFile 的方法直接写文件（注意）
        file.transferTo(new File(filepath + "/" + file.getOriginalFilename()));

        return "redirect:/index.jsp";
    }
```

#### 10.2 文件下载

图片下载可以采用这种方式，也可以直接在前端用 **a** 链接实现。

```java
    // 文件下载
    @RequestMapping("/download")
    public String download(HttpServletResponse response, HttpServletRequest request) throws IOException {
        // 要下载的图片地址
        String path = request.getServletContext().getRealPath("/upload");
        String filename = "Java.pdf";

         // 1. 设置 response 响应头
        response.reset();  // 设置页面不缓存，清空buffer
        response.setCharacterEncoding("UTF-8");  // 字符编码
        response.setContentType("multipart/form-data");  // 二进制传输数据

        // 设置响应头
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename, "UTF-8"));

        File file = new File(path, filename);
        // 2. 读取文件 - 输入流
        FileInputStream input = new FileInputStream(file);
        // 3. 写出文件 - 输出流
        ServletOutputStream out = response.getOutputStream();

        byte[] buffer = new byte[1024];
        int len = 0;
        // 4. 执行写出操作
        while ((len=input.read(buffer)) != -1) {
            out.write(buffer, 0, len);
            out.flush();
        }

        out.close();
        input.close();

        return null;
    }
```

### 11. SSM复习重点

1. 第一个MyBatis程序
2. ResultMap结果集映射（一对多，多对一）
3. DI
4. 静态代理模式
5. JavaConfig
6. Spring整合Mybatis：事务
7. Springmvc执行流程
8. RestFul
9. 整合SSM项目