package Learn_NetworkProgram.lesson05_url;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLDown {
    public static void main(String[] args) throws Exception {

        // 1、下载地址
        URL url = new URL("http://localhost:8080/alibaba/hello.txt");
        // 2、连接到这个资源 HTTP
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        InputStream inputStream = urlConnection.getInputStream();

        FileOutputStream fos = new FileOutputStream("src/main/java/NetworkProgram/lesson05_url/DownloadFile.txt");

        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            fos.write(buffer, 0, len); // 写出这个数据
        }

        // 关闭资源
        fos.close();
        inputStream.close();
        urlConnection.disconnect();
    }
}
