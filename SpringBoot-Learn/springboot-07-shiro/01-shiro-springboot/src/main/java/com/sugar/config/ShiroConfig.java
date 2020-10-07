package com.sugar.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

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
    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }

    // 整合 ShiroDialect：用来整合 shiro thymeleaf
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }
}
