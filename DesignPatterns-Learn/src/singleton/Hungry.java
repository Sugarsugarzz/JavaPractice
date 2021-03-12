package singleton;

/**
 * 饿汉式单例
 * 一上来就加载所有资源，会造成空间浪费 -> 懒汉式，需要时再加载
 */
public class Hungry {

    // 可能会浪费空间
    private byte[] data1 = new byte[1024*1024];
    private byte[] data2 = new byte[1024*1024];
    private byte[] data3 = new byte[1024*1024];
    private byte[] data4 = new byte[1024*1024];


    private Hungry() {

    }

    private final static Hungry HUNGRY = new Hungry();

    public static Hungry getInstance() {
        return HUNGRY;
    }
}
