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

### 4、 HTTP

#### 4.1 什么是Http

HTTP（超文本传输协议）是一个简单的请相应协议，通常运行在TCP之上。

- 文本：HTML、字符串，...
- 超文本：图片，音乐，视频，定位......
- 端口：80

HTTPS：安全的

- 端口：443

#### 4.2 HTTP1.0 和 HTTP2.0

- http1.0
  - HTTP/1.0：客户端可以与Web服务器连接，只能获得一个Web资源，然后断开连接。

- http2.0
  - HTTP/1.1：客户端可以与Web服务器连接，只能获得多个Web资源。

#### 4.3 HTTP请求

- 客户端 -- 发请求（Request） --> 服务器

```Java
Request URL: https://www.baidu.com/   // 请求地址
Request Method: GET    // 请求方法 get post
Status Code: 200 OK    // 状态码：200
Remote Address: 220.181.38.150:443    // 访问的真实地址
Referrer Policy: no-referrer-when-downgrade   // 协议
```

```java
Accept: text/html
Accept-Encoding: gzip, deflate, br
Accept-Language: zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7  // 语言
Cache-Control: max-age=0
Connection: keep-alive
Cookie: BIDUPSID=4A39119EDCAF33827F4F43C896DBBAC4; PSTM=1567144346; BAIDUID=B138147CB03300F29CF75727F0E25750:FG=1; sugstore=1; BD_UPN=123253; H_WISE_SIDS=146722_147410_142019_145946_141744_147028_147280_146538_146307_145837_131247_144682_144741_146574_140259_147347_127969_146550_145971_146752_142420_146734_138425_144375_131423_128698_142209_107314_145290_139909_144877_146396_144966_147301_145608_144535_132921_145398_147285_146226_139914_110085; BDUSS=FicE40ZjBYYTE2dzFGNmdFR3BJRm5hNDBGQ0RNRGR1U090MHVkVDhpZU9XeXBmRVFBQUFBJCQAAAAAAAAAAAEAAABt9i0jX7eov8uG0V8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAI7OAl-OzgJfUD; BDUSS_BFESS=FicE40ZjBYYTE2dzFGNmdFR3BJRm5hNDBGQ0RNRGR1U090MHVkVDhpZU9XeXBmRVFBQUFBJCQAAAAAAAAAAAEAAABt9i0jX7eov8uG0V8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAI7OAl-OzgJfUD; BDSFRCVID=4EAOJeC62wIgGyTrOTW32urz4g2BHAJTH6aoE9BuVIMGGWuQips1EG0PoM8g0Kub7-ZGogKK0mOTHv-F_2uxOjjg8UtVJeC6EG0Ptf8g0f5; H_BDCLCKID_SF=tb4foIIbtIP3fP36q4co-CCShUFsWfbJB2Q-5-3z3hoHqtO_Kxnx-UvX5q5EtpOuHG4t_fbdJJjoDh5zhluM2x70Meck-toQ3gTxoUJRBCnJhhvG-6r5X-_ebPRiJPr9QgbqslQ7tt5W8ncFbT7l5hKpbt-q0x-jLTnhVn0M5DK0MD0xj6-KejPW5ptXatcMbC6X05rJaDvsHljOy4oTj6DdDa8eJxTHBan2bKoa-tFMJl5-X-c83MvB-fnh3b5JBG5yXCJsfnnbSxnMQft20-4beMtjBbLLMCjyXn7jWhk2Dq72ybDVQlRX5q79atTMfNTJ-qcH0KQpsIJM5-DWbT8EjHCeJT-jfRAjVIv5b-0_DJ7Pq4bohjPW3G79BtQmJJrx5Jjh-hcK8UbXhj5hKRT30-FLQTLqQg-q3R7s3R6_oDbuX5oEqqFB-fvR0x-jLN7uVn0MW-5DbtOs-tnJyUnQhtnnBnKL3H8HL4nv2JcJbM5m3x6qLTKkQN3T-PKO5bRh_CF-fCIWhCL9j5RVq4t_KmTtbbQH5CnKLnD8Kb7VbIbHLfnkbfJBDlJAKMb8QTr9-U0btt5KJCbTDx51LfI7yajK2ML80jTpal6T3M3hVJTxKTQpQT8rhPFOK5OibCufsxcTab3vOIJzXpO15CuzBN5thURB2DkO-4bCWJ5TMl5jDh3Mb6ksDMDtqtJHKbDOoDK53f; delPer=0; BD_HOME=1; COOKIE_SESSION=12922_1_7_4_12_5_0_0_4_4_26_0_13061_0_141_0_1598611472_1598587240_1598611331%7C9%2391905_17_1598586863%7C9; PSINO=2; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; BD_CK_SAM=1; H_PS_PSSID=32647_32606_1432_32537_32045_32270_32116_32691_26350_32495
DNT: 1
Host: www.baidu.com
Sec-Fetch-Dest: document
Sec-Fetch-Mode: navigate
Sec-Fetch-Site: none
Sec-Fetch-User: ?1
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.135 Safari/537.36
```

##### 1. 请求行

- 请求行中的请求方式：GET
- 请求方式：GET、POST、RESTful
  - GET：请求能够携带的参数比较少，大小有限制，会在浏览器的URL地址栏显示数据内容，不安全，但高效。
  - POST：请求能够携带的参数没有限制，大小没有限制，不会在浏览器的URL地址栏显示数据内容，安全，但不高效。

##### 2. 消息头

```java
Accept  // 告诉浏览器，它所支持的数据类型
Accept-Encoding  // 支持哪种编码格式 GBK UFT-8 GB2312 ISO8859-1
Accept-Language  // 告诉浏览器，它的语言环境
Cache-Control  // 缓存控制
Connection  // 告诉浏览器，请求完成是断开还是保持连接
Host  // 主机
```

#### 4.4 HTTP响应

- 服务器 -- 响应（Response） --> 客户端

```java
Cache-Control: private   // 缓存控制
Connection: keep-alive   // 连接方式
Content-Encoding: gzip   // 编码
Content-Type: text/html;charset=utf-8    // 类型
```

##### 1. 响应体

```java
Accept  // 告诉浏览器，它所支持的数据类型
Accept-Encoding  // 支持哪种编码格式 GBK UFT-8 GB2312 ISO8859-1
Accept-Language  // 告诉浏览器，它的语言环境
Cache-Control  // 缓存控制
Connection  // 告诉浏览器，请求完成是断开还是保持连接
Host  // 主机
Refrush  // 告诉客户端，多久刷新一次
Location  // 让网页重新定位
```

##### 2. 相应状态码

200：请求响应成功  200

3xx：请求重定向

- 重定向：重新到给的新地址去访问

4xx：请求资源不存在  404

- 资源不存在

5xx：服务器代码错误  500  502(网关错误)



**面试题：**

当在浏览器中地址栏输入地址并回车的一瞬间到页面能够展示回来，经历了什么？



### 5. Maven

理由：

1. 在JavaWeb开发中，需要使用大量的jar包，手动去导入

2. 如何能够让一个东西自动导入和配置jar包

   由此，Maven诞生。

#### 5.1 Maven项目架构管理工具

目前用Maven方便导入jar包。

**核心思想**：约定大于配置

- 有约束，不要去违反。

Maven会规定和敖你改如何取编写java代码，必须要按照这个规范来。

#### 5.2 下载配置Maven

官网：https://maven.apache.org/

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200831092354865.png" alt="image-20200831092354865" style="zoom:30%;" />

下载完成后，解压即可。

#### 5.3 配置环境变量

系统环境变量中，配置如下配置：

- M2_HOME：maven目录下的bin目录
- MAVEN_HOME：maven的目录
- 在系统的Path中配置 %MAVEN_HOME%\bin

测试Maven是否安装成功，保证配置完毕。

#### 5.4 阿里云镜像

- 镜像：Mirrors
  - 作用：加速下载
- 国内建议使用阿里云的镜像
- 放在 conf/settings.xml 的 settings 标签下的 mirrors 标签下。

```xml
<mirror>
  	<id>alimaven</id>
	  <name>aliyun maven</name>
	  <url>http://maven.aliyun.com/nexus/content/groups/public</url>
	  <mirrorOf>central</mirrorOf>
</mirror>

<mirror>
	  <id>alimaven</id>
	  <name>aliyun maven</name>
	  <url>http://maven.aliyun.com/nexus/content/groups/public</url>
	  <mirrorOf>*,!jeecg,!jeecg-snapshots</mirrorOf>
</mirror>
```

#### 5.5 本地仓库

**建立一个本地仓库**：localRepository

```xml
  <localRepository>/Library/Enviroments/apache-maven-3.6.2/repository</localRepository>
```

#### 5.6 在IDEA中使用Maven

1. 启动IDEA

2. 创建一个Maven Web项目

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200831205126300.png" alt="image-20200831205126300" style="zoom:30%;" />

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200831205208755.png" alt="image-20200831205208755" style="zoom:40%;" />

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200831205316684.png" alt="image-20200831205316684" style="zoom:40%;" />

3. 等待项目初始化完毕。

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200831205731292.png" alt="image-20200831205731292" style="zoom:40%;" />

4. 观察Maven仓库中多了的东西。

5. IDEA中的Maven设置。（每次需要手动改为本地Maven库）

   IDEA项目创建成功后，看一眼Maven的配置。

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200831210011401.png" alt="image-20200831210011401" style="zoom:40%;" />

6. 到这里，Maven在IDEA中的配置和使用就OK了。

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200831210618572.png" alt="image-20200831210618572" style="zoom:30%;" />

#### 5.7 创建一个普通的Maven项目

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200831210337821.png" alt="image-20200831210337821" style="zoom:30%;" />

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200831210518207.png" alt="image-20200831210518207" style="zoom:30%;" />

#### 5.8 标记文件夹功能

方式一：从项目目录直接设置。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200831210735688.png" alt="image-20200831210735688" style="zoom:50%;" />

方式二：从项目结构中设置。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200901212039264.png" alt="image-20200901212039264" style="zoom:30%;" />



#### 5.9 在IDAE中配置Tomcat

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200901212430740.png" alt="image-20200901212430740" style="zoom:30%;" />

配置Tomcat

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200901212730643.png" alt="image-20200901212730643" style="zoom:30%;" />

解决警告问题

**原因：访问一个网站，需要指定一个文件夹名字**

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200901213153905.png" alt="image-20200901213153905" style="zoom:40%;" />



<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200901213105861.png" alt="image-20200901213105861" style="zoom:40%;" />

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200901213837894.png" alt="image-20200901213837894" style="zoom:40%;" />

#### 5.10 pom文件

pom.xml 是 Maven 的核心配置文件。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200901215144331.png" alt="image-20200901215144331" style="zoom:40%;" />



Maven 由于约定大于配置，之后可能遇到写的配置文件，无法被导出或者生效的问题

**解决方案：**

```xml
    <!--    在build中配置resources，来防止资源导出失败的问题-->
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
```

#### 5.12 IDAE操作

生成Maven依赖结构体系图

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200902091849124.png" alt="image-20200902091849124" style="zoom:50%;" />



#### 5.13 Maven遇到的问题

1. Maven 3.6.2

   Unable to import maven projects See logs for details

   **解决方法：**降级为3.6.1

2. Tomcat闪退

   **解决方法：**没有在环境变量配置 JAVA_HOME 和 JRE_HOME。

3. IDEA中每次都要配置Maven

   **解决方法：**我 在IDEA启动时，全局配置。

4. Maven项目中Tomcat无法配置

5. Maven默认web项目中的web.xml版本问题

   **解决方法：**替换为tomcat中webapp下web.xml的头文件。

### 6. Servlet

#### 6.1 Servlet简介

- Servlet是sun公司开发动态web的一门技术。
- Sun公司在API中提供一个接口：Servlet，如果要开发一个Servlet程序，只需要完成两个小步骤：
  - 编写一个类，实现Servlet接口。
  - 把开发好的Java类部署到Web服务器中。

**把实现了 Servlet 接口的 Java 程序叫做 Servlet。**

#### 6.2 HelloServlet

**Servlet接口在Sun公司有两个默认的实现类：HttpServlet、GenericServlet**

1. 构建一个Maven项目

2. 关于Maven父子工程理解

   父项目中会有

   ```xml
   <module>
   	<module>subproject-01</module>
   </module>
   ```

   子项目中会有

   ```xml
   <parent>
   		<artifactId>parentprojects</artifactId>
     	<groupId>com.sugar</groupId>
     	<version>1.0-SNAPSHOT</version>
   </parent>
   ```

   Java子项目可以直接使用父项目的依赖。

3. Maven环境优化

   1. 修改 web.xml 为最新的
   2. 将 Maven 结构搭建完整

4. 编写Servlet程序

   1. 编写一个普通类

   2. 实现 HttpServlet 接口

      ```java
      public class HelloServlet extends HttpServlet {
      
          // 由于 Get 或者 Post 只是请求实现的不同方式，可以互相调用，业务逻辑都一样
          @Override
          protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
              // 响应的类型：html
              response.setContentType("text/html");
              // 设置响应编码
              response.setCharacterEncoding("utf-8");
              // 获取响应的输出流
              PrintWriter out = response.getWriter();
              out.println("<html>");
              out.println("<head>");
              out.println("<title>Hello World!</title>");
              out.println("</head>");
              out.println("<body>");
              out.println("<h1>哈咯</h1>");
              out.println("</body>");
              out.println("</html>");
          }
      
          @Override
          protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
              super.doPost(req, resp);
          }
      }
      ```

5. 编写 Servlet 的映射

   原因：写的是Java程序，但要通过浏览器访问，而浏览器是通过连接Web服务器的，所以需要在Web服务中注册写好的Servlet。

   在 web.xml 中注册 Servlet。

   ```xml
       <!-- web.xml是配置web的核心应用 -->
       <!-- 注册Servlet -->
       <servlet>
         <servlet-name>HelloServlet</servlet-name>
         <servlet-class>Learn_JavaWeb.servlet.HelloServlet</servlet-class>
       </servlet>
   
       <!-- 一个Servlet对应一个Mapping -->
   		<!-- localhost:8080/项目名/sugar -->
       <servlet-mapping>
         <servlet-name>HelloServlet</servlet-name>
         <!-- 请求路径 -->
         <url-pattern>/sugar</url-pattern>
       </servlet-mapping>
   ```

6. 配置 Tomcat

   注意：配置项目的发布路径

7. 启动测试

#### 6.3 Servlet原理

Servlet 是由Web服务器调用，Web服务器在收到浏览器请求之后，会：

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200903162753091.png" alt="image-20200903162753091" style="zoom:50%;" />

#### 6.4 Mapping 问题

1. 一个Servlet可以指定一个映射路径

   ```xml
       <servlet-mapping>
         <servlet-name>HelloServlet</servlet-name>
         <url-pattern>/sugar</url-pattern>
       </servlet-mapping>
   ```

2. 一个Servlet可以指定多个映射路径

   ```xml
       <servlet-mapping>
         <servlet-name>HelloServlet</servlet-name>
         <url-pattern>/sugar</url-pattern>
       </servlet-mapping>
   
       <servlet-mapping>
         <servlet-name>HelloServlet</servlet-name>
         <url-pattern>/sugar1</url-pattern>
       </servlet-mapping>
   
       <servlet-mapping>
         <servlet-name>HelloServlet</servlet-name>
         <url-pattern>/sugar2</url-pattern>
       </servlet-mapping>
   ```

3. 一个Servlet可以指定通用映射路径

   ```xml
       <servlet-mapping>
           <servlet-name>HelloServlet</servlet-name>
           <url-pattern>/sugar/*</url-pattern>
       </servlet-mapping>    
   ```

4. 默认请求路径

   ```xml
       <servlet-mapping>
           <servlet-name>HelloServlet</servlet-name>
           <url-pattern>/*</url-pattern>
       </servlet-mapping>
   ```

5. 指定一些前缀或者后缀等等....

   ```xml
       <!-- 注意点.*前面不能加绝对路径 -->
       <servlet-mapping>
           <servlet-name>HelloServlet</servlet-name>
           <url-pattern>*.sugar</url-pattern>
       </servlet-mapping>
   ```

6. 优先级问题

   **指定了固有的映射路径优先级最高，如果找不到就会走默认的处理请求。**

   ```xml
       <!-- 访问 /sugar 还是会走上面的Servlet -->
       <servlet>
           <servlet-name>error</servlet-name>
           <servlet-class>Learn_JavaWeb.servlet.ErrorServlet</servlet-class>
       </servlet>
       <servlet-mapping>
           <servlet-name>error</servlet-name>
           <url-pattern>/*</url-pattern>
       </servlet-mapping>
   ```

#### 6.5 ServletContext（Servlet通信）

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200903181435317.png" alt="image-20200903181435317" style="zoom:30%;" />

Web容器在启动的时候，它会为每个Web程序都创建一个对应的 ServletContext 对象，它代表了当前的 Web应用。

##### 1. 共享数据

在这个 Servlet 中保存的数据，可以在另一个 Servlet中拿到。

- 放置全局属性的Servlet

  ```java
  public class SetServlet extends HttpServlet {
  
      @Override
      protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  
  //        this.getInitParameter()  // 初始化参数
  //        this.getServletConfig()  // Servlet配置
  //        this.getServletContext()  // Servlet上下文
  //        this.getServletName()  // Servlet Name
  
          ServletContext context = this.getServletContext();
  
          String username = "Sugar";  // 数据
          context.setAttribute("username", username);  // 将一个数据保存在 ServletContext中，名字为：username
          
      }
  }
  ```

- 获取全局属性的Servlet

  ```java
  public class GetServlet extends HttpServlet {
  
      @Override
      protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          resp.setCharacterEncoding("utf-8");
          resp.setContentType("text/html");
  
          ServletContext context = this.getServletContext();
          String username = (String) context.getAttribute("username");
  
          resp.getWriter().println("名字：" + username);
      }
  }
  ```

- web.xml

  ```xml
      <!--  ServletContext  -->
      <servlet>
          <servlet-name>set</servlet-name>
          <servlet-class>Learn_JavaWeb.servlet.SetServlet</servlet-class>
      </servlet>
      <servlet-mapping>
          <servlet-name>set</servlet-name>
          <url-pattern>/set</url-pattern>
      </servlet-mapping>
  
      <servlet>
          <servlet-name>get</servlet-name>
          <servlet-class>Learn_JavaWeb.servlet.GetServlet</servlet-class>
      </servlet>
      <servlet-mapping>
          <servlet-name>get</servlet-name>
          <url-pattern>/get</url-pattern>
      </servlet-mapping>
  ```

-  测试结果

  先访问 set 页面，后访问 get 页面，将显示之前set的属性值。

##### 2. 获取初始化参数

- 设置初始化参数

  ```xml
      <!-- 配置一些Web应用的初始化参数 -->
      <context-param>
          <param-name>url</param-name>
          <param-value>jdbc:mysql://localhost:3306/mybatis</param-value>
      </context-param>
  ```

- 获取初始化参数

  ```java
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, 				IOException {
  
          ServletContext context = this.getServletContext();
          String url = context.getInitParameter("url");
          resp.getWriter().println(url);
      }
  ```

##### 3. 请求转发

**请求转发图解：**路径不变，页面变。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200903184625078.png" alt="image-20200903184625078" style="zoom:40%;" />

**重定向图解：**路径变，页面变。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200903184641182.png" alt="image-20200903184641182" style="zoom:40%;" />

- 代码实现

  ```java
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          ServletContext context = this.getServletContext();
  //        RequestDispatcher requestDispatcher = context.getRequestDispatcher("/demo");  // 转发的请求路径
  //        requestDispatcher.forward(req, resp);  // 调用 forward，实现请求转发
          context.getRequestDispatcher("/demo").forward(req, resp);
      }
  ```

##### 4. 读取资源文件

Properties

- 在java目录下新建properties
- 在resources目录下新建properties

发现：都被打包到了 classpath 路径。

思路：需要一个文件流

```properties
username=sugar
password=123456
```

```java
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // WEB 项目，最开始的 / 是相对位置，
        InputStream is = this.getServletContext().getResourceAsStream("/WEB-INF/classes/db.properties");

        Properties prop = new Properties();
        prop.load(is);
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        resp.getWriter().println(username + " : " + password);

    }
```

#### 6.6 HttpServletResponse

Web服务器接收到客户端的http请求，针对这个请求，分别创建一个代表请求的 HttpServletRequest 对象，一个代表响应的 HttpServletResponse 对象。

- 如果要获取客户端请求过来的参数：找 HttpServletRequest
- 如果要给客户响应一些信息：找 HttpServletResponse

##### 1. 方法简单分类

**负责向浏览器发送数据的方法**

```java
ServletOutputStream getOutputStream() throws IOException;
PrintWriter getWriter() throws IOException;
```

**负责向浏览器发送响应头的方法**

```java
void setCharacterEncoding(String var1);
void setContentLength(int var1);
void setContentLengthLong(long var1);
void setContentType(String var1);
void setDateHeader(String var1, long var2);
void addDateHeader(String var1, long var2);
void setHeader(String var1, String var2);
void addHeader(String var1, String var2);
void setIntHeader(String var1, int var2);
void addIntHeader(String var1, int var2);
```

**响应的状态码**

```java
int SC_CONTINUE = 100;
int SC_SWITCHING_PROTOCOLS = 101;
int SC_OK = 200;
int SC_CREATED = 201;
int SC_ACCEPTED = 202;
int SC_NON_AUTHORITATIVE_INFORMATION = 203;
int SC_NO_CONTENT = 204;
int SC_RESET_CONTENT = 205;
int SC_PARTIAL_CONTENT = 206;
int SC_MULTIPLE_CHOICES = 300;
int SC_MOVED_PERMANENTLY = 301;
int SC_MOVED_TEMPORARILY = 302;
int SC_FOUND = 302;
int SC_SEE_OTHER = 303;
int SC_NOT_MODIFIED = 304;
int SC_USE_PROXY = 305;
int SC_TEMPORARY_REDIRECT = 307;
int SC_BAD_REQUEST = 400;
int SC_UNAUTHORIZED = 401;
int SC_PAYMENT_REQUIRED = 402;
int SC_FORBIDDEN = 403;
int SC_NOT_FOUND = 404;
int SC_METHOD_NOT_ALLOWED = 405;
int SC_NOT_ACCEPTABLE = 406;
int SC_PROXY_AUTHENTICATION_REQUIRED = 407;
int SC_REQUEST_TIMEOUT = 408;
int SC_CONFLICT = 409;
int SC_GONE = 410;
int SC_LENGTH_REQUIRED = 411;
int SC_PRECONDITION_FAILED = 412;
int SC_REQUEST_ENTITY_TOO_LARGE = 413;
int SC_REQUEST_URI_TOO_LONG = 414;
int SC_UNSUPPORTED_MEDIA_TYPE = 415;
int SC_REQUESTED_RANGE_NOT_SATISFIABLE = 416;
int SC_EXPECTATION_FAILED = 417;
int SC_INTERNAL_SERVER_ERROR = 500;
int SC_NOT_IMPLEMENTED = 501;
int SC_BAD_GATEWAY = 502;
int SC_SERVICE_UNAVAILABLE = 503;
int SC_GATEWAY_TIMEOUT = 504;
int SC_HTTP_VERSION_NOT_SUPPORTED = 505;
```

##### 2. 下载文件

1. 向浏览器输出消息
2. 下载文件
   1. 获取下载文件的路径
   2. 确定下载的文件名
   3. 设置让浏览器支持下载的东西的格式
   4. 获取下载文件的输入流
   5. 创建缓冲区
   6. 获取OutputStream对象
   7. 将FileOutputStream流写入到buffer缓冲区
   8. 使用OutputStream将缓冲区中的数据输出到客户端

```java
public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. 获取下载文件的路径
        String realPath = "/Users/sugar/Documents/GitHub/JavaPractice/src/main/resources/image.png";
        System.out.println("下载文件的路径：" + realPath);
        // 2. 确定下载的文件名
        String fileName = realPath.substring(realPath.lastIndexOf("/") + 1);
        // 3. 设置让浏览器支持（Content-Disposition）下载的东西的格式。中文文件名让URLEncoder.encode编码，否则会乱码。
        resp.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        // 4. 获取下载文件的输入流
        FileInputStream in = new FileInputStream(realPath);
        // 5. 创建缓冲区
        int len = 0;
        byte[] buffer = new byte[1024];
        // 6. 获取OutputStream对象
        ServletOutputStream out = resp.getOutputStream();
        // 7. 将FileOutputStream流写入到buffer缓冲区
        while ((len=in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();

        // 8. 使用OutputStream将缓冲区中的数据输出到客户端
    }
}
```

##### 3. 验证码功能

验证怎么实现？

- 前端实现
- 后端实现，需要用到 Java 的图片类，生成一个图片

```java
public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 让浏览器5秒自动刷新一次
        resp.setHeader("refresh", "3");

        // 在内存中创建一个图片
        BufferedImage image = new BufferedImage(80, 20, BufferedImage.TYPE_INT_RGB);
        // 得到图片
        Graphics2D g = (Graphics2D) image.getGraphics();  // 笔
        // 设置图片的背景颜色
        g.setColor(Color.white);
        g.fillRect(0, 0, 80, 20);
        // 给图片写数据
        g.setColor(Color.blue);
        g.setFont(new Font(null, Font.BOLD, 20));
        g.drawString(makeNum(), 0, 20);

        // 告诉浏览器，这个请求用图片方式打开
        resp.setContentType("image/png");
        // 网站存在缓存，不让浏览器缓存
        resp.setDateHeader("expires", -1);
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Pragma", "no-cache");

        // 把图片写给浏览器
        ImageIO.write(image, "png", resp.getOutputStream());


    }

    // 生成七位随机数
    private String makeNum() {
        Random random = new Random();
        String num = random.nextInt(9999999) + "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 7 - num.length(); i++) {
            sb.append("0");
        }
        num = sb.toString() + num;
        return num;
    }
}
```







#### 6.7 HttpServletRequest































































































