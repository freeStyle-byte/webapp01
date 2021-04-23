package com.bjpowernode.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author freeStyle
 * @time 2021/4/21/20:17
 * @project idea-workspace
 */
public class UserFilter implements Filter {
    // 使用这个过滤器对象对用户每次访问进行验证身份是否为合法用户
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = null;
        String uri = request.getRequestURI();
        //System.out.println("uri = " + uri);
        if (uri.indexOf("login") != -1 || "/app01/".equals(uri)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return ;
        }
        //2. 判断来访用户身份合法性
        session = request.getSession(false);
        if (session == null) {
            // 身份不合法
            request.getRequestDispatcher("/login-error.html").forward(servletRequest, servletResponse);
            return ;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
