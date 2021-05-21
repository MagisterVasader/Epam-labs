package com.booking.controller.servlet.auxiliary;

import com.booking.controller.servlet.Forward;
import com.booking.controller.servlet.Servlet;
import com.booking.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MainPageServlet extends Servlet {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("session_user");
            if (user != null) {
                return switch (user.getRole()) {
                    case ADMIN -> new Forward("/user/list.html");
                    case CLIENT -> new Forward("/room/list.html");
                };
            }
        }
        return new Forward("/login.html");
    }
}
