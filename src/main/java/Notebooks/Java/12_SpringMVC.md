# SpringMVC

重点：**SpringMVC的执行流程**

重点实践：**SSM框架整合**

MVC：模型（dao、service）  视图（jsp）  控制器（Servlet）

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200914151408723.png" alt="image-20200914151408723" style="zoom:30%;" />

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200914151652586.png" alt="image-20200914151652586" style="zoom:40%;" />

**执行流程**

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

##### 遇到的问题

SpringMVC项目配置好以后，访问路径，报如下错误：

```
java.lang.ClassNotFoundException: org.springframework.web.servlet.DispatcherServlet
```

**解决方法**：

在 Project Structure > Artifacts 中点击项目，在 Output Layout > WEB-INF 目录下，新建 lib 包，然后点击加号 > Library Files，添加依赖。

### 实现方式一：配置文件实现

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



### 实现方式二：注解版