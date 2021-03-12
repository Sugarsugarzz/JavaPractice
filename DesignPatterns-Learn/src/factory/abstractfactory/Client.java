package factory.abstractfactory;

public class Client {
    public static void main(String[] args) {

        System.out.println("=======小米系列=======");
        XiaomiFactory xiaomiFactory = new XiaomiFactory();
        IPhoneProduct iPhoneProduct = xiaomiFactory.iphoneProduct();
        iPhoneProduct.callup();

        IRouterProduct iRouterProduct = xiaomiFactory.irouterProduct();
        iRouterProduct.openWifi();

    }
}
