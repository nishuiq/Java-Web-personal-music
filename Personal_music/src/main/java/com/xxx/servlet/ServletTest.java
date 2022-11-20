package com.xxx.servlet;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test")
public class ServletTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=utf-8");
        PrintWriter writer = response.getWriter();

        String str = request.getParameter("id");
        System.out.println(str);

        Gson gson = new Gson();
        String json = gson.toJson("{\"status\":true, \"msg\":\"测试发送！\"}");
        System.out.println(json);
        writer.append(json);

//        writer.append("测试 2333");
//        int a = Integer.parseInt(request.getParameter("a"));
//        System.out.println(a);
//        writer.append(String.valueOf(a));
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=utf-8");
        PrintWriter writer = response.getWriter();
        Gson gson = new Gson();
        String json = gson.toJson("{\"status\":true, \"msg\":\"测试发送！\"}");
        System.out.println(json);
        writer.append(json);
        return;
    }
}
