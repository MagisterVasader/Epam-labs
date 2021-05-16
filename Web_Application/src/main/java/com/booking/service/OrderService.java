package com.booking.service;

import com.booking.entity.Order;
import com.booking.service.exception.ServiceException;

import java.util.List;

public interface OrderService {
    List<Order> readAllOrdersByUserId(Integer id) throws ServiceException;

    void save(Order order) throws ServiceException;

    void delete(Integer id) throws ServiceException;
}
