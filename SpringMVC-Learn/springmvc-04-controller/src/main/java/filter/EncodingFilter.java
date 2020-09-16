package filter;


import javax.servlet.*;
import java.io.IOException;

// 弃用Servlet级过滤器，用SpringMVC的过滤器
@Deprecated
public class EncodingFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
    }

    public void destroy() {

    }
}
