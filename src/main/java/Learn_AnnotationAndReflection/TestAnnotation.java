package Learn_AnnotationAndReflection;

import java.util.ArrayList;
import java.util.List;

// 什么是注解
public class TestAnnotation extends Object{

    // @Override 重写的注解
    @Override
    public String toString() {
        return super.toString();
    }

    // @Deprecated 已过时的方法，不推荐使用，存在更好的方式
    @Deprecated
    public static void test() {
        System.out.println("Deprecated");
    }

    // @SuppressWarnings 镇压警告
    @SuppressWarnings("all")
    public void test2() {
        List list = new ArrayList();
    }


    public static void main(String[] args) {
        test();
    }


}
