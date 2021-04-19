package demo05;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) throws Exception {
        SmsService s = (SmsService) ProxyUtil.getProxy(new SmsServiceImpl());
        s.sms("a");
    }
}

interface SmsService {
    void sms(String message);
}

class SmsServiceImpl implements SmsService {
    @Override
    public void sms(String message) {
        System.out.println("Send sms:" + message);
    }
}

class SmsServiceProxy implements InvocationHandler {

    private final Object target;

    public SmsServiceProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before");
        Object invoke = method.invoke(target, args);
        System.out.println("After");
        return invoke;
    }
}

class ProxyUtil {
    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new SmsServiceProxy(target));
    }
}