package com.booking.controller;

import com.booking.entity.Role;
import com.booking.entity.User;
import com.booking.service.UserService;
import com.booking.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class LoginServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login != null && !login.isBlank() && password != null) {
            try (ServiceFactory factory = getFactory()) {
                UserService userService = factory.getUserService();
                User user = userService.readByLoginAndPassword(login, password);
                if (user != null && user.getRole() == Role.ADMIN | user.getRole() == Role.CLIENT) {
                    HttpSession session = req.getSession();
                    session.setAttribute("session_user", user);
                    resp.sendRedirect(req.getContextPath() + "/index.html");
                    return;
                }
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/login.html?message=" + URLEncoder.encode("Неправильный логи или пароль", StandardCharsets.UTF_8));
    }
}
