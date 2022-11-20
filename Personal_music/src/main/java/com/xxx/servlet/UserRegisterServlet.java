package com.xxx.servlet;

import com.xxx.bean.User;
import com.xxx.factory.DaoFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/registerForm")
public class UserRegisterServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username.trim().equals("") || username == null || password == null || password == "") {
            req.setAttribute("msg", "用户名或密码不能为空！");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
            return;
        }

        User user = DaoFactory.getUserDaoInstance().findByUsername(username);
        if (user != null) {
            req.setAttribute("msg", "用户名已存在！");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
            return;
        }

        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        int result = DaoFactory.getUserDaoInstance().save(user);
        if (result == 0) {
            req.setAttribute("msg", "未知原因导致注册失败，请稍后尝试");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
            return;
        }

        req.getSession().setAttribute("User", user);
        resp.sendRedirect("index.jsp");
        return;
    }
}
