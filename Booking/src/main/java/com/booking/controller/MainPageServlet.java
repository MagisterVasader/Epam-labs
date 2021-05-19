package com.booking.controller;

import com.booking.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MainPageServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("session_user");
            if (user != null) {
                switch (user.getRole()) {
                    case ADMIN -> resp.sendRedirect(req.getContextPath() + "/user/admin.html");
                    case CLIENT -> resp.sendRedirect(req.getContextPath() + "/user/user.html");
                    default -> resp.sendRedirect(req.getContextPath() + "/login.html");
                }
            } else {
                resp.sendRedirect(req.getContextPath() + "/login.html");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.html");
        }
    }
}
