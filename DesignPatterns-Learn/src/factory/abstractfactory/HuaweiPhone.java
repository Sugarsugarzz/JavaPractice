package factory.abstractfactory;

// 华为手机
public class HuaweiPhone implements IPhoneProduct {
    @Override
    public void start() {
        System.out.println("开启华为手机");
    }

    @Override
    public void shutdown() {
        System.out.println("关闭华为手机");
    }

    @Override
    public void callup() {
        System.out.println("华为打电话");
    }

    @Override
    public void sendSMS() {
        System.out.println("华为发短信");
    }
}
