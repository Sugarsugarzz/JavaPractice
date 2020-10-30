package Learn_AnnotationAndReflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// 获取运行类的信息
public class TestReflection08 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        Class c1 = Class.forName("Learn_AnnotationAndReflection.User");

        User user = new User();
        c1 = user.getClass();

        // 获得类的名字
        System.out.println(c1.getName());  // 获得包名 + 类名
        System.out.println(c1.getSimpleName());  // 获得类名

        // 获得类的属性
        Field[] fields = c1.getFields();  // 只能找到public属性
//        for (Field field: fields) {
//            System.out.println(field);
//        }

        fields = c1.getDeclaredFields();  // 可以找到全部属性
        for (Field field: fields) {
            System.out.println(field);
        }

        // 获得指定属性的值
        Field name = c1.getDeclaredField("name");
        System.out.println(name);

        // 获得类的方法
        Method[] methods = c1.getMethods();  // 获得本类机器父类的全部public方法（不包括private方法）
        for (Method method: methods) {
            System.out.println("正常的：" + method);
        }
        methods = c1.getDeclaredMethods();  // 获得本类的所有方法（包括private方法）
        for (Method method: methods) {
            System.out.println("getDeclaredMethods：" + method);
        }

        // 获得指定方法
        Method getName = c1.getMethod("getName", null);
        Method setName = c1.getMethod("setName", String.class);
        System.out.println(getName);
        System.out.println(setName);

        // 获得指定的构造器
        System.out.println("============================================");
        Constructor[] constructors = c1.getConstructors();
        for (Constructor constructor: constructors) {
            System.out.println("正常的：" + constructor);
        }
        constructors = c1.getDeclaredConstructors();
        for (Constructor constructor: constructors) {
            System.out.println("getDeclaredConstructors" + constructor);
        }

        // 获得指定的构造器
        Constructor constructor = c1.getConstructor(String.class, int.class, int.class);
        System.out.println("指定的" + constructor);

    }
}
