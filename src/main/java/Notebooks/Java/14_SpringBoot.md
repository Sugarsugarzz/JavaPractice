# 微服务阶段

JavaSE：OOP

MySQL：持久化

html+css+js+jquery+框架：视图、框架不熟练，css不好

JavaWeb：独立开发MVC三层架构的网站（原始）

SSM：框架，简化了开发流程，配置也开始较为复杂

**war：tomcat运行**

Spring再简化：SpringBoot  **jar：内嵌tomcat**；微服务架构！

服务越来越多：SpringCloud。

未来的服务架构：**服务网格**！

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200921234401725.png" alt="image-20200921234401725" style="zoom:50%;" />

### 1. 概述

#### 1.1 SpringBoot

核心思想：**约定大于配置**

主要优点：

- 为所有 Spring开发更快入门
- **开箱即用**，提供各种默认配置来简化项目配置
- 内嵌式容器简化Web项目
- 没有冗余代码生成和XML配置的要求

#### 1.2 微服务

##### 微服务

一种架构风格，要求在开发一个应用时，这个应用必须构建成一系列小服务的组合，可以通过http的方式进行互通。

##### 单体应用架构

把所有的功能单元放在一个应用里面，然后将整个应用部署到服务器上。如果负载能力不行，将整个应用进行水平复制，进行扩展，在洪湖再负载均衡。

好处：易于开发，方便部署，扩展时只需要将war复制多份，放在多个服务器上，再做个负载均衡即可。

缺点：哪怕修改一个非常小的地方，都需要停掉整个服务，重新打包，部署这个应用war包。对于大型应用，也不可能把所有内容放在一个应用中，如何维护、分工都是问题。

##### 微服务架构

打破all in one的架构方式，将每个功能元素独立出来，将独立出来的功能元素动态组合，需要的功能元素才拿来组合，需要多一些时可以整合多个功能元素。所以微服务架构是对功能元素进行复制，而没有对整个应用进行复制。

好处：节省了调用资源，每个功能元素的服务都是一个可替换的、可独立升级的软件代码。

##### 如何构建微服务架构

- SpringBoot：构建一个个功能独立的微服务应用单元
- SpringCloud：大型分布式网络服务的调用，实现分布式
- SpringCloudDataFlow：在分布式中间，进行流式数据计算、批处理
- Spring实现了整个从刚开始构建应用到大型分布式应用全流程方案

#### 2. 第一个SpringBoot程序

官方：提供了一个快速生成的网站，IDEA提供了这种方式。

https://start.spring.io/

#### 2.1 Springboot自动装配原理

**pom.xm**l

- spring-boot-dependencies：核心依赖在该父工程中
- 我们在写或者引入一些SpringBoot依赖的时候，不需要指定版本，就因为有这些版本仓库

**启动器**

- ```xml
  <!--启动器-->
  <dependency>
  	  <groupId>org.springframework.boot</groupId>
    	<artifactId>spring-boot-starter</artifactId>
  </dependency>
  ```

- 就是SpringBoot的启动场景；

- 比如，Spring-boot-starter-web，就会帮助自动导入web环境所有的依赖！

  ```xml
  <!--web依赖-->
  <dependency>
  	  <groupId>org.springframework.boot</groupId>
    	<artifactId>spring-boot-starter-web</artifactId>
  	  <version>2.3.1.RELEASE</version>
  </dependency>
  ```

- SpringBoot会将所有的功能场景，都变成一个个的启动器

- 要使用什么功能，就只需要找到对应的启动器即可

**主程序**

```java
// @SpringBootApplication：标注这个类是一个Springboot的应用
@SpringBootApplication
public class Springboot01HelloworldApplication {
	public static void main(String[] args) {
		// 将Springboot应用启动
		SpringApplication.run(Springboot01HelloworldApplication.class, args);
	}
}
```

- 注解

  - ```java
    @SpringBootConfiguration：SpringBoot的配置
      	@Configuration：Spring配置类
      			@Component：说明这也是一个Spring的组件	
    
    @EnableAutoConfiguration：自动配置
      	@AutoConfigurationPackage：自动配置包
      			@Import({Registrar.class})：包注册
        @Import({AutoConfigurationImportSelector.class})：导入选择器
      
    // 获取所有的配置
    List<String> configurations = this.getCandidateConfigurations(annotationMetadata, attributes);
    ```

    获取候选的配置

    ```java
    protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
      List<String> configurations = SpringFactoriesLoader.loadFactoryNames(this.getSpringFactoriesLoaderFactoryClass(), this.getBeanClassLoader());
      Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories. If you are using a custom packaging, make sure that file is correct.");
      return configurations;
    }
    ```

    META-INF/spring.factories：自动配置的核心文件

    <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200922101331426.png" alt="image-20200922101331426" style="zoom:30%;" />

    所有的资源加载到配置类中

    ```xml
    Properties properties = PropertiesLoaderUtils.loadProperties(resource);
    ```

- 结论：Springboot所有自动配置都是在启动的时候扫描并加载，`spring.factories`所有的自动配置类都在这里面，但不一定生效，要判断条件是否成立，只要导入了对应的start，就有对应的启动器了，有了启动器，自动装配就会生效，就会配置成功。

1. springboot在启动的时候，从类路径下的 /META-INF/`spring.factories`获取指定的值
2. 将这些自动配置的类导入容器，自动配置就会生效，就会进行自动配置
3. 以前需要自动配置的东西，现在Springboot帮忙做了。
4. 整个JavaEE的解决方案和自动配置的东西，都在`spring-boot-autoconfigure-2.2.0.RELEASE.jar`这个包下。
5. 它会把所有需要导入的组件，以类名的方式返回，这些组件就会被添加到容器。
6. 容器中也会存在非常多的x...AutoConfiguration的文件，就是这些类给容器中导入了这个场景需要的所有组件，并自动配置。 @Configuration，JavaConfig！
7. 有了自动配置类，就免去手动导入。

#### 2.2 SpringApplication

这个类主要做了以下四件事：

1. 腿短应用的类型是普通项目还是web项目
2. 查找并加载所有可用初始化器，设置到 initializers 属性中
3. 找出所有的应用程序监听器，设置到 listeners 属性中
4. 推荐并设置 main 方法的定义类，找到运行的主类

##### 面试题：关于SpringBoot，谈谈理解

- 自动装配
- SpringApplication.run()方法（就是上面四件事）

#### 2.3 Yaml语法

官方文档：https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/htmlsingle/#boot-features-external-config

##### 配置文件

SpringBoot使用一个全局的配置文件，名称固定

- application.properties
  - 语法：key=value
- application.yaml
  - 语法：key：空格 value

**作用**：修改SpringBoot自动配置的默认值。

##### 配置文件位置

优先级自上而下

```xml
根目录:./config/
根目录:./
classpath:/config/
classpath:
```

##### YAML

是一种标记语言，也不是标记语言

对比一下 YAML 和 XML

```xml
server: 
	port:8080

<server>
	<port>8080</port>
</server>
```

##### YAML语法

基本语法

```yaml
# 对空格要求高
# 可以注入到配置类中
name: sugar
# 对象
student:
  name: sugar
  age: 3
# 行内写法
student2: {name: sugar, age: 3}
# 数组
pets:
  - cat
  - dog
pets2: [cat, dog]
```

**YAML多配置环境切换**

```yml
# 多环境测试
server:
  port: 8081
spring:
  profiles:
    active: dev
---
server:
  port: 8082
spring:
  profiles: dev
---
server:
  port: 8083
spring:
  profiles: test
```

**YAML可以直接给实体类赋值**

```yaml
person:
  name: sugar
  age: ${random.int}
  happy: false
  birth: 2020/11/11
  maps: {k1: v1, k2: v2}
  list:
    - code
    - music
  dog:
    name: ${person.hello:hello}  # 存在的走person.hello，否则为hello
    age: 3
```

```java
/*
@ConfigurationProperties作用：
将配置文件中配置的每一个属性的值，映射到这个组件中
告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定
参数 prefix="person"：将配置文件中的person下面的所有属性一一对应
只有这个组件是容器中的组件，才能使用容器的@ConfigurationProperties功能
 */
@Component  // 注册bean
@ConfigurationProperties(prefix = "person")
public class Person {
    private String name;
    private int age;
    private boolean happy;
    private Date birth;
    private Map<String, Object> maps;
    private List<Object> list;
    private Dog dog;
}
```

YAML与Properties对比

- 配置yaml和properties都可以获取到值，推荐yaml
- 如果在某个业务中，只需要获取配置文件中的某个值，可以使用一下@Value
- 如果专门编写了一个JavaBean来和配置文件进行映射，就直接使用 @ConfigurationProperties，不要犹豫！

#### 2.4 JSR303数据校验

依赖

```xml
<dependency>
  	<groupId>org.springframework.boot</groupId>
	  <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

基本规则

```java
空检查 
@Null 验证对象是否为null 
@NotNull 验证对象是否不为null, 无法查检长度为0的字符串 
@NotBlank 检查约束字符串是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格. 
@NotEmpty 检查约束元素是否为NULL或者是EMPTY.

Booelan检查 
@AssertTrue 验证 Boolean 对象是否为 true 
@AssertFalse 验证 Boolean 对象是否为 false

长度检查 
@Size(min=, max=) 验证对象（Array,Collection,Map,String）长度是否在给定的范围之内 
@Length(min=, max=) Validates that the annotated string is between min and max included.

日期检查 
@Past 验证 Date 和 Calendar 对象是否在当前时间之前，验证成立的话被注释的元素一定是一个过去的日期 
@Future 验证 Date 和 Calendar 对象是否在当前时间之后 ，验证成立的话被注释的元素一定是一个将来的日期 
@Pattern 验证 String 对象是否符合正则表达式的规则，被注释的元素符合制定的正则表达式，regexp:正则表达式 flags: 指定 Pattern.Flag 的数组，表示正则表达式的相关选项。

数值检查 
建议使用在Stirng,Integer类型，不建议使用在int类型上，因为表单值为“”时无法转换为int，但可以转换为Stirng为”“,Integer为null 
@Min 验证 Number 和 String 对象是否大等于指定的值 
@Max 验证 Number 和 String 对象是否小等于指定的值 
@DecimalMax 被标注的值必须不大于约束中指定的最大值. 这个约束的参数是一个通过BigDecimal定义的最大值的字符串表示.小数存在精度 
@DecimalMin 被标注的值必须不小于约束中指定的最小值. 这个约束的参数是一个通过BigDecimal定义的最小值的字符串表示.小数存在精度 
@Digits 验证 Number 和 String 的构成是否合法 
@Digits(integer=,fraction=) 验证字符串是否是符合指定格式的数字，interger指定整数精度，fraction指定小数精度。 
@Range(min=, max=) 被指定的元素必须在合适的范围内 
@Range(min=10000,max=50000,message=”range.bean.wage”) 
@Valid 递归的对关联对象进行校验, 如果关联对象是个集合或者数组,那么对其中的元素进行递归校验,如果是一个map,则对其中的值部分进行校验.(是否进行递归验证) 
@CreditCardNumber信用卡验证 
@Email 验证是否是邮件地址，如果为null,不进行验证，算通过验证。 
@ScriptAssert(lang= ,script=, alias=) 
@URL(protocol=,host=, port=,regexp=, flags=)
```

示例

```java
@Validated  // 数据校验
public class Person {
    @Email(message = "邮箱格式错误")
    private String name;
}
```

#### 2.5 自动装配原理再理解

YAML中能配置的东西，都与 **spring.factories** 相联系。

在配置文件中能够配置的东西，都存在一个固有的规律：

xxxAutoConfiguration：默认值   --->  xxxProperties  --->  与配置文件绑定，可以使用自定义的配置了！

**自动装配原理的精髓**：

1. SpringBoot启动会加载大量的自动配置类

2. 看需要的功能有没有在SpringBoot默认写好的自动配置类当中

3. 再看这个自动配置类中到底配置了哪些组件（只要要用的组件存在其中，就不需要再手动配置了）

4. 给容器中自动配置类添加组件时，会从properties类中获取某些属性，我们只需要在配置文件中指定这些属性的值即可

   xxxAutoConfiguration：自动配置类；给容器中添加组件

   xxxProperties：封装配置文件中相关属性

**@Conditional**注解，判断指定的条件成立，才给容器中添加组件，配置类里面的所有内容才会生效。

**总结：根据当前不同的条件判断，决定这个配置类是否生效。**

```yml
在YAML中配置
debug: true
会打印日志，查看哪些组件生效，哪些没有生效。
```

### 3. SpringBoot Web开发

SpringBoot配置了什么？能否修改？能修改什么？能不能扩展？

- xxxAutoConfiguration：向容器中自动配置组件
- xxxProperties：自动配置类，装配配置文件中自定义的一些内容

要解决的问题：

- 导入静态资源
- 首页
- jsp，模板引擎 Thymeleaf（不要盲目前后端分离，一般大型项目需要分离，小项目看情况）
- 装配扩展SpringMVC
- 增删改查
- 拦截器
- 国家化

#### 3.1 静态资源

```java
public void addResourceHandlers(ResourceHandlerRegistry registry) {
  // 如果在yaml配置了资源路径，则失效
  if (!this.resourceProperties.isAddMappings()) {
    logger.debug("Default resource handling disabled");
  } else {
    Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
    CacheControl cacheControl = this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl();
    // webjars 引入
    if (!registry.hasMappingForPattern("/webjars/**")) {
      this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{"/webjars/**"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/webjars/"}).setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
    }
		// 从默认路径，staticLocation(CLASSPATH_RESOURCE_LOCATIONS) 定义的所有路径中引入
    String staticPathPattern = this.mvcProperties.getStaticPathPattern();
    if (!registry.hasMappingForPattern(staticPathPattern)) {
      this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{staticPathPattern}).addResourceLocations(WebMvcAutoConfiguration.getResourceLocations(this.resourceProperties.getStaticLocations())).setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
    }
  }
}
```

**什么是 webjars**

​	以Maven方式引入静态资源，比如 JQuery。（不推荐）

**ResourceProperties 定义的 CLASSPATH_RESOURCE_LOCATIONS 包括：**

优先级自上而下

```xml
classpath:/META-INF/resources/   # 也就是webjars方式
classpath:/resources/
classpath:/static/
classpath:/public/
```

总结：

1. 在SpringBoot中，可以使用一下方式处理静态资源
   - webjars  访问：localhost:8080/webjars/
   - public、static、resources、/**    访问：localhost:8080/

2. 优先级：resources > static > public

#### 3.2 首页定制

```java
@Bean
public WelcomePageHandlerMapping welcomePageHandlerMapping(ApplicationContext applicationContext, FormattingConversionService mvcConversionService, ResourceUrlProvider mvcResourceUrlProvider) {
  WelcomePageHandlerMapping welcomePageHandlerMapping = new WelcomePageHandlerMapping(new TemplateAvailabilityProviders(applicationContext), applicationContext, this.getWelcomePage(), this.mvcProperties.getStaticPathPattern());
  welcomePageHandlerMapping.setInterceptors(this.getInterceptors(mvcConversionService, mvcResourceUrlProvider));
  welcomePageHandlerMapping.setCorsConfigurations(this.getCorsConfigurations());
  return welcomePageHandlerMapping;
}

private Optional<Resource> getWelcomePage() {
  String[] locations = WebMvcAutoConfiguration.getResourceLocations(this.resourceProperties.getStaticLocations());
  return Arrays.stream(locations).map(this::getIndexHtml).filter(this::isReadable).findFirst();
}

private Resource getIndexHtml(String location) {
  return this.resourceLoader.getResource(location + "index.html");
}
```

将 **index.html** 放在 public/static/resources 下，默认即为首页。

一般来说，是放在 templates 中，不能直接访问，由 Controller 控制其访问。

#### 3.3 thymeleaf 模板引擎

JSP好处：当查出一些数据转到到JSP页面之后，可以轻松实现数据的显示及交互等，能够写Java代码。但在SpringBoot项目中，首先是以jar方式，不是jar，其次用的嵌入式Tomcat，所以**默认不支持JSP**。

直接用纯静态页面的方式，开发会有很大的麻烦，SpringBoot推荐使用模板引擎。

模板引擎有：JSP、freemarker、**thymeleaf**（SpringBoot推荐）

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200924121856808.png" alt="image-20200924121856808" style="zoom:30%;" />

依赖：

```xml
<dependency>
	  <groupId>org.thymeleaf</groupId>
	  <artifactId>thymeleaf-spring5</artifactId>
</dependency>
<dependency>
	  <groupId>org.thymeleaf.extras</groupId>
	  <artifactId>thymeleaf-extras-java8time</artifactId>
</dependency>
```

**结论**：只要需要使用thymeleaf，只需要导入对应的依赖即可！将 **html** 放在 **templates** 目录下即可。

##### thymeleaf语法

见官网

```java
@RequestMapping("/test")
public String test(Model model) {
  model.addAttribute("msg", "<h1>hello 123</h1>");
  model.addAttribute("users", Arrays.asList("sugar", "java"));
  return "test";
}
```

```html
<!--所有的 HTML 元素都可以被 thymeleaf 接管  语法：th:元素名-->

<!--不转义-->
<div th:text="${msg}"></div>
<!--转义-->
<div th:utext="${msg}"></div>

<!--遍历数组-->
<!--<h3 th:each="user:${users}" th:text="${user}"></h3>-->
<h3 th:each="user:${users}">[[${user}]]</h3>
```

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200924124614177.png" alt="image-20200924124614177" style="zoom:40%;" />

#### 3.4 MVC配置原理

SpringBoot在自动配置很多组件时，先看容器中有没有用户自己配置的（如果用户自己配置@bean），如果有就用用户配置的，如果没有就用自动配置的；如果有些组件可以存在多个，比如视图解析器，就将用户配置的和自己默认的组合起来！

```java
// 如果想 DIY 一些定制化的功能，只要写这个组件，然后将它交给SpringBoot，然后就会自动装配
// 扩展 SpringMVC
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    // ViewResolver  实现了视图解析器接口的类，就可以将其看做视图解析器
    @Bean
    public ViewResolver myViewResolver() {
        return new MyViewResolver();
    }

    // 自定义一个自己的视图解析器 MyViewResolver
    public static class MyViewResolver implements ViewResolver {
        @Override
        public View resolveViewName(String s, Locale locale) throws Exception {
            return null;
        }
    }
}
```

#### 3.5 扩展SpringMVC（重难点）

编写一个 **@Configuration** 注解类，并且类型要为 **WebMvcConfigurer**，还**不能标注** **@EnableWebMvc** 注解。

然后去写一个视图解析器，新建一个包叫 config，写一个类 MyMvcConfig

```java
// 如果要扩展SpringMVC，官方建议这样做！
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    // 视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 访问 /sugar，跳转到 test.html
        registry.addViewController("/sugar").setViewName("test");
    }
}
```

**总结**：在SpringBoot中，有非常多的 xxxConfiguration，会帮助进行扩展配置，只要看见这个东西就要注意了，

### 4. 员工管理系统（实践）

#### 4.1 准备工作

1. 准备 404.html、dashboard.html、index.html、list.html
2. 准备 员工和部门 Pojo 和 dao
3. 准备 config/MyMvcConfig

#### 4.2 首页实现

使用 thymeleaf 接管，注意用 th:href 导入静态资源即可。

#### 4.3 国际化

1. 在 resources 下新建 i18n 文件夹，存放国际化配置文件

2. 在 i18n 下建各种配置文件 ， xxx_地区_国家.properties（如 login_zh_CN.properties），并配置参数

3. 改造 html 中国际化参数，用 #{ } 表示

4. 在 html 中加入切换国际化的按钮

   ```html
   <a class="btn btn-sm" th:href="@{/index.html(l='zh_CN')}">中文</a>
   <a class="btn btn-sm" th:href="@{/index.html(l='en_US')}">English</a>
   ```

5. 自定义一个本地化解析器 LocaleResolver，如果请求携带国际化参数，则传入地区和国家参数

   ```java
   // 国际化：本地化解析器
   public class MyLocaleResolver implements LocaleResolver {
   
       // 解析请求
       @Override
       public Locale resolveLocale(HttpServletRequest request) {
           // 获取语言请求参数
           String language = request.getParameter("l");
           // 如果没有就使用默认的
           Locale locale = Locale.getDefault();
           // 如果请求的参数携带了国际化的参数
           if (!StringUtils.isEmpty(language)) {
               String[] split = language.split("_");
               // 国家，地区
               locale = new Locale(split[0], split[1]);
           }
           return locale;
       }
       @Override
       public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
   
       }
   }
   ```

6. 将自定义组件，配置到Spring容器

   ```java
   // 自定义的国际化组件生效
   @Bean
   public LocaleResolver localeResolver() {
     return new MyLocaleResolver();
   }
   ```

**注意点：**

- 需要配置 i18n 文件
- 如果需要，在项目中进行按钮自动切换，需要自定义一个组件 `LocaleResolver`
- 将组件配置到Spring容器
- #{}

#### 4.4 登录注册实现

1. 在 index.html 添加一个 p，显示登录返回的信息

   ```html
   <!--如果msg为空，则不显示-->
   <p th:text="${msg}" style="color: red" th:if="${not #strings.isEmpty(msg)}"></p>
   ```

2. 添加登录控制器 LoginController，处理登录业务

   ```java
   @Controller
   public class LoginController {
   
      @RequestMapping("/user/login")
       public String login(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           Model model, HttpSession session) {
   
          // 具体业务
          if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
              session.setAttribute("loginUser", username);
              return "redirect:/main.html";
          } else {
              model.addAttribute("msg", "用户名或密码错误！");
              return "index";
          }
       }
   }
   ```

3. 添加拦截器，LoginHandlerInterceptor，限制未登录状态

   ```java
   // 拦截器
   public class LoginHandlerInterceptor implements HandlerInterceptor {
     @Override
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       // 登录成功之后，应该取得用户的Session
       Object loginUser = request.getSession().getAttribute("loginUser");
       if (loginUser == null) {
         request.setAttribute("msg", "没有权限，请先登录");
         request.getRequestDispatcher("/index.html").forward(request, response);
         return false;
       } else {
         return true;
       }
     }
   }
   ```

4. 注册拦截器

   ```java
   // 自定义拦截器
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
     registry.addInterceptor(new LoginHandlerInterceptor())
       .addPathPatterns("/**")
       .excludePathPatterns("/index.html", "/", "/user/login", "/css/*", "/js/*", "/img/*");
   }
   ```

#### 4.5 展示员工列表

1. 提取公共页面

   -  `th:fragent="sidebar"` 
   -  `th:replace="~{commons/commons::sidebar}"` 或 `th:insert="~{commons/commons::sidebar}"`
   - 如果要传递参数，可以使用 （）传参，接收判断即可。

   commons.html

   ```html
   <!--侧边栏-->
   <nav class="col-md-2 d-none d-md-block bg-light sidebar" th:fragment="sidebar">
       <div class="sidebar-sticky">
           <ul class="nav flex-column">
               <li class="nav-item">
                   <a th:class="${active=='main.html'? 'nav-link active' : 'nav-link'}" th:href="@{/main.html}">
                       首页
                   </a>
               </li>
               <li class="nav-item">
                   <a th:class="${active=='list.html'? 'nav-link active' : 'nav-link'}" th:href="@{/emps}">
                       员工管理
                   </a>
               </li>
               <li class="nav-item">
                   <a class="nav-link" href="">
                       注销
                   </a>
               </li>
           </ul>
       </div>
   </nav>
   ```

   dashboard.html

   ```html
   <div class="container-fluid">
       <div class="row">
           <div th:replace="~{common/commons::sidebar(active='main.html')}"></div>
       </div>
   </div>
   ```

2. 编写 EmployeeController，提供展示员工数据的请求

   ```java
   @RequestMapping("/emps")
   public String list(Model model) {
     Collection<Employee> employees = employeeDao.getAll();
     model.addAttribute("emps", employees);
     return "emp/list";
   }
   ```

3. 编写展示员工列表页面

   ```html
   <div class="container-fluid">
       <div class="row">
           <div th:replace="~{common/commons::sidebar(active='list.html')}"></div>
   
           <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
               <h2>员工列表</h2>
               <div class="table-responsive">
                   <table class="table table-striped table-sm">
                       <thead>
                           <tr>
                               <th>id</th>
                               <th>lastName</th>
                               <th>email</th>
                               <th>gender</th>
                               <th>department</th>
                               <th>birth</th>
                               <th>操作</th>
                           </tr>
                       </thead>
                       <tbody>
                           <tr th:each="emp: ${emps}">
                               <td th:text="${emp.getId()}"></td>
                               <td th:text="${emp.getLastName()}"></td>
                               <td th:text="${emp.getEmail()}"></td>
                               <td th:text="${emp.getGender() == 0 ? '女' : '男'}"></td>
                               <td th:text="${emp.getDepartment().getDepartmentName()}"></td>
                               <td th:text="${#dates.format(emp.getBirth(), 'yyyy-MM-dd HH:mm:ss')}"></td>
                               <td>
                                   <button class="btn btn-sm btn-primary">编辑</button>
                                   <button class="btn btn-sm btn-danger">删除</button>
                               </td>
                           </tr>
                       </tbody>
                   </table>
               </div>
           </main>
       </div>
   </div>
   ```

#### 4.6 增加员工

1. 修改员工展示页面，增加添加按钮

   ```html
   <h5><a class="btn btn-sm btn-success" th:href="@{/emp}">添加员工</a></h5>
   ```

2. 编写添加员工页面

   ```html
   <div class="container-fluid">
       <div class="row">
           <div th:replace="~{common/commons::sidebar(active='list.html')}"></div>
   
           <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
               <!--添加员工表单-->
               <form th:action="@{/emp}" method="post">
                   <div class="form-group">
                       <label>LastName</label>
                       <input name="lastName" type="text" class="form-control" placeholder="Sugar">
                   </div>
                   <div class="form-group">
                       <label>Email</label>
                       <input name="email" type="email" class="form-control" placeholder="406857586@qq.com">
                   </div>
                   <div class="form-group">
                       <label>Gender</label>
                       <div class="form-check form-check-inline">
                           <input class="form-check-input" type="radio" name="gender" value="1">
                           <label class="form-check-label">男</label>
                       </div>
                       <div class="form-check form-check-inline">
                           <input class="form-check-input" type="radio" name="gender" value="0">
                           <label class="form-check-label">女</label>
                       </div>
                   </div>
                   <div class="form-group">
                       <label>Department</label>
                       <select class="form-control" name="department.id">
                         <!--department.id！！-->
                         <!--在Controller接收的是一个 Employee，所以在需要提交的是Department其中一个属性！-->
                           <option th:each="dept: ${departments}" th:text="${dept.getDepartmentName()}" th:value="${dept.getId()}"></option>
                       </select>
                   </div>
                   <div class="form-group">
                       <label>Birth</label>
                       <input name="birth" type="text" class="form-control" placeholder="sugar">
                   </div>
                   <button type="submit" class="btn btn-primary">添加</button>
               </form>
   
           </main>
       </div>
   </div>
   ```

3. 编写 EmployeeController，处理添加员工请求

   ```java
   @GetMapping("/emp")
   public String toAddpage(Model model) {
     // 查出所有的部门信息
     Collection<Department> departments = departmentDao.getDepartments();
     model.addAttribute("departments", departments);
     return "emp/add";
   }
   
   @PostMapping("/emp")
   public String addEmp(Employee employee) {
     // 调用底层方法增加员工
     employeeDao.add(employee);
     return "redirect:/emps";
   }
   ```

**注意：**

日期问题，在 SpringBoot 配置文件中定义一下。 然后 department.id 注意一下。

```properties
# 自定义时间日期
spring.mvc.date-format=yyyy-MM-dd
```

#### 4.7 修改员工

1. 修改员工展示页面，添加编辑请求

   ```html
   <a class="btn btn-sm btn-primary" th:href="@{/emp/} + ${emp.getId()}">编辑</a>
   ```

2. 编写修改员工页面（隐藏域加一个id）

   ```html
   <!DOCTYPE html>
   <html lang="en" xmlns:th="http://www.thymeleaf.org">
   <head>
       <meta charset="UTF-8">
       <title>Title</title>
       <link th:href="@{/css/bootstrap.min.css}" rel="stylesheet"/>
   </head>
   <body>
   
   <div class="container-fluid">
       <div class="row">
           <div th:replace="~{common/commons::sidebar(active='list.html')}"></div>
   
           <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
               <form th:action="@{/updateEmp}" method="post">
                   <input type="hidden" name="id" th:value="${emp.getId()}">
                   <div class="form-group">
                       <label>LastName</label>
                       <input name="lastName" type="text" class="form-control" th:value="${emp.getLastName()}">
                   </div>
                   <div class="form-group">
                       <label>Email</label>
                       <input name="email" type="email" class="form-control" th:value="${emp.getEmail()}">
                   </div>
                   <div class="form-group">
                       <label>Gender</label>
                       <div class="form-check form-check-inline">
                           <input class="form-check-input" type="radio" name="gender" value="1" th:checked="${emp.getGender() == 1}">
                           <label class="form-check-label">男</label>
                       </div>
                       <div class="form-check form-check-inline">
                           <input class="form-check-input" type="radio" name="gender" value="0" th:checked="${emp.getGender() == 0}">
                           <label class="form-check-label">女</label>
                       </div>
                   </div>
                   <div class="form-group">
                       <label>Department</label>
                       <select class="form-control" name="department.id">
                           <!--在Controller接收的是一个 Employee，所以在需要提交的是Department其中一个属性！-->
                           <option th:selected="${dept.getId() == emp.getDepartment().getId()}" th:each="dept: ${departments}" th:text="${dept.getDepartmentName()}"
                                   th:value="${dept.getId()}"></option>
                       </select>
                   </div>
                   <div class="form-group">
                       <label>Birth</label>
                       <input name="birth" type="text" class="form-control" th:value="${#dates.format(emp.getBirth(), 'yyyy-MM-dd')}">
                   </div>
                   <button type="submit" class="btn btn-primary">修改</button>
               </form>
   
           </main>
       </div>
   </div>
   
   </body>
   </html>
   ```

3. 编写 EmployeeController，处理修改员工请求

   ```java
   package com.sugar.controller;
   
   import com.sugar.dao.DepartmentDao;
   import com.sugar.dao.EmployeeDao;
   import com.sugar.pojo.Department;
   import com.sugar.pojo.Employee;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.stereotype.Controller;
   import org.springframework.ui.Model;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.PathVariable;
   import org.springframework.web.bind.annotation.PostMapping;
   import org.springframework.web.bind.annotation.RequestMapping;
   
   import java.util.Collection;
   
   @Controller
   public class EmployeeController {
   
       @Autowired
       EmployeeDao employeeDao;
       @Autowired
       DepartmentDao departmentDao;
   
       // 去员工的修改页面
       @GetMapping("/emp/{id}")
       public String toUpdateEmp(@PathVariable Integer id, Model model) {
           // 查出原来的数据
           Employee employee = employeeDao.getEmployeeById(id);
           model.addAttribute("emp", employee);
           Collection<Department> departments = departmentDao.getDepartments();
           model.addAttribute("departments", departments);
           return "emp/update";
       }
   
       @PostMapping("/updateEmp")
       public String updateEmp(Employee employee) {
           employeeDao.add(employee);
           return "redirect:/emps";
       }
   }
   
   ```

#### 4.8 删除员工

1. 修改员工展示页面，编辑删除请求

   ```html
   <a class="btn btn-sm btn-danger" th:href="@{/delEmp/{id}(id=${emp.getId()})}">删除</a>
   ```

2. 编写 EmployeeController，处理删除员工请求

   ```java
   // 删除员工
   @GetMapping("/delEmp/{id}")
   public String deleteEmp(@PathVariable("id") Integer id) {
     employeeDao.delete(id);
     return "redirect:/emps";
   }
   ```

#### 4.9 404

只需要在 `templates` 目录下建一个 `error` 文件夹，放 `404.html` 等错误代码页面即可。

#### 4.10 注销

```java
@RequestMapping("/user/logout")
public String logout(HttpSession session) {
  session.invalidate();
  return "redirect:/index.html";
}
```

#### 4.11 网站开发思路

1. 前端
   - 模板：用现成写好的，改成自己IDE
   - 框架：组件：自己手动组合拼接！  Bootstrap、Layui、semantic-ui、elementui
     - 栅格系统
     - 导航栏
     - 侧边栏
     - 表单
2. 设计数据库（**难点**）
3. 前端能够自动运行，独立化工程
4. 数据接口如何对接：JSON，对象
5. 前后端联调测试



要求：

- 有一套自己熟悉的后台模板：X-admin
- 前端界面，至少能够通过前端框架，组合出来一个网站页面
  - index
  - about
  - blog
  - post
  - user
- 让网站独立运行

### 5. Data

SpringBoot 底层统一用 Spring Data 来处理。

#### 5.1 JDBC

默认使用 **HikariDataSource**，号称 Java WEB 当前速度最快的数据源，相比传统的C3P0、DBCP、Tomcat jdbc等连接池更加优秀。

导入依赖

```xml
		<!--JDBC-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
		</dependency>
		<!--MYSQL-->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
```

测试

```java
@SpringBootTest
class Springboot05DataApplicationTests {

	@Autowired
	DataSource dataSource;

	@Test
	void contextLoads() throws SQLException {
		// 查看默认数据源:class com.zaxxer.hikari.HikariDataSource
		System.out.println(dataSource.getClass());

		// 获取数据库连接
		Connection conn = dataSource.getConnection();
		System.out.println(conn);

		// xxxx Template： SpringBoot已经配置好的模板bean，拿来即用（例如：jdbc、redis）

		// 关闭
		conn.close();
	}

}
```

1. 配置 application.yml 的 datasource

   ```yml
   spring:
     datasource:
       username: root
       password: 123456
       url: jdbc:mysql://localhost:3306/learn_mybatis?useUnicode=true&characterEncoding=utf-8
       driver-class-name: com.mysql.cj.jdbc.Driver
   ```

2. 使用 jdbcTemplate

   ```java
   package com.sugar.controller;
   
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.jdbc.core.JdbcTemplate;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.PathVariable;
   import org.springframework.web.bind.annotation.RestController;
   
   import java.util.List;
   import java.util.Map;
   
   @RestController
   public class JDBCController {
   
       @Autowired
       JdbcTemplate jdbcTemplate;
   
       // 查询数据库的所有信息
       // 没有实体类，通过 Map 获取数据库中的东西
       @GetMapping("/userList")
       public List<Map<String, Object>> userList() {
           String sql = "select * from user";
           List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
           return maps;
       }
   
       @GetMapping("/addUser")
       public String addUser() {
           String sql = "insert into user(id, name, pwd) values (99, '小明', '123321')";
           jdbcTemplate.update(sql);
           return "add-ok";
       }
   
       @GetMapping("/updateUser/{id}")
       public String updateUser(@PathVariable("id") int id) {
           String sql = "update user set name = ?, pwd = ? where id = " + id;
           // 封装
           Object[] objects = new Object[2];
           objects[0] = "小明22";
           objects[1] = "zzzzz";
           jdbcTemplate.update(sql, objects);
           return "update-ok";
       }
   
       @GetMapping("/deleteUser/{id}")
       public String deleteUser(@PathVariable("id") int id) {
           String sql = "delete from user where id = ?";
           jdbcTemplate.update(sql, id);
           return "delete-ok";
       }
   }
   ```

#### 5.2 Druid数据源

结合了C3P0、DBCP等DB池的优点，同时加入日志监控。

导入依赖

```xml
<dependency>
  <groupId>com.alibaba</groupId>
  <artifactId>druid</artifactId>
  <version>1.1.23</version>
</dependency>
```

1. 修改 application.yml

   ```yml
   spring:
     datasource:
       username: root
       password: 123456
       url: jdbc:mysql://localhost:3306/learn_mybatis?useUnicode=true&characterEncoding=utf-8
       driver-class-name: com.mysql.cj.jdbc.Driver
       type: com.alibaba.druid.pool.DruidDataSource
   
       # SpringBoot 默认是不注入这些属性值的，需要自己绑定
       # druid 数据源专有配置
       initialSize: 5
       minIdle: 5
       maxActive: 20
       maxWait: 60000
       timeBetweenEvictionRunsMillis: 60000
       minEvictableIdleTimeMillis: 300000
       validationQuery: SELECT 'x'
       testWhileIdle: true
       testOnBorrow: false
       testOnReturn: false
       poolPreparedStatements: true
   
       # 配置监控统计拦截的filters，stat：监控统计、log4j：日志记录、wall：防御sql注入
       # 如果允许时报错 java.lang.ClassNotFoundException: org.apache.log4j.Priority
       # 则导入 log4j 依赖即可，Maven地址： https://mvcrepository.com/artifact/log4j/log4j
       filters: stat,wall,log4j
       maxPoolPreparedStatementPerConnectionSize: 20
       useGlobalDataSourceStat: true
       connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
   ```

2. 导入log4j

   ```xml
   <!--log4j-->
   <dependency>
     <groupId>log4j</groupId>
     <artifactId>log4j</artifactId>
     <version>1.2.17</version>
   </dependency>
   ```

3. 创建 DruidConfig，绑定yml中的datasource，实现自定义配置

   ```java
   package com.sugar.config;
   
   import com.alibaba.druid.pool.DruidDataSource;
   import com.alibaba.druid.support.http.StatViewServlet;
   import com.alibaba.druid.support.http.WebStatFilter;
   import org.springframework.boot.context.properties.ConfigurationProperties;
   import org.springframework.boot.web.servlet.FilterRegistrationBean;
   import org.springframework.boot.web.servlet.ServletRegistrationBean;
   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Configuration;
   
   import javax.sql.DataSource;
   import java.util.HashMap;
   import java.util.Map;
   
   @Configuration
   public class DruidConfig {
   
       @Bean
       @ConfigurationProperties(prefix = "spring.datasource")
       public DataSource druidDataSource() {
           return new DruidDataSource();
       }
   
       // 后台监控：web.xml  ServletRegistrationBean
       // 因为 SpringBoot 内置了 servlet 容器，所以没有web.xml，可以用替代方法：ServletRegistrationBean
       @Bean
       public ServletRegistrationBean statViewServlet() {
           ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
           // 后台需要有人登陆，账号密码配置
           Map<String, String> initParameters = new HashMap<>();
           // 增加配置
           initParameters.put("loginUsername", "admin");  // key是固定的
           initParameters.put("loginPassword", "123456");
           // 允许谁可以访问
           initParameters.put("allow", "");
           // 禁止谁访问
   //        initParameters.put("sugar", "192.168.1.1");
           bean.setInitParameters(initParameters);   // 设置初始化参数
           return bean;
       }
       
       // filter 过滤器
     	@Bean
       public FilterRegistrationBean webStatFilter() {
           FilterRegistrationBean bean = new FilterRegistrationBean();
           bean.setFilter(new WebStatFilter());
           
           // 可以过滤哪些请求？
           Map<String, String> initParameters = new HashMap<>();
           // 这些不进行统计
           initParameters.put("exclusions", "*.js, *.css, /druid/*");
           
           bean.setInitParameters(initParameters);
           return bean;
       }
   }
   
   ```

4. 登陆Druid后台：http://localhost:8080/druid

   可以查看SQL注入防御、慢SQL查询等。

#### 5.3 整合 Mybatis 框架

1. 导入包

   ```xml
   <dependency>
     <groupId>org.mybatis.spring.boot</groupId>
     <artifactId>mybatis-spring-boot-starter</artifactId>
     <version>2.1.1</version>
   </dependency>
   ```

2. 配置 appllication.yml，并对 mybatis 自定义配置

   ```yml
   spring:
     datasource:
       username: root
       password: 123456
       url: jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
       driver-class-name: com.mysql.cj.jdbc.Driver
   
   # 整合mybatis
   mybatis:
     type-aliases-package: com.sugar.pojo
     mapper-locations: classpath:mybatis/mapper/*.xml
   
   ```

3. 编写 pojo 类

   ```java
   package com.sugar.pojo;
   
   import lombok.AllArgsConstructor;
   import lombok.Data;
   import lombok.NoArgsConstructor;
   
   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   public class User {
       private int id;
       private String name;
       private String pwd;
   }
   ```

4. 编写 Mapper 

   ```java
   package com.sugar.mapper;
   
   import com.sugar.pojo.User;
   import org.apache.ibatis.annotations.Mapper;
   import org.springframework.stereotype.Repository;
   
   import java.util.List;
   
   @Mapper
   @Repository
   public interface UserMapper {
       List<User> queryUserList();
       User queryUserById(int id);
       int addUser(User user);
       int updateUser(User user);
       int deleteUser(int id);
   }
   ```

5. 编写 Mapper.xml 

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
   
   <mapper namespace="com.sugar.mapper.UserMapper">
       <select id="queryUserList" resultType="User">
           select * from user
       </select>
   </mapper>
   ```

6. service层调用dao层

   略

7. controller层调用service层

   ```java
   package com.sugar.controller;
   
   import com.sugar.mapper.UserMapper;
   import com.sugar.pojo.User;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.stereotype.Controller;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.RestController;
   
   import java.util.List;
   
   @RestController
   public class UserController {
   
       @Autowired
       private UserMapper userMapper;
   
       @GetMapping("/queryUserList")
       public List<User> queryUserList() {
           List<User> userList = userMapper.queryUserList();
           for (User user : userList) {
               System.out.println(user);
           }
           return userList;
       }
   }
   ```

### 6. 安全

**传统：过滤器、拦截器**

缺点：大量的原生代码，冗余

非功能性需求

在设计之初考虑

SpringSecurity 和 shiro：很像，除了类和名字不一样

认证，授权（vip1、vip2、vip3）

- 功能权限
- 访问权限
- 菜单权限

框架！

AOP思想！

#### 6.1 Spring Security

Spring Security 是针对Spring项目的安全框架，也是SpringBoot底层安全模块默认的技术选型，可以实现强大的Web安全控制。

对于安全控制，仅需要引入 `spring-boot-starter-security` 模块，进行少量的配置，即可实现强大的安全管理。

几个重要的类：

- `WebSecurityConfigurerAdapter`：自定义Security策略
- `AuthenticationManagerBuilder`：自定义认证策略
- `@EnableWebSecurity`：开启WebSecurity模式

Spring Security两个主要目标：**认证**、**授权**（访问控制）

认证（Authentication）

授权（Authorization）

参考官网：https://spring.io/projects/spring-security

根据项目版本，查找对应的帮助文档：https://docs.spring.io/spring-security/site/docs/5.2.0.RELEASE/reference/htmlsingle

注：以下测试需要在 Springboot 2.0.9 环境。

1. 搭建测试项目结构

2. 导入依赖

   ```xml
   <!--security-thymeleaf整合-->
   <dependency>
     <groupId>org.thymeleaf.extras</groupId>
     <artifactId>thymeleaf-extras-springsecurity4</artifactId>
     <version>3.0.4.RELEASE</version>
   </dependency>
   <!--spring security-->
   <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-security</artifactId>
   </dependency>
   <!--thymeleaf-->
   <dependency>
     <groupId>org.thymeleaf</groupId>
     <artifactId>thymeleaf-spring5</artifactId>
   </dependency>
   <dependency>
     <groupId>org.thymeleaf.extras</groupId>
     <artifactId>thymeleaf-extras-java8time</artifactId>
   </dependency>
   ```

3. 编写 SecurityConfig 类，继承 `WebSecurityConfigurerAdapter` 类，重写 `configure` 方法。

   - 实现用户认证和授权
   - 实现注销及权限控制
   - 记住我及首页定制

   ```java
   package com.sugar.config;
   
   import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
   import org.springframework.security.config.annotation.web.builders.HttpSecurity;
   import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
   import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
   import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
   
   // AOP
   @EnableWebSecurity
   public class SecurityConfig extends WebSecurityConfigurerAdapter {
   
       // 授权 Authorization
       @Override
       protected void configure(HttpSecurity http) throws Exception {
           // 首页所有人可以访问，但是功能页只有对应有权限的人才能访问
           // 请求授权的规则
           http.authorizeRequests()
                   .antMatchers("/").permitAll()
                   .antMatchers("/level1/**").hasRole("vip1")
                   .antMatchers("/level2/**").hasRole("vip2")
                   .antMatchers("/level3/**").hasRole("vip3");
   
           // 没有权限会默认请求 /login 到登录页面（自带的登录页面）
           // 定制登录页
           // 默认接收 username 和 password
           http.formLogin().loginPage("/toLogin").loginProcessingUrl("/login")
               .usernameParameter("username").passwordParameter("password");
   
           // 注销
           http.csrf().disable();  // 关闭csrf，否则可能访问不到 /logout
           http.logout().logoutSuccessUrl("/");
   
           // 开启记住我功能  cookie，默认保存两周
           // 默认是 remember-me
           http.rememberMe().rememberMeParameter("remember");
       }
   
       // 认证 Authentication
       // springboot 2.1.x 可以直接使用
       // 密码编码：PasswordEncoder
       // 在 SpringSecurity 5.0+ 新增了很多加密方法
       @Override
       protected void configure(AuthenticationManagerBuilder auth) throws Exception {
           // 这些数据正常从数据库取出
           auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                   .withUser("sugar").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2", "vip3")
                   .and()
                   .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip2", "vip3")
                   .and()
                   .withUser("guest").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");
       }
   }
   
   ```

4. 根据权限，定制 index.html 页面

   ```html
   <!DOCTYPE html>
   <html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
   <head>
       <meta charset="UTF-8">
       <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
       <title>首页</title>
       <!--semantic-ui-->
       <link href="https://cdn.bootcss.com/semantic-ui/2.4.1/semantic.min.css" rel="stylesheet">
       <link th:href="@{/qinjiang/css/qinstyle.css}" rel="stylesheet">
   </head>
   <body>
   
   <!--主容器-->
   <div class="ui container">
   
       <div class="ui segment" id="index-header-nav" th:fragment="nav-menu">
           <div class="ui secondary menu">
               <a class="item"  th:href="@{/index}">首页</a>
   
               <!--登录注销-->
               <div class="right menu">
   
                   <!--如果未登录-->
                   <div sec:authorize="!isAuthenticated()">
                       <a class="item" th:href="@{/toLogin}">
                           <i class="address card icon"></i> 登录
                       </a>
                   </div>
                   <!--如果已登录-->
                   <div sec:authorize="isAuthenticated()">
                       <a class="item">
                           用户名：<span sec:authentication="name"></span>
                       </a>
                       <a class="item" th:href="@{/logout}">
                           <i class="address card icon"></i> 注销
                       </a>
                   </div>
   
               </div>
           </div>
       </div>
   
       <div>
           <br>
           <div class="ui three column stackable grid">
               <!--根据用户角色，动态菜单展示-->
               <div class="column" sec:authorize="hasRole('vip1')">
                   <div class="ui raised segment">
                       <div class="ui">
                           <div class="content">
                               <h5 class="content">Level 1</h5>
                               <hr>
                               <div><a th:href="@{/level1/1}"><i class="bullhorn icon"></i> Level-1-1</a></div>
                               <div><a th:href="@{/level1/2}"><i class="bullhorn icon"></i> Level-1-2</a></div>
                               <div><a th:href="@{/level1/3}"><i class="bullhorn icon"></i> Level-1-3</a></div>
                           </div>
                       </div>
                   </div>
               </div>
   
               <div class="column" sec:authorize="hasRole('vip2')">
                   <div class="ui raised segment">
                       <div class="ui">
                           <div class="content">
                               <h5 class="content">Level 2</h5>
                               <hr>
                               <div><a th:href="@{/level2/1}"><i class="bullhorn icon"></i> Level-2-1</a></div>
                               <div><a th:href="@{/level2/2}"><i class="bullhorn icon"></i> Level-2-2</a></div>
                               <div><a th:href="@{/level2/3}"><i class="bullhorn icon"></i> Level-2-3</a></div>
                           </div>
                       </div>
                   </div>
               </div>
   
               <div class="column" sec:authorize="hasRole('vip3')">
                   <div class="ui raised segment">
                       <div class="ui">
                           <div class="content">
                               <h5 class="content">Level 3</h5>
                               <hr>
                               <div><a th:href="@{/level3/1}"><i class="bullhorn icon"></i> Level-3-1</a></div>
                               <div><a th:href="@{/level3/2}"><i class="bullhorn icon"></i> Level-3-2</a></div>
                               <div><a th:href="@{/level3/3}"><i class="bullhorn icon"></i> Level-3-3</a></div>
                           </div>
                       </div>
                   </div>
               </div>
   
           </div>
       </div>
       
   </div>
   
   
   <script th:src="@{/qinjiang/js/jquery-3.1.1.min.js}"></script>
   <script th:src="@{/qinjiang/js/semantic.min.js}"></script>
   
   </body>
   </html>
   ```

#### 6.2 Shiro

Apache Shiro 是一个 Java 的安全（权限）框架。

可以非常容易的开发出足够好的应用，不仅可以用在 JavaSE 环境，也可以用在 JavaEE 环境。

Shiro可以完成认证、授权、加密、会话管理、Web继承、缓存等。

下载地址：https://shiro.apache.org/

**Shiro功能**

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201006234750186.png" alt="image-20201006234750186" style="zoom:30%;" />

- Authentication：身份认证、登录，验证用户是否拥有相应的身份
- Authorization：授权，即权限验证，验证某个已认证的用户是否拥有某个权限，即判断用户能否进行什么操作，如：验证某个用户是否拥有某个角色，或者细粒度的验证某个用户对某个资源是否具有某个权限！
- Session Manager：会话管理，即用户登录后就是第一次会话，在没有退出之前，所有信息都在会话中，会话可以是普通的JavaSE环境，也可以是Web环境。
- Cryptography：加密，保护数据的安全性，如密码加密存储到数据库中，而不是明文存储
- Web Support：Web支持，可以非常容易集成到Web环境
- Caching：缓存，如用户登录后，其用户信息，拥有的角色、权限不必每次去查，可以提高效率
- Concurrency：Shiro支持多线程应用的并发验证，如在一个线程中开启另一个线程，能把权限自动的传播过去
- Testing：提供测试支持
- Run As：允许一个用户假装成另一个用户的身份进行访问
- Remember Me：记住我，即一次登录后，下次再来不用登录了

**Shiro架构（外部）**

从应用程序角度观察如何使用shiro完成工作

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201006235302324.png" alt="image-20201006235302324" style="zoom:30%;" />

- subject：应用代码直接交互的对象是Subject，即Shiro的对外AIP核心就是Subject，Subject代表了当前的用户，这个用户不一定是一个具体的人，与当前应用交互的任何东西都是Subject，如网络爬虫、机器人等，与Subject的所有交互都会委托给SecurityManager，Subject其实是一个门面，SecurityManager才是实际的执行者
- SecurityManager：安全管理器，即所有与安全有关的操作都会与SecurityManager交互，并且它管理着所有的Subject，它是Shiro的核心，负责与Shiro的其他组件进行交互，相当于SpringMVC的DispatcherServlet的角色
- Realm：Shiro从Realm获取安全数据（如用户、角色、权限），即，SecurityManager要验证用户身份，那么它需要从Realm获取相应的用户进行比较，来确定用户的身份是否合法，也需要从Realm得到用户相应的角色、权限，进行验证用户的操作是否能够进行，可以把Realm看成DataSource。

**Shiro架构（内部）**

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201006235815003.png" alt="image-20201006235815003" style="zoom:30%;" />

- Subject：任何可以与应用交互的”用户“
- Security Manager：核心，所有的具体交互都通过Security Manager进行控制，管理所有的Subject，负责进行认证、授权、会话及缓存的管理。
- Authenticator：负责Subject认证，是一个扩展点，可以自定义实现，可以使用认证策略（Authentication Strategy），即什么情况下算用户认证通过
- Authorizer：授权器，即访问控制器，用来决定主题是否有权限进行相应的操作，即控制着用户能访问应用中的哪些功能
- Realm：可以有一个或多个Realm，可认为是安全实体数据源，即用于获取安全试题的，可以用JDBC实现，也可以是内存实现，由用户提供，在一般应用中都需要实现自己的Realm
- SessionManager：缓存控制器，来管理如用户、角色、权限等缓存的，因为这些数据基本很少改变，放在缓存中可以提高访问性能
- Criptography：密码模块，Shiro提高了一些常见的加密组件，用于密码加密、解密等。

##### 6.2.1 Quickstart

1. 导入依赖

   ```xml
   <dependency>
     <groupId>org.apache.shiro</groupId>
     <artifactId>shiro-core</artifactId>
     <version>1.4.1</version>
   </dependency>
   <!-- configure logging -->
   <dependency>
     <groupId>org.slf4j</groupId>
     <artifactId>jcl-over-slf4j</artifactId>
     <version>1.7.21</version>
   </dependency>
   <dependency>
     <groupId>org.slf4j</groupId>
     <artifactId>slf4j-log4j12</artifactId>
     <version>1.7.21</version>
   </dependency>
   <dependency>
     <groupId>log4j</groupId>
     <artifactId>log4j</artifactId>
     <version>1.2.17</version>
   </dependency>
   ```

2. 配置文件 shiro.ini

   ```ini
   [users]
   # user 'root' with password 'secret' and the 'admin' role
   root = secret, admin
   # user 'guest' with the password 'guest' and the 'guest' role
   guest = guest, guest
   # user 'presidentskroob' with password '12345' ("That's the same combination on
   # my luggage!!!" ;)), and role 'president'
   presidentskroob = 12345, president
   # user 'darkhelmet' with password 'ludicrousspeed' and roles 'darklord' and 'schwartz'
   darkhelmet = ludicrousspeed, darklord, schwartz
   # user 'lonestarr' with password 'vespa' and roles 'goodguy' and 'schwartz'
   lonestarr = vespa, goodguy, schwartz
   
   # -----------------------------------------------------------------------------
   # Roles with assigned permissions
   # 
   # Each line conforms to the format defined in the
   # org.apache.shiro.realm.text.TextConfigurationRealm#setRoleDefinitions JavaDoc
   # -----------------------------------------------------------------------------
   [roles]
   # 'admin' role has all permissions, indicated by the wildcard '*'
   admin = *
   # The 'schwartz' role can do anything (*) with any lightsaber:
   schwartz = lightsaber:*
   # The 'goodguy' role is allowed to 'drive' (action) the winnebago (type) with
   # license plate 'eagle5' (instance specific id)
   goodguy = winnebago:drive:eagle5
   ```

3. HelloWorld

   ```java
   import org.apache.shiro.SecurityUtils;
   import org.apache.shiro.authc.*;
   import org.apache.shiro.ini.IniSecurityManagerFactory;
   import org.apache.shiro.mgt.SecurityManager;
   import org.apache.shiro.session.Session;
   import org.apache.shiro.subject.Subject;
   import org.apache.shiro.lang.util.Factory;
   import org.slf4j.Logger;
   import org.slf4j.LoggerFactory;
   
   
   /**
    * Simple Quickstart application showing how to use Shiro's API.
    *
    * @since 0.9 RC2
    */
   public class Quickstart {
   
       private static final transient Logger log = LoggerFactory.getLogger(Quickstart.class);
   
   
       public static void main(String[] args) {
   
           // The easiest way to create a Shiro SecurityManager with configured
           // realms, users, roles and permissions is to use the simple INI config.
           // We'll do that by using a factory that can ingest a .ini file and
           // return a SecurityManager instance:
   
           // Use the shiro.ini file at the root of the classpath
           // (file: and url: prefixes load from files and urls respectively):
           Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
           SecurityManager securityManager = factory.getInstance();
   
           // for this simple example quickstart, make the SecurityManager
           // accessible as a JVM singleton.  Most applications wouldn't do this
           // and instead rely on their container configuration or web.xml for
           // webapps.  That is outside the scope of this simple quickstart, so
           // we'll just do the bare minimum so you can continue to get a feel
           // for things.
           SecurityUtils.setSecurityManager(securityManager);
   
           // Now that a simple Shiro environment is set up, let's see what you can do:
   
           // get the currently executing user:
           Subject currentUser = SecurityUtils.getSubject();
   
           // Do some stuff with a Session (no need for a web or EJB container!!!)
           Session session = currentUser.getSession();
           session.setAttribute("someKey", "aValue");
           String value = (String) session.getAttribute("someKey");
           if (value.equals("aValue")) {
               log.info("Retrieved the correct value! [" + value + "]");
           }
   
           // let's login the current user so we can check against roles and permissions:
           if (!currentUser.isAuthenticated()) {
               UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
               token.setRememberMe(true);
               try {
                   currentUser.login(token);
               } catch (UnknownAccountException uae) {
                   log.info("There is no user with username of " + token.getPrincipal());
               } catch (IncorrectCredentialsException ice) {
                   log.info("Password for account " + token.getPrincipal() + " was incorrect!");
               } catch (LockedAccountException lae) {
                   log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                           "Please contact your administrator to unlock it.");
               }
               // ... catch more exceptions here (maybe custom ones specific to your application?
               catch (AuthenticationException ae) {
                   //unexpected condition?  error?
               }
           }
   
           //say who they are:
           //print their identifying principal (in this case, a username):
           log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");
   
           //test a role:
           if (currentUser.hasRole("schwartz")) {
               log.info("May the Schwartz be with you!");
           } else {
               log.info("Hello, mere mortal.");
           }
   
           //test a typed permission (not instance-level)
           if (currentUser.isPermitted("lightsaber:wield")) {
               log.info("You may use a lightsaber ring.  Use it wisely.");
           } else {
               log.info("Sorry, lightsaber rings are for schwartz masters only.");
           }
   
           //a (very powerful) Instance Level permission:
           if (currentUser.isPermitted("winnebago:drive:eagle5")) {
               log.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
                       "Here are the keys - have fun!");
           } else {
               log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
           }
   
           //all done - log out!
           currentUser.logout();
   
           System.exit(0);
       }
   }
   ```

   ​	核心代码：

   ```java
   Subject currentUser = SecurityUtils.getSubject();
   Session session = currentUser.getSession();
   currentUser.isAuthenticated()
   currentUser.getPrincipal()
   currentUser.hasRole("schwartz")
   currentUser.isPermitted("lightsaber:wield")
   currentUser.logout();
   ```

##### 6.2.2 在SpringBoot中集成

1. 导入依赖

   ```xml
   <!--
     Subject  用户
     SecurityManager  管理所有用户
     Realm  连接数据
     -->
   <!--shiro整合spring的包-->
   <dependency>
     <groupId>org.apache.shiro</groupId>
     <artifactId>shiro-spring</artifactId>
     <version>1.4.1</version>
   </dependency>
   <!--thymeleaf-->
   <dependency>
     <groupId>org.thymeleaf</groupId>
     <artifactId>thymeleaf-spring5</artifactId>
   </dependency>
   <dependency>
     <groupId>org.thymeleaf.extras</groupId>
     <artifactId>thymeleaf-extras-java8time</artifactId>
   </dependency>
   ```

2. 编写配置类 ShiroConfig

   ```java
   package com.sugar.config;
   
   import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
   import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
   import org.springframework.beans.factory.annotation.Qualifier;
   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Configuration;
   
   @Configuration
   public class ShiroConfig {
   
       // ShiroFilterFactoryBean（第三步）
       @Bean
       public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
           ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
           // 设置安全管理器
           bean.setSecurityManager(securityManager);
           return bean;
       }
   
       // DefaultWebSecurityManager（第二步）
       @Bean(name = "securityManager")
       public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
           DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
           // 关联UserRealm
           securityManager.setRealm(userRealm);
           return securityManager;
       }
   
       // 创建Realm对象，需要自定义类（第一步）
       @Bean(name="userRealm")
       public UserRealm userRealm() {
           return new UserRealm();
       }
   }
   ```

3. 自定义 Realm

   ```java
   package com.sugar.config;
   
   import org.apache.shiro.authc.AuthenticationException;
   import org.apache.shiro.authc.AuthenticationInfo;
   import org.apache.shiro.authc.AuthenticationToken;
   import org.apache.shiro.authz.AuthorizationInfo;
   import org.apache.shiro.realm.AuthorizingRealm;
   import org.apache.shiro.subject.PrincipalCollection;
   
   // 自定义的 UserRealm
   public class UserRealm extends AuthorizingRealm {
   
       // 授权
       @Override
       protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
           System.out.println("执行了授权>doGetAuthorizationInfo");
           return null;
       }
   
       // 认证
       @Override
       protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
           System.out.println("执行了认证>doGetAuthenticationInfo");
           return null;
       }
   }
   ```

##### 6.2.3 实现登录拦截

1. 修改 ShiroConfig

   ```java
       // ShiroFilterFactoryBean（第三步）
       @Bean
       public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
           ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
           // 设置安全管理器
           bean.setSecurityManager(securityManager);
   
           // 添加shiro的内置过滤器
           /*
               anon：无需认证就可以访问
               authc：必须认证了才能访问
               user：必须拥有记住我才能用
               perms：拥有对某个资源的权限才能访问
               role：拥有某个角色权限才能访问
   
   //        filterMap.put("/user/add", "authc");
   //        filterMap.put("/user/update", "authc");
            */
           Map<String, String> filterMap = new LinkedHashMap<>();
           filterMap.put("/user/*", "authc");
           bean.setFilterChainDefinitionMap(filterMap);
   
           // 没有权限，跳转到登录页面
           bean.setLoginUrl("/toLogin");
           return bean;
       }
   ```

##### 6.2.4 实现用户认证

1. 编写登录页面 login.html

   ```html
   <h1>登录</h1>
   <hr>
   <p th:text="${msg}" style="color: red"></p>
   
   <form th:action="@{/login}">
       <p>用户名：<input type="text" name="username"></p>
       <p>密码：<input type="text" name="password"></p>
       <p><input type="submit" value="登录"></p>
   </form>
   ```

2. 编写 Controller，处理登录请求

   subject.login，就会自动走认证。

   ```java
       @RequestMapping("/toLogin")
       public String toLogin() {
           return "login";
       }
   
       @RequestMapping("/login")
       public String login(String username, String password, Model model) {
           // 获取当前用户
           Subject subject = SecurityUtils.getSubject();
           // 封装用户的登录数据
           UsernamePasswordToken token = new UsernamePasswordToken(username, password);
   
           try {
               subject.login(token);   // 执行登录的方法，如果没有异常则OK
               return "index";
           } catch (UnknownAccountException e) { // 用户名不存在
               model.addAttribute("msg", "用户名错误");
               return "login";
           } catch (IncorrectCredentialsException e) {  // 密码不存在
               model.addAttribute("msg", "密码错误");
               return "login";
           }
   
       }
   ```

3. 修改 UserRealm，处理认证

   这里需要从数据库中取 username 和 password。

   ```java
       // 认证
       @Override
       protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
           System.out.println("执行了认证>doGetAuthenticationInfo");
   
           // 用户名、密码，从数据库中取！
           String name = "root";
           String password = "123456";
   
           UsernamePasswordToken userToken = (UsernamePasswordToken) token;
   
           if (!userToken.getUsername().equals(name)) {
               return null;  // 抛出异常，UnknownAccountException
           }
           // 密码认证，交给shiro做
           return new SimpleAuthenticationInfo("", password, "");
       }
   ```

##### 6.2.5 集成 Mybatis

1. 搭建环境，pojo、mapper、mapper.xml、service

2. 修改 UserRealm，从真实数据库对比用户名和密码

   ```java
       // 认证
       @Override
       protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
           System.out.println("执行了认证>doGetAuthenticationInfo");
   
           // 1、用户名、密码，从内存取
   //        String name = "root";
   //        String password = "123456";
   //        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
   //        if (!userToken.getUsername().equals(name)) {
   //            return null;  // 抛出异常，UnknownAccountException
   //        }
   //         密码认证，交给shiro做
   //        return new SimpleAuthenticationInfo("", password, "");
   
           // 2、连接真实数据库
           UsernamePasswordToken userToken = (UsernamePasswordToken) token;
           User user = userService.queryUserByName(userToken.getUsername());
           if (user == null) {
               return null;
           }
           return new SimpleAuthenticationInfo("", user.getPwd(), "");
       }
   ```

##### 6.2.6 实现请求授权

1. 在 ShiroConfig 添加授权拦截，配置未授权跳转页面

   ```java
   Map<String, String> filterMap = new LinkedHashMap<>();
   // 授权（注意语句顺序，授权需要在前）
   filterMap.put("/user/add", "perms[user:add]");
   filterMap.put("/user/update", "perms[user:update]");
   // 拦截
   filterMap.put("/user/*", "authc");
   bean.setFilterChainDefinitionMap(filterMap);
   // 没有登录，跳转到登录页面
   bean.setLoginUrl("/toLogin");
   // 没有授权，跳转到未授权页面
   bean.setUnauthorizedUrl("/noauth");
   ```

2. 修改 UserRealm，处理授权

   ```java
   // 授权
   @Override
   protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
     System.out.println("执行了授权>doGetAuthorizationInfo");
   
     SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
   
     // 拿到当前登录的对象
     Subject subject = SecurityUtils.getSubject();
     User currentUser = (User) subject.getPrincipal();
     // 设置当前用户的权限
     info.addStringPermission(currentUser.getPerms());
     return info;
   }
   ```

##### 6.2.7 整合 thymeleaf

1. 导入依赖

   ```xml
   <dependency>
     <groupId>com.github.theborakompanioni</groupId>
     <artifactId>thymeleaf-extras-shiro</artifactId>
     <version>2.0.0</version>
   </dependency>
   ```

2. 在 ShiroConfig 中整合 thymeleaf

   ```java
   // 整合 ShiroDialect：用来整合 shiro thymeleaf
   @Bean
   public ShiroDialect getShiroDialect() {
     return new ShiroDialect();
   }
   ```

3. 修改 index.html，根据权限动态展示

   ```html
   <!DOCTYPE html>
   <html lang="en" xmlns:th="http://www.thymeleaf.org"
         xmlns:shiro="http://www.thymeleaf.org/thymeleaf-extras-shiro">
   <head>
       <meta charset="UTF-8">
       <title>Title</title>
   </head>
   <body>
   
   <h1>首页</h1>
   
   <p th:text="${msg}"></p>
   <div shiro:notAuthenticated>
       <a th:href="@{/toLogin}">登录</a>
   </div>
   
   <hr>
   
   <div shiro:hasPermission="user:add">
       <a th:href="@{/user/add}">add</a> |
   </div>
   
   <div shiro:hasPermission="user:update">
       <a th:href="@{/user/update}">update</a>
   </div>
   </body>
   </html>
   ```

### 7. Swagger

学习目标：

- 了解Swagger的作用和概念
- 了解前后端分离
- 在SpringBoot中集成Swagger

#### 7.1 简介

**前后端分离**

Vue + SpringBoot

- 前后端交互  ==> API
- 前后端相对独立，松耦合
- 前后端甚至可以部署在不同服务器上

产生问题：

- 前后端集成联调，前后端人员无法做到”即时协商，尽早解决“，最终导致问题集中爆发

解决方案：

- 首先指定`schema`[计划的提纲]，实时更新最新的API，降低集成的风险
- 早些年：制定word计划文档
- 前后端分离：
  - 前端测试后端的接口：**Postman**
  - 后端提供接口，需要实时更新最新的消息及改动
- Swagger！
  - 号称世界上最流行的API框架
  - RestFulAPI文档在线自动生成工具 => **API文档与API定义同步更新**
  - 直接运行，可以在线测试API接口
  - 支持多种语言
  - 官网：https://swagger.io

**Swagger**

在项目中使用Swagger需要 `Springbox`

- swagger2
- ui

#### 7.2 SpringBoot集成Swagger

##### 7.2.1 环境搭建

1. 新建一个SpringBoot web项目

2. 导入相关依赖

   ```xml
   <!--Swagger-->
   <dependency>
     <groupId>io.springfox</groupId>
     <artifactId>springfox-swagger2</artifactId>
     <version>2.9.2</version>
   </dependency>
   <dependency>
     <groupId>io.springfox</groupId>
     <artifactId>springfox-swagger-ui</artifactId>
     <version>2.9.2</version>
   </dependency>
   ```

3. 编写一个HelloWorld Controller

   ```java
   @RestController
   public class HelloController {
   
       @RequestMapping("/hello")
       public String hello() {
           return "hello";
       }
   }
   ```

4. 配置Swagger  ==> Config

   ```java
   @Configuration
   @EnableSwagger2
   public class SwaggerConfig {
       
      
   }
   ```

5. 测试运行：http://localhost:8080/swagger-ui.html

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201007225131143.png" alt="image-20201007225131143" style="zoom:40%;" />

##### 7.2.2 配置基本信息

Swagger的bean实例：Docket

修改 SwaggerConfig，配置基本信息

```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // 配置Swagger的 Docket 的 Bean实例
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
    }

    // 配置Swagger信息 apiInfo
    private ApiInfo apiInfo() {
        // 作者信息
        Contact contact = new Contact("Sugar", "https://www.tzchuan.cn", "406857586@qq.com");

        return new ApiInfo(
                "Sugar的Swagger API文档",
                "哈哈哈描述",
                "1.0",
                "https://www.tzchuan.cn",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
```

##### 7.2.3 配置扫描接口及开关

Docket.select()

```java
// 配置Swagger的 Docket 的 Bean实例
@Bean
public Docket docket() {
  return new Docket(DocumentationType.SWAGGER_2)
    .apiInfo(apiInfo())
    // 是否开启Swagger
    .enable(false)
    .select()
    // RequestHandlerSelectors 配置要扫描接口的方式
    // basePackage 指定要扫描的包
    // any() 扫描全部
    // none() 不扫描
    // withClassAnnotation() 扫描类上的注解，参数是一个注解的反射对象
    // withMethodAnnotation() 扫描方法上的注解
    .apis(RequestHandlerSelectors.basePackage("com.sugar.controller"))
    // paths() 过滤要扫描的路径
    //                .paths(PathSelectors.ant("/sugar/**"))
    .build();
}
```

如何使Swagger在生产环境中使用，在发布时不适用？

- 判断是否是生产环境 flag = false
- 注入enable()

```java
// 设置要显示Swagger的环境
Profiles profiles = Profiles.of("dev", "test");
// 通过监听，判断是否项目处在设定的环境当中
boolean flag = environment.acceptsProfiles(profiles);

return new Docket(DocumentationType.SWAGGER_2)
  .enable(flag);
```

##### 7.2.4 配置API文档分组

```java
.groupName("sugar")
```

配置多个分组，可以通过配置多个Docket实例即可。

```java
@Bean
public Docket docket1() {
  return new Docket(DocumentationType.SWAGGER_2)
    .groupName("sugar2");
}
@Bean
public Docket docket3() {
  return new Docket(DocumentationType.SWAGGER_2)
    .groupName("sugar3");
}
@Bean
public Docket docket4() {
  return new Docket(DocumentationType.SWAGGER_2)
    .groupName("sugar4");
}
```

##### 7.2.5 配置注释

实体类上

```java
@Api(tags = "Hello控制类")
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    // 只要接口中，返回值中存在实体类，就会被扫描到swagger中
    @GetMapping("/user")
    public User user() {
        return new User();
    }

    // Operation放在方法上
    @ApiOperation("hello2方法")
    @GetMapping("/hello2")
    public String hello2(@ApiParam("用户名") String username) {
        return "hello " + username;
    }
}
```

Controller上

```java
@ApiModel("用户实体类")
public class User {
    @ApiModelProperty("用户名")
    public String username;
    public String password;
}
```

##### 7.2.6 总结

1. 可以通过Swagger给一些比较难理解的属性或者接口，增加注释信息
2. 接口文档实时更新
3. 可以在线测试

**注意点**：在正式发布的时候，关闭Swagger！

### 8. 特定任务

#### 8.1 异步任务

1. 开启异步注解功能

   ```java
   // 开启异步注解功能
   @EnableAsync
   @SpringBootApplication
   public class Springboot09TaskApplication {
   	public static void main(String[] args) {
   		SpringApplication.run(Springboot09TaskApplication.class, args);
   	}
   }
   ```

2. 在需要异步的方法上，加上异步注解

   ```java
   // 告诉Spring这是一个异步的方法
   @Async
   public void hello() {
     try {
       Thread.sleep(3000);
     } catch (Exception e) {
       e.printStackTrace();
     }
     System.out.println("数据正在处理...");
   }
   ```

3. 编写Controller调用测试

   ```java
   @RestController
   public class AsyncController {
   
       @Autowired
       AsyncService asyncService;
   
       @RequestMapping("/hello")
       public String hello() {
           asyncService.hello();  // 停止三秒
           return "OK";
       }
   }
   ```

#### 8.2 邮件任务

1. 导入mail依赖

   ```xml
   <!--Email-->
   <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-mail</artifactId>
   </dependency>
   ```

2. 在 application.yml 编写 mail相关配置

   ```yml
   spring:
     mail:
       username: 406857586@qq.com
       password: nmeejbsjhtklcagh
       host: smtp.qq.com
       # 开启加密验证，仅QQ邮箱需要
       properties:
         mail:
           smtp:
             ssl:
               enable: true
   ```

3. 测试

   ```java
   package com.sugar;
   
   import org.junit.jupiter.api.Test;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.boot.test.context.SpringBootTest;
   import org.springframework.mail.SimpleMailMessage;
   import org.springframework.mail.javamail.JavaMailSenderImpl;
   import org.springframework.mail.javamail.MimeMailMessage;
   import org.springframework.mail.javamail.MimeMessageHelper;
   
   import javax.mail.MessagingException;
   import javax.mail.internet.MimeMessage;
   import java.io.File;
   
   @SpringBootTest
   class Springboot09TaskApplicationTests {
   
   	@Autowired
   	JavaMailSenderImpl mailSender;
   
   	@Test
   	void contextLoads() {
   		// 一个简单的邮件
   		SimpleMailMessage mailMessage = new SimpleMailMessage();
   		mailMessage.setSubject("Sugar，你好");
   		mailMessage.setText("谢谢啊");
   		mailMessage.setTo("406857586@qq.com");
   		mailMessage.setFrom("406857586@qq.com");
   		mailSender.send(mailMessage);
   	}
   
   	@Test
   	void contextLoads2() throws MessagingException {
   		// 一个复杂的邮件
   		MimeMessage mimeMessage = mailSender.createMimeMessage();
   		// 组装
   		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
   		helper.setSubject("Sugar，你好");
   		helper.setText("<p style='color:red'>啊哈哈哈</p>", true);  // true，开启解析html
   		// 附件
   		helper.addAttachment("1.jpg", new File("/Users/Sugar/Desktop/1.jpg"));
   		helper.setTo("406857586@qq.com");
   		helper.setFrom("406857586@qq.com");
   		mailSender.send(mimeMessage);
   	}
   
   	// 封装发邮件方法
   	public void sendMail(boolean enableHtml, String subject, String text) throws MessagingException {
   		// 一个复杂的邮件
   		MimeMessage mimeMessage = mailSender.createMimeMessage();
   		// 组装
   		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, enableHtml);
   		helper.setSubject(subject);
   		helper.setText(text, true);  // true，开启解析html
   		// 附件
   		helper.addAttachment("1.jpg", new File("/Users/Sugar/Desktop/1.jpg"));
   		helper.setTo("406857586@qq.com");
   		helper.setFrom("406857586@qq.com");
   		mailSender.send(mimeMessage);	
   	}
   }
   
   ```

#### 8.3 定时执行任务

核心接口：`TaskScheduler[任务调度者]`和`TaskExecutor[任务执行者]`

核心注解：`@EnableScheduling[开启定时功能的注解]` 和 `@Scheduled[什么时候执行]`

表达式：`Cron`

1. 开启定时功能注解

   ```java
   @EnableAsync  // 开启异步注解功能
   @EnableScheduling  // 开启定时功能
   @SpringBootApplication
   public class Springboot09TaskApplication {
   	public static void main(String[] args) {
   		SpringApplication.run(Springboot09TaskApplication.class, args);
   	}
   }
   ```

2. 设定定时任务

   ```java
   package com.sugar.service;
   
   import org.springframework.scheduling.annotation.Scheduled;
   import org.springframework.stereotype.Service;
   
   @Service
   public class ScheduledService {
       
       // 在一个特定时间执行这个方法
       // cron表达式
       // 秒 分 时 日 月 周几~
       /*
          30 15 10 * * ?  每天10点15分30秒 执行一次
          30 0/5 10,18 * * ?  每天10点和18点，从0分开始，每隔5分钟 执行一次
          0 15 10 ? * 1-6  每个月的周一到周六，10点15分执行一次
        */
       @Scheduled(cron = "0 * * * * 0-7")
       public void hello() {
           System.out.println("hello，你被执行了");
       }
   }
   ```

### 9. 分布式

#### 9.1 分布式理论

分布式系统是由一组通过**网络进行通信**，为了完成**共同的任务**而协调工作的计算机节点组成的系统。

分布式系统的出现是为了用廉价、普通的机器完成单个计算机无法完成的计算、存储任务。

其目的是**利用更多的机器，处理更多的数据**。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201008110243605.png" alt="image-20201008110243605" style="zoom:40%;" />

**单一应用架构**

网站流量很小时，只需一个应用，将所有功能都部署在一起，以减少部署节点和成本。

此时，用于简化增删改查工作量的**数据访问框架（ORM）**是关键。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201008105705376.png" alt="image-20201008105705376" style="zoom:30%;" />

缺点：

- 性能扩展比较难
- 协同开发问题
- 不利于升级维护

**垂直应用架构**

当访问量逐渐增大，单一应用增加机器带来的加速度越来越小，将应用拆成互不相干的几个应用，以提升效率。

此时，用于加速前端页面开发的**Web框架（MVC）**是关键。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201008105836710.png" alt="image-20201008105836710" style="zoom:30%;" />

缺点：公共模块无法重复利用，开发性的浪费。

**分布式服务架构**

当垂直应用越来越多，应用之间交互不可避免，将核心业务抽取出来，作为独立的服务，主键形成稳定的服务中心，使前端应用能更快速的响应多变的市场需求。

此时，用于提高业务复用及整合的**分布式服务框架（RPC）**是关键。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201008110029737.png" alt="image-20201008110029737" style="zoom:30%;" />

**流动计算架构**

当服务越来越多，容量的评估，小服务资源的浪费等问题逐渐显现，此时需增加一个调度中心基于访问压力实时管理集群容量，提高集群利用率。

此时，用于**提高机器利用率的资源调度和治理中心（SOA）**是关键。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201008110142626.png" alt="image-20201008110142626" style="zoom:30%;" />

#### 9.2 RPC

RPC（Remote Procedure Call）指远程过程调用，是一种进程间通信方式，是一种技术思想而不是规范。它允许程序调用另一个地址空间（通常是共享网络的另一台机器上）的过程或函数，而不用程序员显式编码这个远程调用的细节。即程序员无论是调用本地的还是远程的函数，本质上编写的调用代码基本相同。

即两台服务器A和B，一个应用部署在A服务器上，想要调用B服务器上应用提供的函数/方法，由于不在一个内存空间，不能直接调用，需要通过网络来表达调用的语义和传达调用的数据。

使用RPC的目的：就是无法再一个进程内，甚至一个计算机内通过本地调用的方式完成的需求，比如不同的系统间的通讯，甚至不同的组织间的通讯，由于计算能力需要横向扩展，需要在多台计算机组成的集群上部署应用。RPC就是要像调用本地的函数一样去调用远程函数。

核心模块：**通讯**、**序列化**

序列化：数据传输需要转换

**RPC基本原理**

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201008110941103.png" alt="image-20201008110941103" style="zoom:40%;" />

**步骤解析**

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201008111027414.png" alt="image-20201008111027414" style="zoom:40%;" />



#### 9.3 Dubbo、Zookeeper

##### 9.3.1 简介

Apache Dubbo是一款高性能、轻量级的开源 Java RPC框架，提供三大核心能力：**面向接口的远程方法调用**、**智能容错和负载均衡**、**服务自动注册和发现**。

官网：http://dubbo.apache.org/

<img src="http://dubbo.apache.org/img/architecture.png" alt="img" style="zoom:50%;" />

**服务提供者（Provider）**：暴露服务的服务提供方，服务提供者在启动时，向注册中心注册自己提供的服务。

**服务消费者（Consumer）**：调用远程服务的服务消费方，服务消费者在启动时，向注册中心订阅自己所需的服务，服务消费者，从提供者地址列表中，基于软负载均衡算法，选一台提供者进行调用，如果调用失败，再选另一台调用。

**注册中心（Register）**：注册中心返回服务提供者地址列表给消费者，如果有变更，注册中心将基于长连接推送变更数据给消费者。

**监控中心（Monitor）**：服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到监控中心。



**调用关系说明：**

- 服务容器负责启动，加载，运行服务提供者
- 服务提供者在启动时，向注册中心注册自己提供的服务
- 服务消费者在启动时，向注册中心订阅自己所需的服务
- 注册中心返回服务提供者地址列表给消费者，如果有变更，注册中心将基于长连接推送变更数据给消费者
- 服务消费者，从提供者地址列表中，基于软负载均衡算法，选一台提供者进行调用，如果调用失败，再选另一台调用
- 服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到监控中心

##### 9.3.2 Dubbo环境搭建

推荐使用 **Zookeeper注册中心**。

**安装Zookeeper**

1. 直接解压 `zookeeper-3.4.14`

2. 在 `conf` 目录下，新建一个 `zoo.cfg` 文件，配置如下

   ```cfg
   # 服务器与客户端之间交互的基本时间单元（ms）
   tickTime=2000
   # zookeeper所能接受的客户端数量
   initLimit=10
   # 服务器和客户端之间请求和应答之间的时间间隔
   syncLimit=5
   # 数据目录，可以是任意目录
   dataDir=/tmp/zookeeper
   # 监听client连接的端口号
   clientPort=2181
   # 最大客户端连接数
   #maxClientCnxns=60
   # The number of snapshots to retain in dataDir
   #autopurge.snapRetainCount=3
   # Purge task interval in hours
   # Set to "0" to disable auto purge feature
   #autopurge.purgeInterval=1
   ```

3. 运行/关闭 Zookeeper

   ```cmd
   ./zkServer.sh start
   ./zkServer.sh stop
   ```

4. 使用 zkCli.cmd 测试

   ```cmd
   ls /：列出zookeeper跟下保存的所有节点
   create -e /sugar 123：创建一个sugar节点，值为123
   get /sugar：获取/sugar节点的值
   ```

**安装Dubbo-admin**

Dubbo本身不是一个服务软件，而是一个jar包，能够帮助Java程序连接到Zookeeper，并利用zookeeper消费、提供服务。

但为了更好的管理监控众多的dubbo服务，官方提供了一个可视化的监控程序 dubbo-admin。（不装也不影响使用）

1. 下载dubbo-admin

   地址：https://github.com/apache/dubbo-admin/tree/master

2. 解压进入目录

   修改 dubbo-admin/src/main/resources/application.properties 指定 zookeeper地址。

   ```properties
   server.port=7001
   spring.velocity.cache=false
   spring.velocity.charset=UTF-8
   spring.velocity.layout-url=/templates/default.vm
   spring.messages.fallback-to-system-locale=false
   spring.messages.basename=i18n/message
   spring.root.password=root
   spring.guest.password=guest
   # 注册中心的地址
   dubbo.registry.address=zookeeper://127.0.0.1:2181
   ```

3. 打包 dubbo-admin-master 目录下执行

   ```cmd
   mvn clean package -Dmaven.test.skip=true
   ```

4. 执行 dubbo-admin/target 下的 dubbo-admin-0.0.1-SNAPSHOT.jar

   **注意：**如果zookeeper服务未启动，就会报错连接失败，并处于监听状态，再次启动zookeeper即可。

   ```cmd
   java -jar dubbo-admin-0.0.1-SNAPSHOT.jar
   ```

5. 启动成功后，访问管理页面：https://localhost:7001

   默认账号密码：root/root

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201008122329847.png" alt="image-20201008122329847" style="zoom:30%;" />

#### 9.4 Dubbo+Zookeeper+SpringBoot

1. 创建 consumer-server 和 provider-server 两个项目

2. 导入 dubbo 和 zookeeper 依赖

   ```xml
   <!--Dubbo-->
   <dependency>
     <groupId>org.apache.dubbo</groupId>
     <artifactId>dubbo-spring-boot-starter</artifactId>
     <version>2.7.3</version>
   </dependency>
   <!--zkclient-->
   <dependency>
     <groupId>com.github.sgroschupf</groupId>
     <artifactId>zkclient</artifactId>
     <version>0.1</version>
   </dependency>
   <!--zookeeper，日志会冲突-->
   <dependency>
     <groupId>org.apache.curator</groupId>
     <artifactId>curator-framework</artifactId>
     <version>2.12.0</version>
   </dependency>
   <dependency>
     <groupId>org.apache.curator</groupId>
     <artifactId>curator-recipes</artifactId>
     <version>2.12.0</version>
   </dependency>
   <dependency>
     <groupId>org.apache.zookeeper</groupId>
     <artifactId>zookeeper</artifactId>
     <version>3.4.14</version>
     <exclusions>
       <exclusion>
         <groupId>org.slf4j</groupId>
         <artifactId>slf4j-log4j12</artifactId>
       </exclusion>
     </exclusions>
   </dependency>
   ```

3. 先编写provider-server，在 application.yml 配置 dubbo

   ```properties
   server.port=8001
   
   # 服务应用名字
   dubbo.application.name=provider-server
   # 注册中心地址
   dubbo.registry.address=zookeeper://127.0.0.1:2181
   # 哪些服务要被注册
   dubbo.scan.base-packages=com.sugar.service
   ```

4. 编写 Service，并添加注解注册到注册中心

   ```java
   package com.sugar.service;
   
   import org.apache.dubbo.config.annotation.Service;
   import org.springframework.stereotype.Component;
   
   // Zookeeper：服务注册与发现
   // 使用了Dubbo后，尽量不使用@Service注解注册到Spring容器（防止与Spring容器注册的@Service弄混）
   // @Service 使其可以被Dubbo扫描到，在项目已启动就自动注册到Zookeeper注册中心
   @Service
   @Component
   public class TicketServiceImpl implements TicketService{
       @Override
       public String getTicket() {
           return "Sugar";
       }
   }
   ```

5. 访问 localhost:7100（dubbo-admin），查看服务（需要启动zookeeper，dubbo-admin和Springboot测试项目）

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20201008160140443.png" alt="image-20201008160140443" style="zoom:50%;" />

6. 然后编写 consumer-server，同样导入相同的依赖

7. 配置 consumer 的 application.yml

   ```properties
   server.port=8002
   
   # 消费者去哪里拿服务，需要暴露自己的名字
   dubbo.application.name=consumer-server
   # 注册中心的地址
   dubbo.registry.address=zookeeper://127.0.0.1:2181
   ```

8. 编写一个与provier-server中相同的接口，但不需要实现

   ```java
   package com.sugar.service;
   
   public interface TicketService {
       public String getTicket();
   }
   ```

9. 编写 UserService，调用注册中心的方法

   ```java
   package com.sugar.service;
   
   import org.apache.dubbo.config.annotation.Reference;
   import org.springframework.stereotype.Service;
   
   @Service  // 放到Spring容器中，非Dubbo
   public class UserService {
   
       // 要拿到 provide-server 提供的 Ticket，要去注册中心拿到服务
       @Reference  // 引用，Pom坐标，可以定义路径相同的接口名
       TicketService ticketService;
   
       public void buyTicket() {
           String ticket = ticketService.getTicket();
           System.out.println("在注册中心拿到 => " + ticket);
       }
   }
   ```

10. 测试

    ```java
    package com.sugar;
    
    import com.sugar.service.UserService;
    import org.junit.jupiter.api.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.context.SpringBootTest;
    
    @SpringBootTest
    class ConsumerServerApplicationTests {
    
    	@Autowired
    	UserService userService;
    
    	@Test
    	void contextLoads() {
    		userService.buyTicket();
    	}
    }
    ```

    

##### 总结

前提：Zookeeper已开启

1. 提供者提供服务
   1. 导入依赖
   2. 配置注册中心的地址，以及服务发现名，和要扫描的包
   3. 在想要被注册的服务上面，增加一个注册 @Service
2. 消费者如何消费
   1. 导入依赖
   2. 配置注册中心的地址，配置自己的服务名
   3. 从远程注入服务 @Reference







