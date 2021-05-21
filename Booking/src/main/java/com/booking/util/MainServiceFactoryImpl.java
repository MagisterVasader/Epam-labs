package com.booking.util;

import com.booking.dao.BillDao;
import com.booking.dao.OrderDao;
import com.booking.dao.RoomDao;
import com.booking.dao.UserDao;
import com.booking.dao.mysql.BillDaoImpl;
import com.booking.dao.mysql.OrderDaoImpl;
import com.booking.dao.mysql.RoomDaoImpl;
import com.booking.dao.mysql.UserDaoImpl;
import com.booking.pool.ConnectionPool;
import com.booking.pool.ConnectionPoolException;
import com.booking.service.*;
import com.booking.service.logic.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public class MainServiceFactoryImpl implements ServiceFactory {
    public static final Logger LOGGER = LogManager.getLogger(MainServiceFactoryImpl.class);

    private Connection connection;

    @Override
    public UserService getUserService() throws FactoryException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setTransaction(getTransaction());
        userService.setUserDao(getUserDao());
        return userService;
    }

    @Override
    public BillService getBillService() throws FactoryException {
        BillServiceImpl billService = new BillServiceImpl();
        billService.setTransaction(getTransaction());
        billService.setBillDao(getBillDao());
        return billService;
    }

    @Override
    public OrderService getOrderService() throws FactoryException {
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.setTransaction(getTransaction());
        orderService.setOrderDao(getOrderDao());
        return orderService;
    }

    @Override
    public RoomService getRoomService() throws FactoryException {
        RoomServiceImpl roomService = new RoomServiceImpl();
        roomService.setTransaction(getTransaction());
        roomService.setRoomDao(getRoomDao());
        return roomService;
    }

    @Override
    public UserDao getUserDao() throws FactoryException {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.setConnection(getConnection());
        return userDao;
    }

    @Override
    public BillDao getBillDao() throws FactoryException {
        BillDaoImpl billDao = new BillDaoImpl();
        billDao.setConnection(getConnection());
        return billDao;
    }

    @Override
    public OrderDao getOrderDao() throws FactoryException {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        orderDao.setConnection(getConnection());
        return orderDao;
    }

    @Override
    public RoomDao getRoomDao() throws FactoryException {
        RoomDaoImpl roomDao = new RoomDaoImpl();
        roomDao.setConnection(getConnection());
        return roomDao;
    }

    @Override
    public Connection getConnection() throws FactoryException {
        if (connection == null) {
            try {
                connection = ConnectionPool.getInstance().getConnection();
            } catch (ConnectionPoolException e) {
                throw new FactoryException(e);
            }
        }
        return connection;
    }

    @Override
    public Transaction getTransaction() throws FactoryException {
        TransactionImpl transaction = new TransactionImpl();
        transaction.setConnection(getConnection());
        return transaction;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                Connection c = connection;
                connection = null;
                c.close();
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
    }
}