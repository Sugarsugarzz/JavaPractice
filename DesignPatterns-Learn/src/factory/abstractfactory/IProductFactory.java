package factory.abstractfactory;

// 抽象产品工厂
public interface IProductFactory {

    // 生产手机
    IPhoneProduct iphoneProduct();

    // 生产路由器
    IRouterProduct irouterProduct();
}
