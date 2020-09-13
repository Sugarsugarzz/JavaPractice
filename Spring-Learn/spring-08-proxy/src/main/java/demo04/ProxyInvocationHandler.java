package demo04;

import demo03.Rent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 用这个类，自动生成代理类
public class ProxyInvocationHandler implements InvocationHandler {


    // 被代理的接口
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    // 生成代理类
    public Object getProxy() {
        // 三个固定参数（ClassLoader，代理的接口，InvocationHandler实现类）
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    // 处理代理实例，并返回结果
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 动态代理的本质，就是使用反射机制实现
        log(method.getName());  // 通过反射，获取方法名
        Object result = method.invoke(target, args);

        return result;
    }

    public void log(String msg) {
        System.out.println("执行了 " + msg + " 方法");
    }
}
