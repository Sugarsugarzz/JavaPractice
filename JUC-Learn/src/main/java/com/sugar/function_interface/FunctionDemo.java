package com.sugar.function_interface;

import java.util.function.Function;

/**
 * Function 函数型接口：有一个输入参数，有一个输出
 *  只要是函数式接口，都可以用lambda表达式简化
 */
public class FunctionDemo {
    public static void main(String[] args) {
        // 工具类：输出输入的值
//        Function<String, String> function = new Function<String, String>() {
//            @Override
//            public String apply(String o) {
//                return o;
//            }
//        };

        // lambda简化写法
        Function function = (o) -> {return o;};

        System.out.println(function.apply("aaa"));
    }
}
