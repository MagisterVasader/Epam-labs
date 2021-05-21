package com.booking.service.logic;

import com.booking.dao.DaoException;
import com.booking.dao.OrderDao;
import com.booking.entity.Order;
import com.booking.service.OrderService;
import com.booking.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OrderServiceImpl extends BaseService implements OrderService {
    public static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);

    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order readById(Integer id) throws ServiceException {
        try {
            return orderDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> readAll() throws ServiceException {
        try {
            return orderDao.readAll();
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> readAllOrdersByUserId(Integer id) throws ServiceException {
        try {
            return orderDao.readAllOrdersByUserId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Order order) throws ServiceException {
        try {
            getTransaction().start();
            if (order.getId() == null) {
                orderDao.create(order);
            } else {
                orderDao.update(order);
            }
            getTransaction().commit();
        } catch (DaoException | ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
                LOGGER.error(e1.getMessage(), e);
            }
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            orderDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
