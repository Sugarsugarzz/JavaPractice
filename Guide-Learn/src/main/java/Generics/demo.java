package Generics;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// 证明编译时类型擦除的存在
public class demo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        List<Integer> list = new ArrayList<>();

        list.add(12);
        // 直接添加String类型的会报错
//        list.add("a");

        // 但通过反射可以添加
        list.getClass().getDeclaredMethod("add", Object.class).invoke(list, "haha");
        System.out.println(list);
    }
}
