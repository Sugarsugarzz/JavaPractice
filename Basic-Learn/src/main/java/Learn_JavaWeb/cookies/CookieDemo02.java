package Learn_JavaWeb.cookies;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

// 删除Cookie，有效期设为0
public class CookieDemo02 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 创建一个Cookie，名字与需要删除的名字一致（替换掉）
        Cookie cookie = new Cookie("lastLoginTime", System.currentTimeMillis() + "");
        // 将有效期设为0
        cookie.setMaxAge(0);
        resp.addCookie(cookie);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
