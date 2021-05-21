package com.booking.controller.servlet.bill;

import com.booking.controller.servlet.Forward;
import com.booking.controller.servlet.Servlet;
import com.booking.entity.Bill;
import com.booking.service.BillService;
import com.booking.service.exception.ServiceException;
import com.booking.util.FactoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BillSaveServlet extends Servlet {
    public static final Logger LOGGER = LogManager.getLogger(BillSaveServlet.class);

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Bill bill = new Bill();
        if (req.getParameter("create") != null) {
            bill.setCreate("create");
        }

        try {
            bill.setId(Integer.parseInt(req.getParameter("id")));
        } catch (NumberFormatException e) {
            LOGGER.error(e);
        }

        try {
            bill.setTotalPrice(Integer.parseInt(req.getParameter("totalPrice")));
        } catch (NumberFormatException e) {
            LOGGER.error(e);
        }

        if (bill.getTotalPrice() != null) {
            try {
                BillService service = getServiceFactory().getBillService();
                service.save(bill);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/bill/list.html");
    }
}
