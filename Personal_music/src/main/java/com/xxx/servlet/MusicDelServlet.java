package com.xxx.servlet;

import com.google.gson.Gson;
import com.xxx.bean.User;
import com.xxx.factory.DaoFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/delMusic")
public class MusicDelServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        int wid = Integer.parseInt(req.getParameter("wid"));

        User user = (User) req.getSession().getAttribute("User");

        int result = DaoFactory.getMusicDaoInstance().delete(user.getId(), wid);
        PrintWriter writer = resp.getWriter();

        Gson gson = new Gson();
        if (result == 0) {
            String json = gson.toJson("{\"status\":false, \"msg\":\"删除数据失败！\"}");
            writer.write(json);
            return;
        }

        String json = gson.toJson("{\"status\":true, \"msg\":\"删除数据成功！\"}");
        writer.write(json);
        req.getSession().setAttribute("Refresh", true);
        return;
    }
}
