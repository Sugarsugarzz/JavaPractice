package com.sugar.heap;

import java.util.Random;

public class OOMDemo {
    public static void main(String[] args) {
        String s = "hello";
        System.out.println(Runtime.getRuntime().maxMemory());
        System.out.println(Runtime.getRuntime().totalMemory());
        while (true) {
            s += new Random().nextInt(999999999) + new Random().nextInt(999999999);
        }

    }
}
