package com.booking.service;

import com.booking.entity.Order;
import com.booking.service.exception.ServiceException;

import java.util.List;

public interface OrderService {
    Order readById(Integer id) throws ServiceException;

    List<Order> readAllOrdersByUserId(Integer id) throws ServiceException;

    void save(Order order) throws ServiceException;

    void delete(Integer id) throws ServiceException;

    List<Order> readAll() throws ServiceException;
}
