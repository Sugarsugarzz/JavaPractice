package Learn_JavaWeb.session;

import Learn_JavaWeb.session.pojo.Person;
import org.apache.jasper.runtime.HttpJspBase;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SessionDemo01 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 解决中文乱码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        // 得到Session
        HttpSession session = req.getSession();

        // 给Session中存东西
        // Session不仅可以存字符串，还可以存对象
//        session.setAttribute("name", "哈哈");
        session.setAttribute("name", new Person("哈哈", 3));

        // 获取Session的ID
        String id = session.getId();

        // 判断Session是不是新创建的
        if (session.isNew()) {
            resp.getWriter().write("Session创建成功，ID：" + id);
        } else {
            resp.getWriter().write("Session已经在服务中存在，ID：" + id);
        }

        /*
        发现 name 存在Cookie中，Session对应的ID 为 JSESSIONID的 value
         */
        // Session创建的时候做了什么事情
//        Cookie cookie = new Cookie("JSESSIONID", id);
//        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
