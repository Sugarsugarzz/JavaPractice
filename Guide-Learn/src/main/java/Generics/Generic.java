package Generics;

// 泛型类
public class Generic<T> {

    private T key;

    public Generic(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public static void main(String[] args) {
        Generic hahah = new Generic("hahah");
        System.out.println(hahah.getKey());
        System.out.println(hahah.getKey().getClass().getName());

        Generic generic = new Generic(1);
        System.out.println(generic.getKey());
        System.out.println(generic.getKey().getClass().getName());
    }
}

