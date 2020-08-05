package Learn_NetworkProgram.lesson05_url;

import java.net.MalformedURLException;
import java.net.URL;

public class URLDemo01 {
    public static void main(String[] args) throws MalformedURLException {

        URL url = new URL("http://localhost:8080/helloword/index.html?username=kuangshen&password=123");
        // 根据url获取信息（说白了就是拆分URL）
        System.out.println(url.getProtocol());  // 协议
        System.out.println(url.getHost());  // 主机IP
        System.out.println(url.getPort());  // 端口
        System.out.println(url.getPath());  // 文件
        System.out.println(url.getFile());  // 全路径
        System.out.println(url.getQuery());  // 参数

    }
}
