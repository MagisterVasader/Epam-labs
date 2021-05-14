package com.booking.dao;

import com.booking.entity.Room;

import java.util.List;

public interface RoomDao extends Dao<Integer, Room>{
    List<Room> readAll() throws DaoException;

    List<Room> readAllFreeRooms() throws DaoException;
}
