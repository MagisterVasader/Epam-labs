package com.booking.dao.mysql;

import com.booking.dao.DaoException;
import com.booking.dao.OrderDao;
import com.booking.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {
    public static final Logger LOGGER = LogManager.getLogger(OrderDaoImpl.class);

    @Override
    public List<Order> readAll() throws DaoException {
        String sql = "SELECT `order_id`, `room_id`, `duration_start`, `duration_end`, `user_id` FROM `order`";
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt(1));
                order.getRoom().setId(resultSet.getInt(2));
                order.setDurationStart(resultSet.getDate(3));
                order.setDurationEnd(resultSet.getDate(4));
                order.getUser().setId(resultSet.getInt(5));
                orders.add(order);
            }
            LOGGER.info("ReadAll successfully!");
            return orders;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

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
                order.getRoom().setId(resultSet.getInt("room_id"));
                order.setDurationStart(resultSet.getDate("duration_start"));
                order.setDurationEnd(resultSet.getDate("duration_end"));
                order.getUser().setId(resultSet.getInt("user_id"));
            }
            LOGGER.info("Read successfully!");
            return order;
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
    public Integer create(Order order) throws DaoException {
        String sql = "INSERT INTO `order` (`room_id`,`duration_start`,`duration_end`,`user_id`) VALUES (?,?,?,?)";
        ResultSet resultSet = null;
        try (PreparedStatement statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, order.getRoom().getId());
            statement.setDate(2, order.getDurationStart());
            statement.setDate(3, order.getDurationEnd());
            statement.setInt(4, order.getUser().getId());
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
    public void update(Order order) throws DaoException {
        String sql = "UPDATE `order` SET `room_id` = ? ,`duration_start` = ?, `duration_end` = ?, `user_id` = ? WHERE `order_id` = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, order.getRoom().getId());
            statement.setDate(2, order.getDurationStart());
            statement.setDate(3, order.getDurationEnd());
            statement.setInt(4, order.getUser().getId());
            statement.setInt(5, order.getId());
            statement.executeUpdate();
            LOGGER.info("Update successfully!");
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
            LOGGER.info("Delete successfully!");
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
                order.getRoom().setId(resultSet.getInt("room_id"));
                order.setDurationStart(resultSet.getDate("duration_start"));
                order.setDurationEnd(resultSet.getDate("duration_end"));
                order.getUser().setId(id);
                orders.add(order);
            }
            LOGGER.info("ReadAllOrdersByUserId successfully!");
            return orders;
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
}
