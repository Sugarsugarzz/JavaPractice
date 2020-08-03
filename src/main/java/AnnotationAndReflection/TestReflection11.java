package AnnotationAndReflection;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

// 通过反射获取泛型
public class TestReflection11 {

    public void test01(Map<String, User> map, List<User> list) {
        System.out.println("test01");
    }

    public Map<String, User> test02() {
        System.out.println("test02");
        return null;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Method method = TestReflection11.class.getMethod("test01", Map.class, List.class);
        // 获得泛型的参数类型
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        for (Type genericParameterType: genericParameterTypes) {
            System.out.println("#" + genericParameterType);
            // 是否为结构化参数类型
            if (genericParameterType instanceof ParameterizedType) {
                // 获得真实信息
                Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println(actualTypeArgument);
                }
            }
        }


        Method method2 = TestReflection11.class.getMethod("test02", null);
        Type genericReturnType = method2.getGenericReturnType();
        if (genericReturnType instanceof ParameterizedType) {
            // 获得真实信息
            Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                System.out.println(actualTypeArgument);
            }
        }
    }
}
