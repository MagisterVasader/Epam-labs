package com.booking.service.logic;

import com.booking.Printable;
import com.booking.dao.DaoException;
import com.booking.dao.OrderDao;
import com.booking.entity.Order;
import com.booking.service.OrderService;
import com.booking.service.exception.ServiceException;

import java.util.List;

public class OrderServiceImpl extends BaseService implements OrderService {
    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
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
                Printable.printError(e1.getLocalizedMessage(), e);
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
