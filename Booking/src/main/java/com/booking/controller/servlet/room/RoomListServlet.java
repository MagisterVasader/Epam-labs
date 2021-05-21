package com.booking.controller.servlet.room;

import com.booking.controller.servlet.Forward;
import com.booking.controller.servlet.Servlet;
import com.booking.entity.Role;
import com.booking.entity.Room;
import com.booking.entity.User;
import com.booking.service.RoomService;
import com.booking.service.exception.ServiceException;
import com.booking.util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class RoomListServlet extends Servlet {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RoomService service = getServiceFactory().getRoomService();
            List<Room> rooms;
            HttpSession session = req.getSession(false);
            User user = (User) session.getAttribute("session_user");
            if (user.getRole() == Role.ADMIN) {
                rooms = service.readAll();
            } else {
                rooms = service.readAllFreeRooms();
            }
            req.setAttribute("rooms", rooms);
            return null;
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
