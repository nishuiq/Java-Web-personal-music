package com.xxx.servlet;

import com.xxx.bean.User;
import com.xxx.factory.DaoFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/loginForm")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username.equals("") || username == null || password == null) {
            req.setAttribute("msg", "用户名或密码不能为空！");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
            return;
        }

        User user = DaoFactory.getUserDaoInstance().findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            req.setAttribute("msg", "用户名或密码错误！");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
            return;
        }

        req.getSession().setAttribute("User", user);
        resp.sendRedirect("index.jsp");
        return;
    }
}
