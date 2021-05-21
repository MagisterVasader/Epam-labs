package com.booking.controller.servlet.order;

import com.booking.controller.servlet.Forward;
import com.booking.controller.servlet.Servlet;
import com.booking.entity.Order;
import com.booking.entity.Role;
import com.booking.entity.User;
import com.booking.service.OrderService;
import com.booking.service.exception.ServiceException;
import com.booking.util.FactoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OrderListServlet extends Servlet {
    public static final Logger LOGGER = LogManager.getLogger(OrderListServlet.class);

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            OrderService service = getServiceFactory().getOrderService();
            List<Order> orders;
            HttpSession session = req.getSession(false);
            User user = (User) session.getAttribute("session_user");
            if (user.getRole() == Role.CLIENT) {
                orders = service.readAllOrdersByUserId(user.getId());
            } else {
                orders = service.readAll();
            }
            req.setAttribute("orders", orders);
            return null;
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
