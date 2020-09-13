# Spring

### 1.1 简介

- 2002年，首次推出 Spring 框架的雏形：interface21框架
- Spring 框架以 interface21 框架为基础，经过重新设计并丰富内涵，于2004年3月24日发布了1.0正式版。
- **Rod Johnson**，Spring Framework 创始者，悉尼大学音乐学博士（震惊）。
- Spring理念：**使现有的技术更加容易使用**，本身是一个大杂烩，整合了现有的技术框架。



- SSH：Struct2 + Spring + Hibernate
- SSM：SpringMVC + Spring + MyBatis



官网：https://spring.io/projects/spring-framework#overview

官方下载地址：http://repo.spring.io/release/org/springframework/spring

GitHub：https://github.com/spring-projects/spring-framework

Maven：导一个最大的，maven会自动将其他的导入。

```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.2.0.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.2.0.RELEASE</version>
</dependency>

```

#### 1.2 优点

- Spring 是一个开源、免费的框架（容器）。
- 轻量级、非入侵式（无需修改代码）的框架。
- **控制反转（IOC），面向切面编程（AOP）**。
- 支持h事务的处理，对框架整合的支持。

总结：**Spring 就是一个轻量级的控制反转和面向切面编程的框架。**

#### 1.3 组成

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200911123521147.png" alt="image-20200911123521147" style="zoom:35%;" />

#### 1.4 拓展

在 Spring 的官网有这个介绍：现代化的 Java 开发，就是基于 Spring 的开发。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200911123837881.png" alt="image-20200911123837881" style="zoom:30%;" />

- Spring Boot
  - 快速开发的脚手架
  - 基于 SpringBoot 可以快速的开发单个微服务
  - **约定大于配置**
- Spring Cloud
  - SpringCloud 是基于 SpringBoot 实现的

**学习 SpringBoot 的前提，需要完全掌握 Spring 及 SpringMVC（承上启下）。**

**弊端**：Spring 发展太久后，违背了原来的理念！配置繁琐，”配置地域“！

### 2. IOC 理论推导

之前：

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200911151707944.png" alt="image-20200911151707944" style="zoom:20%;" />

IOC：

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200911151729551.png" alt="image-20200911151729551" style="zoom:30%;" />

见 **spring-01-ic1** Demo，理解 IOC 的思想。
手动在 Service 创建多个 UserDaoXXX 对象，不如被动接收对象，交控制权交给用户。

1. UserDao接口
2. UserDaoImlp实现类
3. UserService业务接口
4. UserServiceImpl业务实现类

在之前的业务中，用户的需求可能会影响原来的代码，需要根据用户的需求去修改源代码。
如果程序代码量十分大，修改一次的成本十分昂贵。

使用一个 Set 接口实现。（思想上，已经发生革命性变化！）

```java
private UserDao userDao;

// 利用set进行动态实现指导注入！
public void setUserDao(UserDao userDao) {
  this.userDao = userDao;
}
```

- 之前，程序是主动创建对象。控制权在程序员手上。
- 使用了 Set 注入后，程序不再具有主动性，而是变成了被动的接受对象！

IOC（控制反转） 这种思想，从本质上解决了问题，程序员不再去管理对象的创建，系统的耦合性大大降低，可以更加专注在业务的实现上。（这是 IOC 的原型）

#### IOC 本质

控制反转IoC（Inversion of Control），是一种设计思想，DI（依赖注入）是实现 IoC 的一种方法，也有人认为 DI 只是 IoC 的另一种说法。没有 IoC 的程序中，使用面向对象编程，对象的创建与对象间的依赖关系完全硬编码在陈谷中，对象的创建由程序自己控制，控制反转后将对象的创建转移给第三方。所以个人理解，控制反转就是：**获得依赖对象的方式反转了**。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200911152228756.png" alt="image-20200911152228756" style="zoom:50%;" />

采用 XML 方式配置 Bean 的时候，Bean 的定义信息是和实现分离的，而采用注解的方式可以把两者合为一体，Bean 的定义信息直接以注解的形式定义在实现类中，从而达到零配置的目的。

**控制反转是一种通过描述（XML或注释），并通过第三方去产生或获取特定对象的方式。在 Spring 中实现控制反转的是 IoC 容器，其实现方法是依赖注入（Dependency Injaction， DI）。**

### 3. HelloSpring

1. 创建实体类

   ```java
   package pojo;
   
   public class Hello {
       private String str;
   
       public String getStr() {
           return str;
       }
   
       public void setStr(String str) {
           this.str = str;
       }
   
       @Override
       public String toString() {
           return "Hello{" +
                   "str='" + str + '\'' +
                   '}';
       }
   }
   ```

2. 创建 Spring 配置文件 beans.xml（applicationContext.xml），注册实体类

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
                           https://www.springframework.org/schema/beans/spring-beans.xsd">
   
       <!--使用 Spring 来创建对象，在 Spring 中这些都称为 Bean
           类型 变量名 = new 类型();
           Hello hello = new Hello();
       Spring中：
           bean = 对象   ：   new Hello();
           id：变量名
           class：new 的对象
           property：给对象中的属性设置一个值
               value：基本类型或String
               ref：对象
       -->
       <bean id="hello" class="pojo.Hello">
           <property name="str" value="Spring"/>
       </bean>
       <!--    -->
       <bean id="mysqlImpl" class="dao.UserDaoMysqlImpl" />
       <bean id="oracleImpl" class="dao.UserDaoOracleImpl" />
       <bean id="UserServiceImpl" class="service.UserServiceImpl">
           <property name="userDao" ref="mysqlImpl"/>
       </bean>
   
   </beans>
   ```

3. 测试

   ```java
   public static void main(String[] args) {
     // 获取 Spring 上下文对象
     ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
     // 对象都在 Spring 中管理了，使用直接从里面取出即可！
     Hello hello = (Hello) context.getBean("hello");
     System.out.println(hello.toString());
   }
   ```

#### 思考问题

- Hello 对象是由谁创建的？

  hello 对象是由 Spring 创建的。

- Hello 对象的属性是怎么设置的？

  hello 对象的属性是由 Spring 容器设置的。

这个过程就叫**控制反转**。

**控制**：谁来控制对象的创建，传统应用程序的对象是由程序本身控制创建的，使用 Spring 后，对象是由 Spring 来创建的。

**反转**：程序本身不创建对象，而变成被动的接收对象。

**依赖注入**：就是利用实体类的 set 方法进行注入的。

IOC 是一种编程思想，由主动的编程编程被动的接收。

可以通过 **newClassPathXmlApplicationContext** 浏览一下底层源码。

**所以到目前为止，就彻底地不用再到程序中去改动了，要实现不同的操作，只需要在 xml 配置文件中进行修改。**

**所谓的 IoC：对象由 Spring 来创建，管理，装配！**

### 4. IOC 创建对象的方式

测试实体类 User：

```java
package pojo;

public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("name=" + name);
    }
}
```

**无参和有参的创建方式：**

1. Spring 使用无参构造方法创建对象（默认）

2. 如果要让 Spring 使用有参构造创建对象

   1. 下标赋值

      ```xml
      <!--第一种：下标赋值-->
      <bean id="user" class="pojo.User">
      	  <constructor-arg index="0" value="Sugar"/>
      </bean>
      ```

   2. 根据参数类型创建（不推荐！）

      ```xml
      <!--第二种：根据参数类型创建（多个相同的类型的难搞，不建议使用！）-->
      <bean id="user" class="pojo.User">
      	  <constructor-arg type="java.lang.String" value="Sugar" />
      </bean>
      ```

   3. 直接通过参数名（建议）

      ```xml
      <!--第三种：直接通过参数名（最直观）-->
      <bean id="user" class="pojo.User">
      	  <constructor-arg name="name" value="Sugar"/>
      </bean>
      ```

**总结**：在配置文件 ApplicationContext.xml 加载的时候，容器中管理的对象就已经被初始化了！且这些对象都属于同一个对象！

### 5. Spring 配置

#### 5.1 alias 别名

```xml
<!--别名：添加了别名，也可以使用别名获取到对象-->
<alias name="user" alias="userAlias"/>
```

#### 5.2 Bean 的配置

```xml
<!--
    id：bean 的唯一标识符，相当于对象名
    class：bean 对象所对应的全限定名
    userT：别名，同 alias，但name属性可以取多个别名，以 , 或空格或分号分开
    -->
<bean id="user2" class="pojo.User2" name="userT,ut">
	  <property name="name" value="Sugar"/>
</bean>
```

#### 5.3 import

一般用于团队开发使用，可以将多个配置文件，导入合并为一个。

假设，项目中有多个人开发，这三个人负责不同的类开发，不同的类需要注册在不同的 bean 中，可以利用 import 将所有的 beans 合并为一个总的。

- beans1.xml

- beans2.xml

- beans3.xml

- applicationContext.xml

  ```xml
  <import resource="beans2.xml"/>
  <import resource="beans2.xml"/>
  <import resource="beans3.xml"/>
  ```

使用的时候，直接使用 applicationContext.xml 即可。（**需要避免重名**）

### 6.  依赖注入（DI）

#### 6.1 构造器注入

上面已经写过了。

#### 6.2 Set方式注入【重点】

- 依赖注入：Set注入！
  - 依赖：Bean对象的创建都依赖于容器
  - 注入：Bean对象的所有属性，由容器啦注入！

【环境搭建】

1. 复杂对象

   ```java
   public class Address {
       private String address;
   }
   ```

2. 真实测试对象

   ```java
   public class Student {
       private String name;
       private Address address;
       private String[] books;
       private List<String> hobbies;
       private Map<String, String> card;
       private Set<String> games;
       private  String wife;
       private Properties info;
   }
   ```

3. applicationContext.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
                           https://www.springframework.org/schema/beans/spring-beans.xsd">
   
   
       <bean id="student" class="pojo.Student">
           <!--第一种，普通值注入，value-->
           <property name="name" value="Sugar" />
       </bean>
   
   </beans>
   ```

4. 测试

   ```java
   public class MyTest {
       public static void main(String[] args) {
   
           ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
           Student student = (Student) context.getBean("student");
   
           System.out.println(student.toString());
   
       }
   }
   ```

   **多类型注入方法**

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
                           https://www.springframework.org/schema/beans/spring-beans.xsd">
   
   
       <bean id="address" class="pojo.Address">
           <property name="address" value="北京" />
       </bean>
   
       <bean id="student" class="pojo.Student">
           <!--第一种，普通值注入，value-->
           <property name="name" value="Sugar" />
           <!--第二种，Bean注入对象类，ref-->
           <property name="address" ref="address" />
           <!--数组注入，ref-->
           <property name="books">
               <array>
                   <value>Java</value>
                   <value>Python</value>
                   <value>C++</value>
               </array>
           </property>
           <!--list-->
           <property name="hobbies">
               <list>
                   <value>敲代码</value>
                   <value>看电影</value>
               </list>
           </property>
           <!--Map-->
           <property name="card">
               <map>
                   <entry key="身份证" value="1313131" />
                   <entry key="手机号" value="1300123123" />
               </map>
           </property>
           <!--Set-->
           <property name="games">
               <set>
                   <value>Dota2</value>
                   <value>csgo</value>
               </set>
           </property>
           <!--null-->
           <property name="wife">
               <null/>
           </property>
           <!--Properties-->
           <property name="info">
               <props>
                   <prop key="学号">2019262</prop>
                   <prop key="姓名">小明</prop>
               </props>
           </property>
       </bean>
   
   </beans>
   ```

#### 6.3 拓展方式注入

可以使用 **p命名空间** 和 **c命名空间** 进行注入。

需在 applicationContext.xml 头部引入。

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--p命名空间注入：可以直接注入属性的值：property-->
    <bean id="user" class="pojo.User" p:name="Sugar" p:age="18" />

    <!--c命名空间注入：通过构造器注入：construct-args -->
    <bean id="user2" class="pojo.User" c:name="Sugar1" c:age="18" />
</beans>
```

**注意**：

- p命名和c命名空间不能直接导入，需要导入 xml 约束。

#### 6.4 Bean 的作用域

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200913141915735.png" alt="image-20200913141915735" style="zoom:40%;" />

1. 单例模式 singleton （Spring默认机制）

   所有 Bean 初始化为一个对象。

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200913142226877.png" alt="image-20200913142226877" style="zoom:30%;" />

   ```xml
   <bean id="user2" class="pojo.User" c:name="Sugar1" c:age="18" scope="singleton"/>
   ```

2. 原型模式 prototype：每次从容器中get的时候，都会产生一个新对象

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200913142243480.png" alt="image-20200913142243480" style="zoom:30%;" />

   ```xml
   <bean id="user2" class="pojo.User" c:name="Sugar1" c:age="18" scope="prototype"/>
   ```

3. 其余的 request、session、application，这些只能在 web 开发中使用。

### 7. Bean 的自动装配

- 自动装配是 Spring 满足 Bean 依赖的一种方式
- Spring 会在上下文中自动寻找，并自动给 Bean 装配属性

在 Spring 中有三种自动装配的方式

1. 在 xml 中显式的配置
2. 在 Java 中显式配置
3. 隐式的自动装配Bean【重要】

#### 7.1 测试

环境搭建：一个人有两个宠物

#### 7.2 ByName自动装配

```xml
<bean id="cat" class="pojo.Cat"/>
<bean id="dog" class="pojo.Dog"/>
<!--
    byName：会自动在容器上下文中查找，和自己对象 set 方法后面的值对应的beanid（根据bean的id）
-->
<bean id="people" class="pojo.People" autowire="byName">
  <property name="name" value="Sugar"/>
  <property name="cat" ref="cat"/>
  <property name="dog" ref="dog"/>
</bean>
```

#### 7.3 ByType自动装配

```xml
<bean class="pojo.Cat"/>
<bean class="pojo.Dog"/>
<!--
    byType：会自动在容器上下文中查找，和自己对象属性类型相同的bean（根据bean的class）
-->
<bean id="people" class="pojo.People" autowire="byName">
  <property name="name" value="Sugar"/>
</bean>
```

**小结**

- byName的时候，需要保证所有bean的id唯一，并且这个bean需要和自动注入的属性的set方法的值一致
- byName的时候，需要保证所有bean的class唯一，并且这个bean需要和自动注入的属性的类型一致

#### 7.4 使用注解实现自动装配

jdk1.5支持的注解，Spring2.5就支持注解。

要使用注解的须知：

1. 导入约束（context约束）

2. 配置注解的支持（    <context:annotation-config/>）

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
                           https://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           https://www.springframework.org/schema/context/spring-context.xsd">
   
       <context:annotation-config/>
   </beans>
   ```

##### @Nullable

属性字段标记了这个注解，说明这个字段可以为null

##### @Autowired

直接在属性上使用即可！也可以在 set 方法上使用！

使用 Autowired，可以不用编写 set 方法了，前提是这个自动装配的属性在 IOC（Spring）容器中存在。

```java
// 如果显式定义了Autowired的required属性为false，说明这个对象可以为null，否则不允许为空
@Autowired(required = false)
private Cat cat;
```

##### @Qualifier

如果@Autowired 自动装配的环境比较复杂，自动装配无法通过一个注解完成的时候，可以使用 @Qualifier（value=""）去配合@Autowird的使用，指定唯一的一个注入对象。

```java
@Autowired
@Qualifier(value = "cat222")
private Cat cat;
```

```xml
<bean id="cat" class="pojo.Cat"/>
<bean id="cat222" class="pojo.Cat"/>
```

##### @Resource

```java
@Resource(name = "dog222")
private Dog dog;
```

```xml
<bean id="dog" class="pojo.Dog"/>
<bean id="dog222" class="pojo.Dog"/>
```

**小结**：

@Resource 和 @Autowired 的区别：

- 都是用来自动装配的，都可以放在属性字段上
- @Autowired 默认按byType的方式实现，找不到，再通过byName实现。要求这个对象在容器存在【常用】
- @Resource 默认通过byName的方式实现，如果找不到名字，则通过byType实现。都找不到，则报错。【常用】
- 执行熟悉怒不同：@Autowired 通过byType的方式实现

### 8. 使用注解开发

在Spring4之后，要使用注解开发，必须保证aop的包导入了。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200913151805366.png" alt="image-20200913151805366" style="zoom:30%;" />

在使用注解，需要导入context约束，增加注解的支持！

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                        https://www.springframework.org/schema/beans/spring-beans.xsd
                        http://www.springframework.org/schema/context
                        https://www.springframework.org/schema/context/spring-context.xsd">
    
    <!--开启注解支持-->
    <context:annotation-config/>

</beans>
```

#### 8.1 Bean

##### @Component

组件，放在类上，说明这个类被Spring管理了。就是Bean！

```java
@Component
public class User {
    public String name;
}
```

#### 8.2 属性注入

##### @Value

```java
@Component
public class User {
    // 相当于 <property name="name" value="Sugar"/>
    @Value("Sugar")
    public String name;
}
```

#### 8.3 衍生的注解

@Component有几个衍生注解，在web开发中，会按照mvc三层架构分层。	

	- 	dao【**@Repository**】
- 	service【**@Service**】
- 	controller【**@Controller**】

这四个注解的功能是一样的，都代表将某个类注册到Spring的容器中，装配Bean。

#### 8.4 自动装配

对于引用对象属性，使用这些注解。

##### @Autowired

##### @Nullable

##### @Resource

#### 8.5 作用域

同样放在类上。

```java
@Scope("singleton")
public class User {
}
```

#### 8.6 小结

XML 与注解：

- XML更加万能，适用于任何场合，维护简单方便。
- 注解不是自己类不能使用不了，维护相对复杂。

XML 与注解最佳实践：

- XML 用来管理 bean
- 注解只负责完成属性的注入
- 在使用的过程中，只需要注意一个问题，**必须让注解生效，就需要开启注解的支持**！

### 9. 使用 Java 的方式配置 Spring 

现在完全不使用 Spring 的xml 配置了，全权交给 Java 来做。

JavaConfig 是Spring 的一个子项目，在Spring4之后，它成为了一个核心功能。

这种纯 Java 的配置方式，在 SpringBoot 中随处可见。

- 实体类

  ```java
  package pojo;
  
  
  import org.springframework.beans.factory.annotation.Value;
  import org.springframework.stereotype.Component;
  
  // 这个注解，就是说明这个类被Spring接管，注册到了容器中
  @Component
  public class User {
  
      @Value("Sugar")
      private String name;
  
      @Override
      public String toString() {
          return "User{" +
                  "name='" + name + '\'' +
                  '}';
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  }
  ```

- 配置类

  ```java
  package config;
  
  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.ComponentScan;
  import org.springframework.context.annotation.Configuration;
  import org.springframework.context.annotation.Import;
  import pojo.User;
  
  // 这个也会被Spring托管，注册到容器中，因为它本来就是一个 @Component
  // @Configuration代表这是一个配置类，和之前的 applicationContext.xml 一样
  @Configuration
  // 可以采用 配置类+注册Bean 和 配置类+扫描包 两种方式
  // 如果扫描包了，就无需在类内注册Bean了
  @ComponentScan("pojo")
  // 相当于融合多个 applicationContext.xml
  @Import(MyConfig2.class)
  public class MyConfig {
  
      // 注册一个bean，相当于直接写的 <bean>
      // 方法名相当于，bean的id
      // 方法的返回值，相当于bean的class
      @Bean
      public User user() {
          return new User();
      }
  }
  ```

- 测试类

  ```java
  import config.MyConfig;
  import org.junit.Test;
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.annotation.AnnotationConfigApplicationContext;
  import pojo.User;
  
  public class MyTest {
  
      @Test
      public void test1() {
          // 如果完全使用配置类的方式去做，就只能通过 AnnotationConfig 上下文来获取容器，通过配置类的class对象加载
          ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
  
          User user = (User) context.getBean("user");
          System.out.println(user.getName());
      }
  }
  ```

### 10. 代理模式

学习代理模式的**目的**：这是 Spring AOP 的底层！【SpringAOP 和 SpringMVC】

代理模式的**分类**：

- 静态代理
- 动态代理

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200913165255096.png" alt="image-20200913165255096" style="zoom:40%;" />

#### 10.1 静态代理

**角色分析**：

- 抽象角色：一般会使用接口或者抽象类来解决
- 真实角色：被代理的角色
- 代理角色：代理真实角色，代理真实角色后，一般会做一些附属操作
- 客户：访问代理对象的人

**代码步骤**：

1. 接口

   ```java
   // 租房
   public interface Rent {
       public void rent();
   }
   ```

2. 真实角色

   ```java
   // 房东
   public class Host implements Rent{
       @Override
       public void rent() {
           System.out.println("房东要出租房子");
       }
   }
   ```

3. 代理角色

   ```java
   public class Proxy implements Rent{
   
       private Host host;
   
       public Proxy() {
       }
   
       public Proxy(Host host) {
           this.host = host;
       }
   
       @Override
       public void rent() {
           seeHouse();
           host.rent();
           hetong();
           fare();
       }
   
       // 看房
       public void seeHouse() {
           System.out.println("中介带看房");
       }
   
       // 签合同
       public void hetong() {
           System.out.println("签租赁合同");
       }
   
       // 收中介费
       public void fare() {
           System.out.println("收中介费");
       }
   }
   ```

4. 客户

   ```java
   public class Client {
       public static void main(String[] args) {
           // 房东要租房子
           Host host = new Host();
           // 代理，中介帮房东租房子，但代理角色会有一些附属操作
           Proxy proxy = new Proxy(host);
           // 不用面对房东，直接找中介即可
           proxy.rent();
       }
   }
   ```

   

代理模式的**好处**：

- 可以使真实角色的操作更加纯粹，不需要关注一些公共的业务
- 公共业务交给代理角色，实现了业务的分工
- 公共业务发生扩展的时候，方便集中管理

**缺点**：

- 一个真实角色就会产生一个代理角色，代码量会翻倍，开发效率降低。

#### 10.2 静态代理再理解

**代码**：spring-08-proxy-demo02，以UserService再举例实现静态代理。

在不修改原有代码的基础上，扩展一些功能。

- 改动原有的业务代码，在公司中是大忌

**AOP简单图示**：

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200913171710586.png" alt="image-20200913171710586" style="zoom:40%;" />

#### 10.3 动态代理

- 动态代理和静态代理角色一样
- 动态代理的代理类是动态生成的，不是直接写好的
- 动态代理分为两大类：**基于接口的动态代理**、**基于类的动态代理**
  - 基于接口  -- JDK动态代理【在这里使用】
  - 基于类：cglib
  - Java字节码：javassist

需要了解两个类：

- Proxy：提供创建动态代理类和实例的静态方法，也是由这些方法创建的所有动态代理类的超类
- InvocationHandler：由代理实现的调用处理程序实现的接口



动态代理的**好处**：

- 可以使真实角色的操作更加纯粹，不需要关注一些公共的业务
- 公共业务交给代理角色，实现了业务的分工
- 公共业务发生扩展的时候，方便集中管理
- 一个动态代理类代理的是一个接口，一般就是对应的一类业务
- 一个动态代理类可以代理多个类，只要是实现了同一个接口即可

**代码**：

spring-08-proxy的demo03和04.

### 11. AOP

#### 11.1 什么是AOP

**AOP（Aspect Oriented Programming）：面向切面编程**，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。AOP是OOP的延续，是软件开发中的一个热点，也是 Spring 框架中的一个重要内容，是函数式编程的一种衍生范型。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高开发的效率。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200913182320048.png" alt="image-20200913182320048" style="zoom:50%;" />

#### 11.2 AOP在Spring中的应用

**提供声明式事务：允许用户自定义切面**

- **横切关注点**：跨越应用程序多个模块的方法或功能，即，与业务逻辑无关，但是需要关注的部分，就是横切关注点。比如日志、安全、缓存、事务等。
- **切片（Aspect）**：横切关注点被模块化的特殊对象。即，是一个类。
- **通知（Advice）**：切面必须要完成的工作。即，是类中的一个方法。
- **目标（Target）**：被通知对象。
- **代理（Proxy）**：向目标对象应用通知之后创建的对象。
- **切入点（PointCut）**：切面通知执行的“地点”的定义。
- **连接点（JointPoint）**：与切入点匹配的执行点。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200913183007422.png" alt="image-20200913183007422" style="zoom:30%;" />

#### 11.3 使用Spring实现AOP





