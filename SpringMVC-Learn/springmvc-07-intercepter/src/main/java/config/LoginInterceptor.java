package config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        // 放行判断：已登录的情况
        if (session.getAttribute("userLoginInfo") != null) {
            return true;
        }

        // 直接访问登录请求也会放行
        if (request.getRequestURL().toString().contains("login")) {
            return true;
        }

        // 直接访问登录页面也会放行
        if (request.getRequestURL().toString().contains("goLogin")) {
            return true;
        }

        // 没有登录的情况
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        return false;
    }
}
