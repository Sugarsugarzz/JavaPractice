package adaptor;

/**
 * 适配方法
 *  1. 继承（类适配器，单继承）
 *  2. 组合（对象适配器：常用）
 */
// 适配器，需要连接USB和网线
// 1. 组合方法
public class Adaptor2 implements NetToUsb{

    private Adaptee adaptee;

    public Adaptor2(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void handlerRequest() {
        adaptee.request();  // 可以上网
    }
}
