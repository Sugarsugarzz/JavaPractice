package com.sugar.function_interface;

import java.util.function.Supplier;

/**
 * Supplier 供给型接口：没有参数，只有返回值
 */
public class SupplierDemo {
    public static void main(String[] args) {
//        Supplier<String> supplier = new Supplier<String>() {
//            @Override
//            public String get() {
//                return "haha";
//            }
//        };

        Supplier<String> supplier = () -> "hahaa";

        System.out.println(supplier.get());
    }
}
