package AnnotationAndReflection;

import java.lang.annotation.*;

// 测试元注解
@MyAnnotation
public class TestAnnotation02 {

    @MyAnnotation
    public void test() {

    }
}

// 定义一个注解
// @Target 表示我们的注解可以用在哪些地方上
@Target(value = {ElementType.METHOD, ElementType.TYPE})  // 能够定义在方法和类上
// @Retention 表示我们的注解在什么地方还有效
// runtime > class > sources
@Retention(value = RetentionPolicy.RUNTIME)
// @Documented 表示是否将我们的注解生成在JavaDoc中
@Documented
// @Inherited 子类可以继承父类的注解
@Inherited
@interface MyAnnotation {

}
