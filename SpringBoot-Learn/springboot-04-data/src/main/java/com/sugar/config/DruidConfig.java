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
