package com.booking.controller.servlet.order;

import com.booking.controller.servlet.Forward;
import com.booking.controller.servlet.Servlet;
import com.booking.entity.Order;
import com.booking.service.OrderService;
import com.booking.service.exception.ServiceException;
import com.booking.util.FactoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderListServlet extends Servlet {
    public static final Logger LOGGER = LogManager.getLogger(OrderListServlet.class);
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            OrderService service = getServiceFactory().getOrderService();
            List<Order> orders;

            if (req.getAttribute("user_id") !=  null){
                Integer id = null;
                try {
                    id = Integer.parseInt(req.getParameter("user_id"));
                } catch (NumberFormatException e){
                    LOGGER.error(e);
                }
                orders = service.readAllOrdersByUserId(id);
            } else {
                orders = service.readAll();
            }
            req.setAttribute("orders", orders);
            return null;
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
