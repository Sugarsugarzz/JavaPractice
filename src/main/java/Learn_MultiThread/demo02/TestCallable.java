package Learn_MultiThread.demo02;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

// 线程创建方式三：实现callable接口
/*
Callable的好处
    1. 可以定义返回值
    2. 可以抛出异常
 */
public class TestCallable implements Callable<Boolean> {


    private String url;  // 网络图片地址
    private String name;  // 保存的文件领

    public TestCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    // 下载图片线程的执行体
    @Override
    public Boolean call() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("下载了文件名为：" + name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable t1 = new TestCallable("https://pics6.baidu.com/feed/f7246b600c338744bcd1f3e15632dafed62aa0df.jpeg?token=4a129e5928a5c43599acfbf66e2c249b&s=FB0BA4461D9E05CC0E0A498A0300709A",
                "src/main/java/MultiThread/demo02/receive1.png");
        TestCallable t2 = new TestCallable("https://pics6.baidu.com/feed/f7246b600c338744bcd1f3e15632dafed62aa0df.jpeg?token=4a129e5928a5c43599acfbf66e2c249b&s=FB0BA4461D9E05CC0E0A498A0300709A",
                "src/main/java/MultiThread/demo02/receive2.png");
        TestCallable t3 = new TestCallable("https://pics6.baidu.com/feed/f7246b600c338744bcd1f3e15632dafed62aa0df.jpeg?token=4a129e5928a5c43599acfbf66e2c249b&s=FB0BA4461D9E05CC0E0A498A0300709A",
                "src/main/java/MultiThread/demo02/receive3.png");

        // 创建方式不同
        // 1、创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(3);
        // 2、提交执行
        Future<Boolean> r1 = ser.submit(t1);
        Future<Boolean> r2 = ser.submit(t2);
        Future<Boolean> r3 = ser.submit(t3);
        // 3、获取结果
        boolean rs1 = r1.get();
        boolean rs2 = r2.get();
        boolean rs3 = r3.get();
        System.out.println(rs1);
        System.out.println(rs2);
        System.out.println(rs3);
        // 4、关闭服务
        ser.shutdownNow();
    }
}

// 下载器
class WebDownloader {
    // 下载方法
    public void downloader(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出现问题");
        }
    }
}