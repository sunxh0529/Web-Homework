package com.sxh.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "loginFilter", urlPatterns = {"/addServlet/*"})
public class loginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //转换为http的类
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        Integer str = (Integer)session.getAttribute("Login");
        if(null == str) { //没登录
            //重定向
            response.sendRedirect(request.getContextPath()+"/login.html");
        }
        else {
            //过滤器前后打印
            System.out.println("*********filter--before***********");
            chain.doFilter(req, resp);
            System.out.println("*********filter--after***********");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
