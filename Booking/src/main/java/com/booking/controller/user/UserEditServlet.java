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

public class UserEditServlet extends BaseServlet {
    public static final Logger LOGGER = LogManager.getLogger(UserEditServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        Integer id = null;
        String strId = req.getParameter("id");
        if (strId != null) {
            try {
                id = Integer.parseInt(strId);
            } catch (NumberFormatException e) {
                LOGGER.error(e);
                try {
                    resp.sendError(404);
                } catch (IOException e1) {
                    LOGGER.error(e1);
                }
            }
        }
        if (id != null) {
            try (ServiceFactory factory = getFactory()) {
                UserService service = factory.getUserService();
                User user = service.readById(id);
                if (user != null) {
                    req.setAttribute("user", user);
                } else {
                    resp.sendError(404);
                    return;
                }
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }

        req.setAttribute("roles", Role.values());
        try {
            req.getRequestDispatcher("/WEB-INF/jsp/user/edit.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            LOGGER.error(e);
        }
    }
}
