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

### 4. 员工管理系统 实践

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

