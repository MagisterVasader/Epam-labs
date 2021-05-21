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

public class UserEditServlet extends Servlet {
    public static final Logger LOGGER = LogManager.getLogger(UserEditServlet.class);

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        Integer id = null;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            LOGGER.error(e);
        }
        if (id != null) {
            try {
                UserService service = getServiceFactory().getUserService();
                User user = service.readById(id);
                req.setAttribute("user", user);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        req.setAttribute("roles", Role.values());
        return null;
    }
}
