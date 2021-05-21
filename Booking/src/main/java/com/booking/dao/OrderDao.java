package com.booking.dao;

import com.booking.entity.Order;

import java.util.List;

public interface OrderDao extends Dao<Integer, Order>{
    List<Order> readAll() throws DaoException;

    List<Order> readAllOrdersByUserId(Integer id) throws DaoException;
}
