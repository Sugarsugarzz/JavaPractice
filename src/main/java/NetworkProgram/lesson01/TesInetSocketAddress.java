package NetworkProgram.lesson01;

import java.net.InetSocketAddress;

// 套接字测试
public class TesInetSocketAddress {

    public static void main(String[] args) {

        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8080);
        System.out.println(socketAddress);

        System.out.println(socketAddress.getAddress());
        System.out.println(socketAddress.getHostName());  // 地址
        System.out.println(socketAddress.getPort());  // 端口
    }
}
