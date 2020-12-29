package Generics;

// 泛型接口
public interface Generator<T> {

    public T method();

}

// 实现泛型接口，不指定类型
class GeneratorImpl1<T> implements Generator<T> {
    @Override
    public T method() {
        return null;
    }
}

// 实现泛型接口，指定类型
class GeneratorImpl2<T> implements Generator<String> {
    @Override
    public String method() {
        return "haha";
    }
}