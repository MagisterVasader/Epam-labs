package com.booking.controller.user;

import com.booking.Printable;
import com.booking.controller.BaseServlet;
import com.booking.entity.Role;
import com.booking.entity.User;
import com.booking.service.UserService;
import com.booking.service.exception.ServiceException;
import com.booking.util.FactoryException;
import com.booking.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserEditServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = null;
        String strId = req.getParameter("id");
        if (strId != null) {
            try {
                id = Integer.parseInt(strId);
            } catch (NumberFormatException e) {
                resp.sendError(404);
                Printable.printError(e.getLocalizedMessage(), e);
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
            Printable.printError(e.getLocalizedMessage(), e);
        }
    }
}
