package com.booking.util;

import com.booking.dao.BillDao;
import com.booking.dao.OrderDao;
import com.booking.dao.RoomDao;
import com.booking.dao.UserDao;
import com.booking.service.*;

import java.sql.Connection;

public interface ServiceFactory extends AutoCloseable {
    UserService getUserService() throws FactoryException;

    BillService getBillService() throws FactoryException;

    OrderService getOrderService() throws FactoryException;

    RoomService getRoomService() throws FactoryException;

    Transaction getTransaction() throws FactoryException;

    UserDao getUserDao() throws FactoryException;

    BillDao getBillDao() throws FactoryException;

    OrderDao getOrderDao() throws FactoryException;

    RoomDao getRoomDao() throws FactoryException;

    Connection getConnection() throws FactoryException;
}