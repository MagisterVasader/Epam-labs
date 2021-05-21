package com.booking.dao.mysql;

import com.booking.dao.DaoException;
import com.booking.dao.RoomDao;
import com.booking.entity.Comfort;
import com.booking.entity.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl extends BaseDaoImpl implements RoomDao {
    public static final Logger LOGGER = LogManager.getLogger(RoomDaoImpl.class);

    @Override
    public Room read(Integer id) throws DaoException {
        String sql = "SELECT `comfort`, `price`, `free`,`capacity` FROM `room` WHERE `room_id` = ?";
        ResultSet resultSet = null;
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Room room = null;
            if (resultSet.next()) {
                room = new Room();
                room.setId(id);
                room.setComfort(Comfort.valueOf(resultSet.getString(1)));
                room.setPrice(resultSet.getInt(2));
                room.setFree(resultSet.getInt(3) == 0);
                room.setCapacity(resultSet.getInt(4));
            }
            LOGGER.info("Read successfully!");
            return room;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public Integer create(Room room) throws DaoException {
        String sql = "INSERT INTO `room` (`comfort`,`price`,`free`,`capacity`) VALUES (?,?,?,?)";
        ResultSet resultSet = null;
        try (PreparedStatement statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, room.getComfort().toString());
            statement.setInt(2, room.getPrice());
            if (room.getFree().equals(Boolean.TRUE)) {
                statement.setInt(3, 0);
            } else {
                statement.setInt(3, 1);
            }
            statement.setInt(4, room.getCapacity());
            statement.executeUpdate();

            Integer id = null;
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            LOGGER.info("Create successfully!");
            return id;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public void update(Room room) throws DaoException {
        String sql = "UPDATE `room` SET `comfort` = ?,`price` = ?,`free` = ?,`capacity` = ? WHERE `room_id` = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, room.getComfort().toString());
            statement.setInt(2, room.getPrice());
            if (room.getFree().equals(Boolean.TRUE)) {
                statement.setInt(3, 0);
            } else {
                statement.setInt(3, 1);
            }
            statement.setInt(4, room.getCapacity());
            statement.setInt(5, room.getId());
            statement.executeUpdate();
            LOGGER.info("Update successfully!");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        String sql = "DELETE FROM `room` WHERE `room_id` = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            LOGGER.info("Delete successfully!");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Room> readAll() throws DaoException {
        String sql = "SELECT `room_id`, `comfort`, `price`, `free`, `capacity` FROM `room`";
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Room> rooms = new ArrayList<>();
            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("room_id"));
                room.setComfort(Comfort.valueOf(resultSet.getString("comfort")));
                room.setPrice(resultSet.getInt("price"));
                room.setFree(resultSet.getInt("free") == 0);
                room.setCapacity(resultSet.getInt("capacity"));
                rooms.add(room);
            }
            LOGGER.info("ReadAll successfully!");
            return rooms;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Room> readAllFreeRooms() throws DaoException {
        String sql = "SELECT `room_id`, `comfort`, `price`,`capacity` FROM `room` WHERE `free` = 0";
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Room> rooms = new ArrayList<>();
            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("room_id"));
                room.setComfort(Comfort.valueOf(resultSet.getString("comfort")));
                room.setPrice(resultSet.getInt("price"));
                room.setFree(true);
                room.setCapacity(resultSet.getInt("capacity"));
                rooms.add(room);
            }
            LOGGER.info("ReadAllFreeRooms successfully!");
            return rooms;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
