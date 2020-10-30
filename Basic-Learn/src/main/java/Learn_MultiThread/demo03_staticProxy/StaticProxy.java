package Learn_MultiThread.demo03_staticProxy;

/*
静态代理模式总结：
    真实对象和代理对象都要实现同一个接口
    代理对象要代理真实角色

好处：
    - 代理对象可以做很多真实对象做不了的事情
    - 真实对象专注做自己的事情
 */
public class StaticProxy {

    public static void main(String[] args) {

        // 对比一下，理解静态代理（底部实现原理）
        // Thread代理Runnable接口
        new Thread( () -> System.out.println("我爱你")).start();
        new WeddingCompany(new You()).HappyMarry();

        // 将下面两句简化上上面一句
//        WeddingCompany weddingCompany = new WeddingCompany(new You());
//        weddingCompany.HappyMarry();
    }
}

interface Marry {
    void HappyMarry();
}

// 真实角色
class You implements Marry {

    @Override
    public void HappyMarry() {
        System.out.println("Sugar 要结婚了");
    }
}

// 代理角色，帮助结婚
class WeddingCompany implements Marry {

    // 代理是哦--> 真实目标劫色
    private Marry target;

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void HappyMarry() {
        before();
        this.target.HappyMarry();  // 这就是真实对象
        after();
    }

    private void after() {
        System.out.println("结婚后，收尾款");
    }

    private void before() {
        System.out.println("结婚前，布置现场");
    }
}

