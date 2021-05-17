package com.booking.controller.user;

import com.booking.Printable;
import com.booking.controller.BaseServlet;
import com.booking.entity.Role;
import com.booking.entity.User;
import com.booking.service.UserService;
import com.booking.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserSaveServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        try {
            user.setId(Integer.parseInt(req.getParameter("id")));
        } catch (NumberFormatException e) {
            Printable.printError(e.getLocalizedMessage(), e);
        }
        user.setLogin(req.getParameter("login"));

        try {
            user.setRole(Role.values()[Integer.parseInt(req.getParameter("role"))]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            Printable.printError(e.getLocalizedMessage(), e);
        }

        if (user.getLogin() != null && user.getRole() != null) {
            try (ServiceFactory factory = getFactory()) {
                UserService service = factory.getUserService();
                service.save(user);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        try {
            resp.sendRedirect(req.getContextPath() + "/user/list.html");
        } catch (IOException e) {
            Printable.printError(e.getLocalizedMessage(), e);
        }
    }
}