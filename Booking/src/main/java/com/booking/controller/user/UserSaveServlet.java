package com.booking.controller.user;

import com.booking.controller.BaseServlet;
import com.booking.entity.Role;
import com.booking.entity.User;
import com.booking.service.UserService;
import com.booking.util.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserSaveServlet extends BaseServlet {
    public static final Logger LOGGER = LogManager.getLogger(UserSaveServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Role role = null;
        try {
            role = Role.valueOf(req.getParameter("role"));
        } catch (IllegalArgumentException | NullPointerException e) {
            LOGGER.error(e);
        }

        if (login != null && !login.isBlank() && password != null && !password.isBlank() && role != null) {
            Integer id = null;
            try {
                id = Integer.parseInt(req.getParameter("id"));
            } catch (NumberFormatException e) {
                LOGGER.error(e);
            }

            User user = new User();
            user.setId(id);
            user.setLogin(login);
            user.setPassword(password);
            user.setRole(role);

            try (ServiceFactory factory = getFactory()) {
                UserService service = factory.getUserService();
                service.save(user);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }

        try {
            resp.sendRedirect(req.getContextPath() + "/user/list.html");
        } catch (IOException e){
            LOGGER.error(e);
        }
    }
}