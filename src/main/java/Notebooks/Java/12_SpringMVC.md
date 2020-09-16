## SpringMVC

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

