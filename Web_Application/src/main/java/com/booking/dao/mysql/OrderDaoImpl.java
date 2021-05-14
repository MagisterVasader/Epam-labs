package com.booking.dao.mysql;

import com.booking.Printable;
import com.booking.dao.DaoException;
import com.booking.dao.OrderDao;
import com.booking.entity.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {
    @Override
    public Order read(Integer id) throws DaoException {
        String sql = "SELECT `room_id`, `duration_start`, `duration_end`, `user_id` FROM `order` WHERE `order_id` = ?";
        ResultSet resultSet = null;
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Order order = null;
            if (resultSet.next()) {
                order = new Order();
                order.setId(id);
                order.setRoomId(resultSet.getInt("room_id"));
                order.setDurationStart(resultSet.getDate("duration_start"));
                order.setDurationEnd(resultSet.getDate("duration_end"));
                order.setUserId(resultSet.getInt("user_id"));
            }
            Printable.printInfo("OrderDaoImpl: Read successfully!");
            return order;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (Exception e) {
                Printable.printError(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public Integer create(Order order) throws DaoException {
        String sql = "INSERT INTO `order` (`room_id`,`duration_start`,`duration_end`,`user_id`) VALUES (?,?,?,?)";
        ResultSet resultSet = null;
        try (PreparedStatement statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, order.getRoomId());
            statement.setDate(2, order.getDurationStart());
            statement.setDate(3, order.getDurationEnd());
            statement.setInt(4, order.getUserId());
            statement.executeUpdate();
            Integer id = null;
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            Printable.printInfo("OrderDaoImpl: Create successfully!");
            return id;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (Exception e) {
                Printable.printError(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void update(Order order) throws DaoException {
        String sql = "UPDATE `order` SET `room_id` = ? ,`duration_start` = ?, `duration_end` = ?, `user_id` = ? WHERE `order_id` = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, order.getRoomId());
            statement.setDate(2, order.getDurationStart());
            statement.setDate(3, order.getDurationEnd());
            statement.setInt(4, order.getUserId());
            statement.setInt(5, order.getId());
            statement.executeUpdate();
            Printable.printInfo("OrderDaoImpl: Update successfully!");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        String sql = "DELETE FROM `order` WHERE `order_id` = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            Printable.printInfo("OrderDaoImpl: Delete successfully!");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> readAllOrdersByUserId(Integer id) throws DaoException {
        String sql = "SELECT `order_id`, `room_id`, `duration_start`, `duration_end` FROM `order` WHERE `user_id` = ?";
        ResultSet resultSet = null;
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("order_id"));
                order.setRoomId(resultSet.getInt("room_id"));
                order.setDurationStart(resultSet.getDate("duration_start"));
                order.setDurationEnd(resultSet.getDate("duration_end"));
                order.setUserId(id);
                orders.add(order);
            }
            Printable.printInfo("OrderDaoImpl: ReadAllOrdersByUserId successfully!");
            return orders;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (Exception e) {
                Printable.printError(e.getLocalizedMessage());
            }
        }
    }
}
