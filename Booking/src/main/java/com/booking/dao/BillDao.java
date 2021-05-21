package com.booking.dao;

import com.booking.entity.Bill;

import java.util.List;

public interface BillDao extends Dao<Integer, Bill> {
    List<Bill> readAll() throws DaoException;
}
