package factory.abstractfactory;

public class XiaomiFactory implements IProductFactory{
    @Override
    public IPhoneProduct iphoneProduct() {
        return new XiaomiPhone();
    }

    @Override
    public IRouterProduct irouterProduct() {
        return new XiaomiRouter();
    }
}
