# SpringMVC

重点：**SpringMVC的执行流程**

重点实践：**SSM框架整合**

MVC：模型（dao、service）  视图（jsp）  控制器（Servlet）

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200914151408723.png" alt="image-20200914151408723" style="zoom:30%;" />

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200914151652586.png" alt="image-20200914151652586" style="zoom:40%;" />

##### 遇到的问题

SpringMVC项目配置好以后，访问路径，报如下错误：

```
java.lang.ClassNotFoundException: org.springframework.web.servlet.DispatcherServlet
```

**解决方法**：

1. 在 Project Structure > Artifacts 中点击项目，在 Output Layout > WEB-INF 目录下，新建 lib 包，然后点击加号 > Library Files，添加依赖。