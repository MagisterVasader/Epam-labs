package com.booking.controller.user;

import com.booking.controller.BaseServlet;
import com.booking.service.UserService;
import com.booking.util.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserDeleteServlet extends BaseServlet {
    public static final Logger LOGGER = LogManager.getLogger(UserDeleteServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
        Integer id = null;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            LOGGER.error(e);
        }
        if (id != null) {
            try (ServiceFactory factory = getFactory()) {
                UserService service = factory.getUserService();
                service.delete(id);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }

        try {
            resp.sendRedirect(req.getContextPath() + "/user/list.html");
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}