package com.booking.dao.mysql;

import com.booking.dao.BillDao;
import com.booking.dao.DaoException;
import com.booking.entity.Bill;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BillDaoImpl extends BaseDaoImpl implements BillDao {
    public static final Logger LOGGER = LogManager.getLogger(BillDaoImpl.class);

    @Override
    public Bill read(Integer id) throws DaoException {
        String sql = "SELECT `bill_id`, `total_price` FROM `bill` WHERE `bill_id` = ?";
        ResultSet resultSet = null;
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Bill bill = null;
            if (resultSet.next()) {
                bill = new Bill();
                bill.setId(id);
                bill.setTotalPrice(resultSet.getInt("total_price"));
            }
            LOGGER.info("Read successfully!");
            return bill;
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
    public Integer create(Bill bill) throws DaoException {
        String sql = "INSERT INTO `bill` (`bill_id`,`total_price`) VALUES (?,?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, bill.getId());
            statement.setInt(2, bill.getTotalPrice());
            statement.executeUpdate();
            LOGGER.info("Create successfully!");
            return bill.getId();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Bill bill) throws DaoException {
        String sql = "UPDATE `bill` SET `total_price` = ? WHERE `bill_id` = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, bill.getTotalPrice());
            statement.setInt(2, bill.getId());
            statement.executeUpdate();
            LOGGER.info("Update successfully!");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        String sql = "DELETE FROM `bill` WHERE `bill_id` = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            LOGGER.info("Delete successfully!");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Bill> readAll() throws DaoException {
        String sql = "SELECT `bill_id`, `total_price` FROM `bill`";
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Bill> bills = new ArrayList<>();
            while (resultSet.next()) {
                Bill bill = new Bill();
                bill.setId(resultSet.getInt("bill_id"));
                bill.setTotalPrice(resultSet.getInt("total_price"));
                bills.add(bill);
            }
            LOGGER.info("BillDaoImpl: ReadAll successfully!");
            return bills;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
