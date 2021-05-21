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
import java.sql.Date;

public class OrderSaveServlet extends Servlet {
    public static final Logger LOGGER = LogManager.getLogger(OrderSaveServlet.class);

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = new Order();
        try {
            order.setId(Integer.parseInt(req.getParameter("id")));
        } catch (NumberFormatException e) {
            LOGGER.error(e);
        }

        try {
            String[] durationStart = req.getParameter("durationStart").split("-");
            int year = Integer.parseInt(durationStart[0]);
            int month = Integer.parseInt(durationStart[1]);
            int day = Integer.parseInt(durationStart[2]);
            order.setDurationStart(new Date(year - 1900, month, day));

            String[] durationEnd = req.getParameter("durationEnd").split("-");
            year = Integer.parseInt(durationEnd[0]);
            month = Integer.parseInt(durationEnd[1]);
            day = Integer.parseInt(durationEnd[2]);
            order.setDurationEnd(new Date(year - 1900, month, day));

            order.getRoom().setId(Integer.parseInt(req.getParameter("room")));
            order.getUser().setId(Integer.parseInt(req.getParameter("user")));
        } catch (NumberFormatException e) {
            LOGGER.error(e);
        }

        if (order.getUser().getId() != null && order.getRoom().getId() != null && order.getDurationStart() != null && order.getDurationEnd() != null) {
            try {
                OrderService service = getServiceFactory().getOrderService();
                service.save(order);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/order/list.html");
    }
}
