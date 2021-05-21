package com.booking.controller.servlet.room;

import com.booking.controller.servlet.Forward;
import com.booking.controller.servlet.Servlet;
import com.booking.controller.servlet.user.UserSaveServlet;
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

public class RoomSaveServlet extends Servlet {
    public static final Logger LOGGER = LogManager.getLogger(RoomSaveServlet.class);

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Room room = new Room();
        try {
            room.setId(Integer.parseInt(req.getParameter("id")));
        } catch (NumberFormatException e) {
            LOGGER.error(e);
        }

        try {
            room.setComfort(Comfort.valueOf(req.getParameter("comfort")));
            room.setPrice(Integer.parseInt(req.getParameter("price")));
            System.out.println("DEBUG " + req.getParameter("free"));
            room.setFree(req.getParameter("free").equals("true"));
            room.setCapacity(Integer.parseInt(req.getParameter("capacity")));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            LOGGER.error(e);
        }

        if (room.getFree() != null && room.getCapacity() != null && room.getComfort() != null && room.getPrice() != null) {
            try {
                RoomService service = getServiceFactory().getRoomService();
                service.save(room);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/room/list.html");
    }
}
