package com.xxx.filter;

import com.xxx.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 非法访问拦截
 *      拦截的资源：
 *          拦截所有资源 /*
 *      需要放行的资源：
 *          1. 放行指定页面（无须登录即可访问的页面 例如：登录页面，注册页面等）
 *          2. 静态资源，放行（image、js、css文件等）
 *          3. 指定操作，放行（无需登录即可执行的操作 例如：登录页面、注册页面）
 *          4. 登录状态，放行（判断session中用户信息是否为空）
 */
@WebFilter("/*")
public class LoginAccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        // 基于HTTP请求
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        String url = request.getRequestURI();
        System.out.println(url);

        // 包含需要登录才能操作的页面时，需要检查权限
        if (url.contains("/music.jsp") || url.contains("/addMusic") || url.contains("/update_music") || url.contains("/delMusic")) {
            User user = (User) request.getSession().getAttribute("User");
            if (user != null) {
                chain.doFilter(request,response);
                return;
            }
            else {
                // 否则，用户未登录，拦截请求并跳转到登录页面
                response.sendRedirect("login.jsp");
                return;
            }
        }
        // 其它资源放行
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
