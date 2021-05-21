package com.booking.controller.servlet.user;

import com.booking.controller.servlet.Forward;
import com.booking.controller.servlet.Servlet;
import com.booking.entity.Role;
import com.booking.entity.User;
import com.booking.service.UserService;
import com.booking.service.exception.ServiceException;
import com.booking.util.FactoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserSaveServlet extends Servlet {
    public static final Logger LOGGER = LogManager.getLogger(UserSaveServlet.class);

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        try {
            user.setId(Integer.parseInt(req.getParameter("id")));
        } catch (NumberFormatException e) {
            LOGGER.error(e);
        }
        user.setLogin(req.getParameter("login"));

        try {
            user.setRole(Role.valueOf(req.getParameter("role")));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            LOGGER.error(e);
        }

        user.setPassword(req.getParameter("password"));

        if (user.getLogin() != null && user.getRole() != null && user.getPassword() != null) {
            try {
                UserService service = getServiceFactory().getUserService();
                service.save(user);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/user/list.html");
    }
}