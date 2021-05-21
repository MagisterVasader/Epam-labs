package com.booking.controller.servlet.order;

import com.booking.controller.servlet.Forward;
import com.booking.controller.servlet.Servlet;
import com.booking.entity.Order;
import com.booking.entity.Role;
import com.booking.entity.Room;
import com.booking.entity.User;
import com.booking.service.OrderService;
import com.booking.service.RoomService;
import com.booking.service.UserService;
import com.booking.service.exception.ServiceException;
import com.booking.util.FactoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OrderEditServlet extends Servlet {
    public static final Logger LOGGER = LogManager.getLogger(OrderEditServlet.class);

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        Integer id = null;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            LOGGER.error(e);
        }
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("session_user");

        try {
            RoomService service = getServiceFactory().getRoomService();
            List<Room> rooms;
            if (user.getRole() == Role.CLIENT) {
                rooms = service.readAllFreeRooms();
            } else {
                rooms = service.readAll();
            }
            req.setAttribute("rooms", rooms);

            UserService userService = getServiceFactory().getUserService();
            List<User> users = userService.readAll();
            req.setAttribute("users", users);
        } catch (ServiceException | FactoryException e) {
            LOGGER.error(e);
        }

        if (id != null) {
            try {
                OrderService service = getServiceFactory().getOrderService();
                Order order = service.readById(id);
                req.setAttribute("order", order);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return null;
    }
}
