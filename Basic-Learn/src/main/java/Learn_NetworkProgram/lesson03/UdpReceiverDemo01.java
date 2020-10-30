package Learn_NetworkProgram.lesson03;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

// 循环接受
public class UdpReceiverDemo01 {
    public static void main(String[] args) throws Exception {

        DatagramSocket socket = new DatagramSocket(6666);

        while (true) {
            // 准备接受包裹
            byte[] container = new byte[1024];
            DatagramPacket packet = new DatagramPacket(container, 0, container.length);
            socket.receive(packet);
            // 断开连接
            byte[] data = packet.getData();
            String receiveData = new String(data, 0, data.length);
            System.out.println(receiveData);
            if (receiveData.equals("bye"))
                break;

        }
        socket.close();
    }
}
