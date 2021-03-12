package singleton;

import java.lang.reflect.Constructor;

/**
 * 枚举单例
 * enum 是什么？本身也是一个 Class 类
 */
public enum EnumSingle {

    INSTANCE;

    public EnumSingle getInstance() {
        return INSTANCE;
    }
}

class Test {
    public static void main(String[] args) throws Exception {
        EnumSingle instance1 = EnumSingle.INSTANCE;
        EnumSingle instance2 = EnumSingle.INSTANCE;

        System.out.println(instance1);
        System.out.println(instance2);

        // NoSuchMethodException 没有无参构造方法
//        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(null);
        // 经过反编译后，发现Enum 构造器有两个参数
        // 运行后，彻底说明，反射不能破坏枚举的单例  Cannot reflectively create enum objects
        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        EnumSingle instance3 = declaredConstructor.newInstance();
        System.out.println(instance3);
    }
}
