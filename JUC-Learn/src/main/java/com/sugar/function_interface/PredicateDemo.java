package com.sugar.function_interface;

import java.util.function.Predicate;

/**
 * Predicate 断定型接口：有一个输入参数，返回值只能是布尔值。
 */
public class PredicateDemo {
    public static void main(String[] args) {
        // 判断字符串是否为空
//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String o) {
//                return o.isEmpty();
//            }
//        };

        Predicate<String> predicate = String::isEmpty;

        System.out.println(predicate.test("haha"));
    }
}
