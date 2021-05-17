package com.booking.service;

import com.booking.entity.Room;
import com.booking.service.exception.ServiceException;

import java.util.List;

public interface RoomService {
    List<Room> readAllFreeRooms() throws ServiceException;

    List<Room> readAll() throws ServiceException;

    void save(Room room) throws ServiceException;

    void delete(Integer id) throws ServiceException;
}
