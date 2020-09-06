package Learn_JavaWeb.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

// 统计网站在线人数：统计Session
public class OnlineCountListener implements HttpSessionListener {

    // 创建 Session 监听
    // 一旦擦混构建Session就会触发一次这个事件
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();
        Integer onlineCount = (Integer) ctx.getAttribute("OnlineCount");
        if (onlineCount == null) {
            onlineCount = 1;
        } else {
            onlineCount = onlineCount + 1;
        }

        ctx.setAttribute("OnlineCount", onlineCount);
    }

    // 销毁 Session 监听
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();
        Integer onlineCount = (Integer) ctx.getAttribute("OnlineCount");
        if (onlineCount == null) {
            onlineCount = 0;
        } else {
            onlineCount = onlineCount - 1;
        }

        ctx.setAttribute("OnlineCount", onlineCount);
    }

    /*
    Session销毁：
    1. 手动销毁
    2. 自动销毁
     */
}
