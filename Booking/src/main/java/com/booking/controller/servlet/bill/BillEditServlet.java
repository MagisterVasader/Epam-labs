package com.booking.controller.servlet.bill;

import com.booking.controller.servlet.Forward;
import com.booking.controller.servlet.Servlet;
import com.booking.entity.Bill;
import com.booking.entity.Order;
import com.booking.service.BillService;
import com.booking.service.OrderService;
import com.booking.service.exception.ServiceException;
import com.booking.util.FactoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BillEditServlet extends Servlet {
    public static final Logger LOGGER = LogManager.getLogger(BillEditServlet.class);

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        Integer id = null;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            LOGGER.error(e);
        }

        if (id != null) {
            try {
                BillService service = getServiceFactory().getBillService();
                Bill bill = service.readById(id);
                req.setAttribute("bill", bill);


            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        } else {
            try {
                OrderService orderService = getServiceFactory().getOrderService();
                List<Order> orders = orderService.readAll();
                req.setAttribute("orders", orders);
            } catch (ServiceException | FactoryException e) {
                LOGGER.error(e);
            }
        }

        return null;
    }
}
