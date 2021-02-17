package com.sugar.function_interface;

import java.util.function.Consumer;

/**
 * Consumer 消费型接口：只有输入，没有返回值
 */
public class ConsumerDemo {
    public static void main(String[] args) {
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String o) {
//                System.out.println(o);
//            }
//        };

        Consumer<String> consumer = System.out::println;

        consumer.accept("haha");
    }
}
