package com.sxh.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "logoutServlet", urlPatterns = "/logoutServlet")
public class logoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html charset=UTF-8");

        PrintWriter writer = response.getWriter();

        if(request.getSession().getAttribute("Login") == null) {
            writer.println("未登录");
            //重定向
            response.sendRedirect(request.getContextPath()+"/login.html");
        }
        else {
            writer.println("登出成功");
            request.getSession().removeAttribute("Login");
        }

    }
}
