package com.booking.service.logic;

import com.booking.Printable;
import com.booking.dao.DaoException;
import com.booking.dao.RoomDao;
import com.booking.entity.Room;
import com.booking.service.RoomService;
import com.booking.service.exception.ServiceException;

import java.util.List;

public class RoomServiceImpl extends BaseService implements RoomService {
    private RoomDao roomDao;

    public void setRoomDao(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public List<Room> readAllFreeRooms() throws ServiceException {
        try {
            return roomDao.readAllFreeRooms();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Room> readAll() throws ServiceException {
        try {
            return roomDao.readAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Room room) throws ServiceException {
        try {
            getTransaction().start();
            if (room.getId() == null) {
                roomDao.create(room);
            } else {
                roomDao.update(room);
            }
            getTransaction().commit();
        } catch (DaoException | ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
                Printable.printError(e1.getLocalizedMessage());
            }
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            roomDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
