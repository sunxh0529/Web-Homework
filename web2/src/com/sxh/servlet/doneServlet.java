package com.sxh.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "doneServlet", urlPatterns = "/doneServlet")
public class doneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html charset=UTF-8");

        Integer A = (Integer)request.getAttribute("A");
        Integer B = (Integer)request.getAttribute("B");
        Integer C = A+B;
        request.setAttribute("SUM",C);//求和得到结果

        // 将处理结果转发回 addServlet
        request.getRequestDispatcher("/addServlet").forward(request,response);
    }
}
