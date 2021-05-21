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

public class OrderSaveServlet extends Servlet {
    public static final Logger LOGGER = LogManager.getLogger(OrderSaveServlet.class);

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Order order = new Order();
//        try {
//            order.setId(Integer.parseInt(req.getParameter("id")));
//        } catch (NumberFormatException e) {
//            LOGGER.error(e);
//        }
//
//        order.setDurationEnd();
//        order.setDurationStart();
//        order.setRoom();
//        order.setUser();
//
//        if () {
//            try {
//                OrderService service = getServiceFactory().getOrderService();
//                service.save(order);
//            } catch (FactoryException | ServiceException e) {
//                throw new ServletException(e);
//            }
//        }
        return new Forward("/order/list.html");
    }
}
