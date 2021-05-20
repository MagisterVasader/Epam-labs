package com.booking.controller;

import com.booking.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class MainPageServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("session_user");
            if (user != null) {
                switch (user.getRole()) {
                    case ADMIN -> {
                        resp.sendRedirect(req.getContextPath() + "/user/list.html");
                        return;
                    }
                    case CLIENT -> {
                        resp.sendRedirect(req.getContextPath() + "/login.html?message=" + URLEncoder.encode(String.format("Пользователь %s (%s) вошел в систему", user.getLogin(), user.getRole()), StandardCharsets.UTF_8));
                        return;
                    }
                   // default -> resp.sendRedirect(req.getContextPath() + "/login.html");
                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
