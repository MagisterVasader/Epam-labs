package com.booking.controller.servlet;

import com.booking.controller.servlet.auxiliary.*;
import com.booking.controller.servlet.bill.BillDeleteServlet;
import com.booking.controller.servlet.bill.BillEditServlet;
import com.booking.controller.servlet.bill.BillListServlet;
import com.booking.controller.servlet.bill.BillSaveServlet;
import com.booking.controller.servlet.order.OrderDeleteServlet;
import com.booking.controller.servlet.order.OrderEditServlet;
import com.booking.controller.servlet.order.OrderListServlet;
import com.booking.controller.servlet.order.OrderSaveServlet;
import com.booking.controller.servlet.room.RoomDeleteServlet;
import com.booking.controller.servlet.room.RoomEditServlet;
import com.booking.controller.servlet.room.RoomListServlet;
import com.booking.controller.servlet.room.RoomSaveServlet;
import com.booking.controller.servlet.user.UserDeleteServlet;
import com.booking.controller.servlet.user.UserEditServlet;
import com.booking.controller.servlet.user.UserListServlet;
import com.booking.controller.servlet.user.UserSaveServlet;

import javax.servlet.ServletException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ServletFactory {
    private static final Map<String, Class<? extends Servlet>> servlets = new HashMap<>();

    static {
        servlets.put("/", MainPageServlet.class);
        servlets.put("/index", MainPageServlet.class);
        servlets.put("/login", LoginServlet.class);
        servlets.put("/logout", LogoutServlet.class);
        servlets.put("/user/list", UserListServlet.class);
        servlets.put("/user/edit", UserEditServlet.class);
        servlets.put("/user/save", UserSaveServlet.class);
        servlets.put("/user/delete", UserDeleteServlet.class);
        servlets.put("/room/list", RoomListServlet.class);
        servlets.put("/room/edit", RoomEditServlet.class);
        servlets.put("/room/save", RoomSaveServlet.class);
        servlets.put("/room/delete", RoomDeleteServlet.class);
        servlets.put("/bill/list", BillListServlet.class);
        servlets.put("/bill/edit", BillEditServlet.class);
        servlets.put("/bill/save", BillSaveServlet.class);
        servlets.put("/bill/delete", BillDeleteServlet.class);
        servlets.put("/order/list", OrderListServlet.class);
        servlets.put("/order/edit", OrderEditServlet.class);
        servlets.put("/order/save", OrderSaveServlet.class);
        servlets.put("/order/delete", OrderDeleteServlet.class);
    }

    public static Servlet getServlet(String url) throws ServletException {
        Class<?> servlet = servlets.get(url);
        if (servlet != null) {
            try {
                return (Servlet) servlet.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                throw new ServletException(e);
            }
        }
        return null;
    }
}
