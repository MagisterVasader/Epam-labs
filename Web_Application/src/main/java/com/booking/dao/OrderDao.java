package com.booking.dao;

import com.booking.entity.Order;

import java.util.List;

public class OrderDao implements Dao<Integer, Order>{
    private static final String ORDER_TABLE = "order";
    private static final String ORDER_ID_COLUMN = "order_id";
    private static final String COMFORT_COLUMN = "comfort";
    private static final String DURATION_START_COLUMN = "duration_start";
    private static final String DURATION_END_COLUMN = "duration_end";
    private static final String CAPACITY_COLUMN = "capacity";
    private static final String USER_ID_COLUMN = "user_id";
    private static final String ROOM_ID_COLUMN = "rooms_id";

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Order findEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean create(Order entity) {
        return false;
    }

    @Override
    public boolean update(Order entity) {
        return false;
    }
}
