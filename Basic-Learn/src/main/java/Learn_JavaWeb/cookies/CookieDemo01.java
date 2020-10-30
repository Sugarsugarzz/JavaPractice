package Learn_JavaWeb.cookies;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

// 保存用户上一次访问的时间
public class CookieDemo01 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 服务器，告诉客户来的时间，把这个时间封装称为一个信件，下次客户来就知道了

        // 解决中文乱码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        PrintWriter out = resp.getWriter();

        // Cookie，服务器端从客户端获取
        Cookie[] cookies = req.getCookies();  // 这里返回数据，说明Cookie可能存在多个

        // 判断Cookie是否存在
        if (cookies != null) {
            // 如果存在Cookie
            out.write("上一次访问的时间是：");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                // 获取Cookie的name
                if (cookie.getName().equals("lastLoginTime")) {
                    // 获取Cookie的value
                    Long timeStamp = Long.parseLong(cookie.getValue());  // 转成时间戳
                    Date date = new Date(timeStamp);
                    out.write(date.toLocaleString());
                }
            }
        } else {
            out.write("这是第一次访问");
        }

        // 服务器给客户端响应一个cookie
        Cookie cookie = new Cookie("lastLoginTime", System.currentTimeMillis() + "");
        // 给Cookie设置存活时间
        cookie.setMaxAge(24*60*60);
        resp.addCookie(cookie);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
