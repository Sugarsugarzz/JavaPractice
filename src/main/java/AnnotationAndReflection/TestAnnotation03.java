package AnnotationAndReflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 自定义注解
public class TestAnnotation03 {

    // 注解可以显式赋值，如果没有默认值，就必须给注解赋值
    @MyAnnotation2(name="Sugar")  // 注解参数可以不按照顺序写
    public void test() {}

    @MyAnnotation3("SUGAR")  // 如果只有一个参数且命名为value，则可以不写参数名
    public void test2() {}
}

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation2 {
    // 注解的参数： 参数类型 + 参数名(); （不是方法，是参数，必须得有括号）
    String name() default "";
    int age() default 0;
    int id() default -1;  // 如果默认值为-1，代表不存在

    String[] schools() default {"UCAS", "UK"};
}

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation3 {
    // 如果只有一个参数，建议用value命名
    String value();
}
