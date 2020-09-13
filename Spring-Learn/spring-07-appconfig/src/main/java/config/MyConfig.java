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
