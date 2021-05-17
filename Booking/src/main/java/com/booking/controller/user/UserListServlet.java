package com.booking.controller.user;

import com.booking.controller.BaseServlet;
import com.booking.entity.User;
import com.booking.service.UserService;
import com.booking.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserListServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
        try(ServiceFactory factory = getFactory()) {
            UserService service = factory.getUserService();
            List<User> users = service.readAll();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(req, resp);
        } catch(Exception e) {
            throw new ServletException(e);
        }
    }
}
