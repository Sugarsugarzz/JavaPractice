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
