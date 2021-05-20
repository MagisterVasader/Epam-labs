package com.booking.controller.user;

import com.booking.controller.BaseServlet;
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
import java.util.List;

public class UserListServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("session_user");
            if (user != null && user.getRole() == Role.ADMIN){
                try (ServiceFactory factory = getFactory()) {
                    UserService service = factory.getUserService();
                    List<User> users = service.readAll();
                    req.setAttribute("users", users);
                    req.getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(req, resp);
                    return;
                } catch (Exception e) {
                    throw new ServletException(e);
                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/login.html?message=" + URLEncoder.encode("Доступ запрещен",StandardCharsets.UTF_8));
    }
}
