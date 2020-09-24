package com.sugar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

// 如果想 DIY 一些定制化的功能，只要写这个组件，然后将它交给SpringBoot，然后就会自动装配
// 扩展 SpringMVC
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

//    // ViewResolver  实现了视图解析器接口的类，就可以将其看做视图解析器
//    @Bean
//    public ViewResolver myViewResolver() {
//        return new MyViewResolver();
//    }
//
//    // 自定义一个自己的视图解析器 MyViewResolver
//    public static class MyViewResolver implements ViewResolver {
//        @Override
//        public View resolveViewName(String s, Locale locale) throws Exception {
//            return null;
//        }
//    }

    // 视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 访问 /sugar，跳转到 test.html
        registry.addViewController("/sugar").setViewName("test");
    }
}


