package com.booking.controller.user;

import com.booking.Printable;
import com.booking.controller.BaseServlet;
import com.booking.service.UserService;
import com.booking.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserDeleteServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = null;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) { }
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
            Printable.printError(e.getLocalizedMessage(),e);
        }
    }
}