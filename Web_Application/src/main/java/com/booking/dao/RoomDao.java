package com.booking.dao;

import com.booking.entity.Room;

import java.util.List;

public class RoomDao implements Dao<Integer, Room>{

    @Override
    public List<Room> findAll() {
        return null;
    }

    @Override
    public Room findEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean create(Room entity) {
        return false;
    }

    @Override
    public boolean update(Room entity) {
        return false;
    }
}
