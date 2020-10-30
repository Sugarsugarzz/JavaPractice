package Learn_NetworkProgram.lesson03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

// 循环发送
public class UdpSenderDemo01 {
    public static void main(String[] args) throws Exception {

        DatagramSocket socket = new DatagramSocket(8888);

        // 准备数据：控制台读取 System.in
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String data = reader.readLine();
            DatagramPacket packet = new DatagramPacket(data.getBytes(), 0, data.getBytes().length,
                    new InetSocketAddress("localhost", 6666));
            socket.send(packet);

            // 本地退出
            if (data.equals("bye"))
                break;
        }

        socket.close();
    }
}
