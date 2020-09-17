package controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.URLEncoder;

@RestController
public class FileController {

    // @RequestMapping("/upload") 将name=file控件得到的文件封装成CommonsMultipartFile 对象
    // 批量上传CommonsMultipartFile则为数组即可
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {

        // 获取文件名：file.getOriginalFilename();
        String filename = file.getOriginalFilename();

        // 如果文件夹为空，直接回到首页！
        if ("".equals(filename)) {
            return "redirect:/index.jsp";
        }
        System.out.println("上传文件名为：" + filename);

        // 上传路径保存设置
        String path = request.getServletContext().getRealPath("/upload");
        System.out.println(path);

        // 如果路径不存在，则创建一个
        File filepath = new File(path);
        if (!filepath.exists()) {
            filepath.mkdir();
        }
        System.out.println("上传文件保存地址：" + filepath);

        InputStream is = file.getInputStream();  // 文件输入流
        OutputStream os = new FileOutputStream(new File(filepath, filename));  // 文件输出流

        // 读取写出
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
            os.flush();
        }

        os.close();
        is.close();

        return "redirect:/index.jsp";
    }

    // 采用 file.transferTo 来保存上传的文件 （推荐）
    @RequestMapping("/upload2")
    public String upload2(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {

        // 上传路径保存设置
        String path = request.getServletContext().getRealPath("/upload");
        File filepath = new File(path);


        // 上传文件地址
        System.out.println("上传文件保存地址：" + filepath);

        // 通过 CommonsMultipartFile 的方法直接写文件（注意）
        file.transferTo(new File(filepath + "/" + file.getOriginalFilename()));

        return "redirect:/index.jsp";
    }

    // 文件下载
    @RequestMapping("/download")
    public String download(HttpServletResponse response, HttpServletRequest request) throws IOException {
        // 要下载的图片地址
        String path = request.getServletContext().getRealPath("/upload");
        String filename = "Java.pdf";

         // 1. 设置 response 响应头
        response.reset();  // 设置页面不缓存，清空buffer
        response.setCharacterEncoding("UTF-8");  // 字符编码
        response.setContentType("multipart/form-data");  // 二进制传输数据

        // 设置响应头
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename, "UTF-8"));

        File file = new File(path, filename);
        // 2. 读取文件 - 输入流
        FileInputStream input = new FileInputStream(file);
        // 3. 写出文件 - 输出流
        ServletOutputStream out = response.getOutputStream();

        byte[] buffer = new byte[1024];
        int len = 0;
        // 4. 执行写出操作
        while ((len=input.read(buffer)) != -1) {
            out.write(buffer, 0, len);
            out.flush();
        }

        out.close();
        input.close();

        return null;
    }



}
