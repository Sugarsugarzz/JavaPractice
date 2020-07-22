package NetworkProgram.lesson02;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

// 文件上传
public class TcpClientDemo02 {

    public static void main(String[] args) throws Exception {

        // 1、创建Socket连接
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9000);
        // 2、创建一个输出流
        OutputStream os = socket.getOutputStream();
        // 3、读取文件
        // File 相对路径是从根目录开始
        FileInputStream fis = new FileInputStream(new File("src/main/java/NetworkProgram/lesson02/pic.png"));
        // 4、写出文件
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }

        // 通知服务器，已经传输结束了
        socket.shutdownOutput();

        // 确定服务器接收完毕，才能够断开连接
        InputStream is = socket.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buffer2 = new byte[1024];
        int len2;
        while ((len2 = is.read(buffer2)) != -1) {
            baos.write(buffer2, 0, len2);
        }
        System.out.println(baos.toString());

        // 5、关闭资源
        baos.close();
        fis.close();
        os.close();
        socket.close();
    }
}
