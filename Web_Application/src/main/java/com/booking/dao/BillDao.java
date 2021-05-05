package com.booking.dao;

import com.booking.entity.Bill;

import java.util.List;

public class BillDao implements Dao<Integer, Bill>{
    private static final String BILL_TABLE = "bill";
    private static final String BILL_ID_COLUMN = "bill_id";
    private static final String TOTAL_PRICE_COLUMN = "total_price";

    @Override
    public List<Bill> findAll() {
        return null;
    }

    @Override
    public Bill findEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean create(Bill entity) {
        return false;
    }

    @Override
    public boolean update(Bill entity) {
        return false;
    }
}
