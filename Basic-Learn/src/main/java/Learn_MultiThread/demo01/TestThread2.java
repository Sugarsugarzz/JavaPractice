package Learn_MultiThread.demo01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

// 练习Thread实现多线程同步下载图片
//public class TestThread2 implements Runnable {
public class TestThread2 extends Thread {

    private String url;  // 网络图片地址
    private String name;  // 保存的文件领

    public TestThread2(String url, String name) {
        this.url = url;
        this.name = name;
    }

    // 下载图片线程的执行体
    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("下载了文件名为：" + name);
    }

    public static void main(String[] args) {
        TestThread2 t1 = new TestThread2("https://pics6.baidu.com/feed/f7246b600c338744bcd1f3e15632dafed62aa0df.jpeg?token=4a129e5928a5c43599acfbf66e2c249b&s=FB0BA4461D9E05CC0E0A498A0300709A",
                "src/main/java/MultiThread/demo01/receive1.png");
        TestThread2 t2 = new TestThread2("https://pics6.baidu.com/feed/f7246b600c338744bcd1f3e15632dafed62aa0df.jpeg?token=4a129e5928a5c43599acfbf66e2c249b&s=FB0BA4461D9E05CC0E0A498A0300709A",
                "src/main/java/MultiThread/demo01/receive2.png");
        TestThread2 t3 = new TestThread2("https://pics6.baidu.com/feed/f7246b600c338744bcd1f3e15632dafed62aa0df.jpeg?token=4a129e5928a5c43599acfbf66e2c249b&s=FB0BA4461D9E05CC0E0A498A0300709A",
                "src/main/java/MultiThread/demo01/receive3.png");

        // 下载顺序和执行顺序不同
        t1.start();
        t2.start();
        t3.start();

//        new Thread(t1).start();
//        new Thread(t2).start();
//        new Thread(t3).start();
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
