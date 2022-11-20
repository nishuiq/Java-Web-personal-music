package com.xxx.servlet;

import com.google.gson.Gson;
import com.xxx.bean.Music;
import com.xxx.bean.User;
import com.xxx.factory.DaoFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addMusic")
public class MusicAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        String title = req.getParameter("title");
        String time = req.getParameter("time");
        String author = req.getParameter("author");
        String album_name = req.getParameter("album_name");
        int wid = Integer.parseInt(req.getParameter("wid"));
        User user = (User) req.getSession().getAttribute("User");

        Music music = new Music(title, time, author, album_name, user.getId(), wid);
        int result = DaoFactory.getMusicDaoInstance().save(music);
        PrintWriter writer = resp.getWriter();

        Gson gson = new Gson();
        if (result == 0) {
            String json = gson.toJson("{\"status\":false, \"msg\":\"插入数据失败！检查该歌曲是否已存在。\"}");
            writer.write(json);
            return;
        }

        String json = gson.toJson("{\"status\":true, \"msg\":\"插入数据成功！\"}");
        writer.write(json);
        req.getSession().setAttribute("Refresh", true);
        return;
    }

}
