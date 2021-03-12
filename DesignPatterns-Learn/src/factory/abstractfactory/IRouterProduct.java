package factory.abstractfactory;

// 路由器产品接口
public interface IRouterProduct {

    void start();
    void shutdown();
    void openWifi();
    void setting();
}
