package Generics;

public class GenericMethod {

    // 泛型方法
    public static < E > void printArray(E[] inputArray) {
        for (E element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }

    public static void main(String[] args) {

        // 创建不同类型数组
        Integer[] intArray = {1, 2, 3};
        String[] stringArray = {"haha", "hehe", "xixi"};
        printArray(intArray);
        printArray(stringArray);

        String s = "haha";
        System.out.println(s.hashCode());
    }
}
