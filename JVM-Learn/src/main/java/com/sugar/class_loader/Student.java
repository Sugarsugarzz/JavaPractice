package com.sugar.class_loader;

public class Student {
    /**
     * 双亲委派
     * BOOT->EXT->APP
     */
    public String toString() {
        return "Hello";
    }

    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student.toString());
        // 确定Student是否在用户加载器  ->  APP
        System.out.println(student.getClass().getClassLoader());
    }
}
