package com.booking.controller.servlet.room;

import com.booking.controller.servlet.Forward;
import com.booking.controller.servlet.Servlet;
import com.booking.entity.Comfort;
import com.booking.entity.Room;
import com.booking.service.RoomService;
import com.booking.service.exception.ServiceException;
import com.booking.util.FactoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoomEditServlet extends Servlet {
    public static final Logger LOGGER = LogManager.getLogger(RoomEditServlet.class);

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = null;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            LOGGER.error(e);
        }
        if (id != null) {
            try {
                RoomService service = getServiceFactory().getRoomService();
                Room room = service.readById(id);
                req.setAttribute("room", room);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        req.setAttribute("comforts", Comfort.values());
        return null;
    }
}
