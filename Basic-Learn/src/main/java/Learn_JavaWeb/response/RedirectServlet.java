package Learn_JavaWeb.response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*
        resp.setHeader("Location", "/web/verify");
        resp.setStatus(302);
         */

        resp.sendRedirect("/web/verify");  // 重定向
    }
}
