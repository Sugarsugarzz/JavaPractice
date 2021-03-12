package adaptor;

// 客户端类：想上网，插不上网线
public class Computer {

    // 需要连接上转接器才能上网
    public void net(NetToUsb adaptor) {
        // 上网的具体实现，找一个转接头
        adaptor.handlerRequest();
    }
}
