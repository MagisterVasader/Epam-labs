package com.booking.service;

import com.booking.entity.Bill;
import com.booking.service.exception.ServiceException;

import java.util.List;

public interface BillService {
    List<Bill> readAll() throws ServiceException;

    void save(Bill bill) throws ServiceException;

    void delete(Integer id) throws ServiceException;
}
