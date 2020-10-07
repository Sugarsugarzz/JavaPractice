package com.sugar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // 配置Swagger的 Docket 的 Bean实例
    @Bean
    public Docket docket(Environment environment) {

        // 设置要显示Swagger的环境
        Profiles profiles = Profiles.of("dev", "test");
        // 通过监听，判断是否项目处在设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 配置分组
                .groupName("sugar1")
                // 是否开启Swagger
                .enable(flag)
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
