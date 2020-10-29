package com.sxh.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 注解
@WebServlet(name = "addServlet", urlPatterns = "/addServlet/*")
public class addServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //转换编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html charset=UTF-8");

        PrintWriter writer = response.getWriter();

        //返回资源uri
        String url = request.getRequestURI();

        if(null != request.getAttribute("SUM")) {
            Integer C = (Integer)request.getAttribute("SUM");
            writer.println(C);
        }

        else { //校验
            String[] urlget = url.split("/");
            if(urlget.length != 4) {
                writer.println("地址级别错误");
            }

            else {
                //正则表达式
                String regex = "^a=(-?[0-9]+)&b=(-?[0-9]+)$";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(urlget[3]);
                boolean flag = false;
                String a = null;
                String b = null;
                Integer A = null;
                Integer B = null;
                while(m.find()) {
                    a = m.group(1);
                    b = m.group(2);
                    A = Integer.parseInt(a);
                    B = Integer.parseInt(b);
                    flag = true;
                }
                if(flag == true) { //校验成功
                    request.setAttribute("A",A);
                    request.setAttribute("B",B);
                    request.getRequestDispatcher("/doneServlet").forward(request,response);//请求转发
                }
                else { //校验失败
                    writer.println("参数错误");
                }

            }

        }

    }
}
