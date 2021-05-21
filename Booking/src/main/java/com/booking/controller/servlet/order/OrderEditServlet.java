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

public class OrderEditServlet extends Servlet {
    public static final Logger LOGGER = LogManager.getLogger(OrderEditServlet.class);

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        Integer id = null;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch(NumberFormatException e) {
            LOGGER.error(e);
        }

        if(id != null) {
            try {
                OrderService service = getServiceFactory().getOrderService();
                Order order = service.readById(id);
                req.setAttribute("order", order);
            } catch(FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return null;
    }
}
