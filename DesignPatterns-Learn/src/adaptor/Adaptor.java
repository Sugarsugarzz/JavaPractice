package adaptor;

/**
 * 适配方法
 *  1. 继承（类适配器，单继承）
 *  2. 组合（对象适配器：常用）
 */
// 适配器，需要连接USB和网线
// 1. 继承方法
public class Adaptor extends Adaptee implements NetToUsb{
    @Override
    public void handlerRequest() {
        super.request();  // 可以上网
    }
}
