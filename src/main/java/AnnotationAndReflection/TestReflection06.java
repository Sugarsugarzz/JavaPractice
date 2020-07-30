package AnnotationAndReflection;

// 测试类什么时候会初始化
public class TestReflection06 {
    static {
        System.out.println("Main类被加载");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        // 1、主动引用
//        Son son = new Son();

        // 反射也会产生主动引用
//        Class.forName("AnnotationAndReflection.Son");

        // 不会产生类的引用的方法
//        System.out.println(Son.b);  // 通过子类调用父类的静态方法或变量，并不会加载子类

//        Son[] array = new Son[5];  // 只是空间命名，并不会加载类

//        System.out.println(Son.M);  // 调用常量，并不会加载父类和子类
    }
}

class Father {
    static int b = 2;
    static {
        System.out.println("父类被加载");
    }
}

class Son extends Father {
    static {
        System.out.println("子类被加载");
        m = 300;
    }
    static int m = 100;
    static final int M = 1;
}
