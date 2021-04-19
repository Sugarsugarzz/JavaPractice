package dynamicProxyCglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB动态代理
 *  <dependency>
 *   <groupId>cglib</groupId>
 *   <artifactId>cglib</artifactId>
 *   <version>3.3.0</version>
 *  </dependency>
 *
 * 更加灵活，原始类无需实现任何接口。
 */
public class Test {
    public static void main(String[] args) {
        SmsService proxy = (SmsService) ProxyUtil.getProxy(SmsService.class);
        proxy.send("hehe");
    }
}

class SmsService {
    public String send(String message) {
        System.out.println("Send:" + message);
        return message;
    }
}

class ProxyMethodInterceptor implements MethodInterceptor {

    /**
     * @param o           被代理的对象
     * @param method      被拦截的方法
     * @param args        方法入参
     * @param methodProxy 用于调用原始方法
     */
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before");
        Object result = methodProxy.invokeSuper(o, args);
        System.out.println("After");
        return result;
    }
}

class ProxyUtil {
    public static Object getProxy(Class<?> clazz) {
        // 创建动态代理增强类
        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());
        // 设置被代理类
        enhancer.setSuperclass(clazz);
        // 设置方法拦截器
        enhancer.setCallback(new ProxyMethodInterceptor());
        // 创建代理类
        return enhancer.create();
    }
}