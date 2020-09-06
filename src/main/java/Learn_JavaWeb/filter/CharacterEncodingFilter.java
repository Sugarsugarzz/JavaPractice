package Learn_JavaWeb.filter;

import javax.servlet.*;
import java.io.IOException;

// 处理中文乱码
// 需在 web.xml 中配置，和servlet一样
public class CharacterEncodingFilter implements Filter {


    // 初始化，Web服务器启动，就已经初始化了，随时等待过滤对象的出现。
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CharacterEncodingFilter初始化成功");
    }

    // Chain：链
    /*
    1. 过滤中的所有代码，在过滤特定请求的时候都会执行
    2. 必须要让过滤器继续通行
        chain.doFilter(request, response);
    3.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        System.out.println("CharacterEncodingFilter执行前...");
        chain.doFilter(request, response);  // 让请求继续走，如果不写，程序就被到此拦截停止了
        System.out.println("CharacterEncodingFilter执行后...");
    }

    // 销毁，Web服务器关闭后才会销毁
    @Override
    public void destroy() {
        System.out.println("CharacterEncodingFilter销毁成功");
    }
}
