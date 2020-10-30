package Learn_NetworkProgram.lesson04_chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

// 多线程聊天 发送工具类
public class TalkSend implements Runnable{

    DatagramSocket socket = null;
    BufferedReader reader = null;

    private int fromPort;
    private String toIP;
    private int toPort;

    public TalkSend(int fromPort, String toIP, int toPort) {
        this.fromPort = fromPort;
        this.toIP = toIP;
        this.toPort = toPort;

        try {
            socket = new DatagramSocket(fromPort);
            reader = new BufferedReader(new InputStreamReader(System.in));
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String data = reader.readLine();
                DatagramPacket packet = new DatagramPacket(data.getBytes(), 0, data.getBytes().length,
                        new InetSocketAddress(this.toIP, this.toPort));
                socket.send(packet);

                // 本地退出
                if (data.equals("bye"))
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        socket.close();
    }
}
