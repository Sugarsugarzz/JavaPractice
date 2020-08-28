### 1. 基本概念

#### 1.1 前言

web开发：

- web，网页
- 静态web
  - html、css
  - 提供给所有人看的数据，始终不会发生变化。
- 动态web
  - 提供给所有人看的数据，始终会发生变化。每个人在不同时间、地点看到的信息各不相同。
  - 技术栈：Servlet/JSP、ASP、PHP

在Java中，动态web资源开发的技术统称为 JavaWeb。

#### 1.2 web应用程序

web应用程序：可以提供浏览器访问的程序。

- a.html、b.html..... 多个web资源，可以被外界访问，对外界提供服务。
- 能访问到的任何页面或资源，都存在于某一个计算机上。
- URL：统一资源定位符
- 这些统一的web资源会被放在同一个文件夹下，web应用程序  --> Tomcat：服务器
- 一个web应用由多部分组成（静态web，动态web）
  - html、css、js
  - jsp、servlet
  - java程序
  - jar包
  - 配置文件（Properties）

web应用程序编写完毕后，若想提供给外接访问，需要一个服务器来统一管理。

#### 1.3 静态web

- *.htm或html，都是静态网页的后缀，如果服务器上一直存在这些东西，可以直接进行读取。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200828103055542.png" alt="image-20200828103055542" style="zoom:30%;" />

- 静态web存在的缺点：

  - web页面无法动态更新，所有用户看到的都是同一个页面。
    - 轮播图、点击特效（伪动态）
    - JavaScript（实际开发中，用的最多）
    - VBScript

  - 无法和数据库交互（数据无法持久化，用户无法交互）

#### 1.4 动态web

页面会动态展示：“Web页面展示的效果因人而异”

 <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200828103716950.png" alt="image-20200828103716950" style="zoom:40%;" />

缺点：

	- 假如服务器的动态web资源出现了错误，需要重新编写**后台程序**，重新发布（停机维护）

优点：

- Web页面可以动态更新，所有用户看到的都不是同一个页面
- 可以与数据库交互（数据持久化）

### 

### 2. Web 服务器

#### 2.1 技术

ASP

- 微软：国内最早流行的。
- 在HTML中嵌入VB的脚本，ASP + COM。
- 在ASP开发中，基本一个页面中会嵌入几千航的业务代码，页面维护成本高。
- C#

PHP

- PHP开发速度快，功能强大，跨平台，代码简单。
- 无法承载大访问量的情况（局限性）

**JSP / Servlet**

- SUN公司主推的 B/S 架构。
- 基于 Java 语言（所有大公司，或者开源组件，都是Java写的）
- 可以承载高并发，高可用和高性能。
- 语法像ASP。 ASP --> JSP

#### 2.2 Web服务器

服务器是一种被动的操作，用来处理用户的一些请求和给用户一些响应信息。

**IIS**

微软的，ASP......Windows自带的。

**Tomcat**

Tomcat 是 **Apache** 软件基金会的一个核心项目，最新的 Servlet 和 JSP 规范总能在 Tomcat 中得到体现。

Tomcat 技术先进、性能稳定，而且免费，是目前比较流行的 **Web应用服务器**。

Tomcat 是一个免费的开放源代码的 Web应用服务器，属于轻量级应用服务器，在**中小型系统和并发访问用户不是很多的场合**下被普遍使用，是开发和调试 JSP 程序的首选。

Tomcat实际上运行 JSP页面 和 Servlet。最新版本为 **9.0**.



下载 Tomcat：

1. 安装 or 解压
2. 了解配置文件及目录结构
3. 作用

### 3. Tomcat

#### 3.1 安装Tomcat

官网：https://tomcat.apache.org/

#### 3.2 Tomcat 启动和配置

文件夹的作用：

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200828121542251.png" alt="image-20200828121542251" style="zoom:50%;" />



**启动、关闭Tomcat**

```bash
cd /Library/Tomcat9
./startup.sh
./shuwtodwn
```

访问测试：http://localhost:8080

可能遇到的问题：

1. 没有配置Java环境变量
2. 闪退问题：需要配置兼容性
3. 

### 3.3 配置

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200828122723287.png" alt="image-20200828122723287" style="zoom:30%;" />

可以配置启动的端口号，

- Tomcat的默认端口号为：8080
- mysql：3306
- http：80
- https：443

```xml
    <Connector port="8080" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" />
```

可以配置主机的名称。

- 默认的主机名为：localhost --> 127.0.0.1
- 默认网站应用的存储文件夹位置：webapps

```xml
      <Host name="www.sugar.com"  appBase="webapps"
            unpackWARs="true" autoDeploy="true">
```

配置一下环境变量（可选）

**面试题：**

请谈一谈网站是如何访问的？

1. 输入一个域名；回车。

2. 检查本机的 hosts 配置文件下有没有这个域名映射；

   1. 有：直接返回对应的ip地址。这个地址中如果有需要访问的web程序，则可以直接访问。

      ```bash
      127.0.0.1      www.sugar.com
      ```

   2. 没有：去DNS服务器（DNS存放管理全世界的域名）找，找到的话就返回，找不到就返回找不到。
      <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200828123622798.png" alt="image-20200828123622798" style="zoom:30%;" />

#### 3.4 发布一个Web网站

- 将自己写的网站，放到服务器（Tomcat）中指定的web应用的文件夹（webapps）下，就可以访问了。

**网站应该有的结构**

```java
-- webapps ： Tomcat服务器的web目录
  - ROOT
  - sugarstudy ： 网站的目录名
      - WEB-INF
          - classes ： java程序
          - lib ： web应用所依赖的jar包
          - web.xml ： 网站的配置文件
      - index.html 默认的首页
  		- static
	  			- css
  						- style.css
  				- js
  				- img
  		- ...
```





















































