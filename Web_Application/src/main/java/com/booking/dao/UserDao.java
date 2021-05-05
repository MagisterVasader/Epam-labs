package com.booking.dao;

import com.booking.Printable;
import com.booking.connection.DBConnection;
import com.booking.entity.Role;
import com.booking.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<Integer, User> {
    private static final String USER_TABLE = "user";
    private static final String USER_ID_COLUMN = "user_id";
    private static final String LOGIN_COLUMN = "login";
    private static final String PASSWORD_COLUMN = "password";
    private static final String ROLE_COLUMN = "role";
    private static final String NAME_COLUMN = "name";
    private static final String SURNAME_COLUMN = "surname";

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Statement st = DBConnection.getDbConnection().createStatement()) {
            String sqlSelectAll = "SELECT * FROM " + USER_TABLE;
            ResultSet resultSet = st.executeQuery(sqlSelectAll);
            User user;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setRole(Role.valueOf(resultSet.getString(4)));
                user.setName(resultSet.getString(5));
                user.setSurname(resultSet.getString(6));
                users.add(user);
            }
        } catch (SQLException e) {
            Printable.printError(e.getLocalizedMessage());
        }
        return users;
    }

    @Override
    public User findEntityById(Integer id) {
        User user = new User();
        String sqlSelectID = "SELECT " + NAME_COLUMN + "," + SURNAME_COLUMN + "," + LOGIN_COLUMN + " FROM "
                + USER_TABLE + " WHERE " + USER_ID_COLUMN + "=?";
        try (PreparedStatement st = DBConnection.getDbConnection().prepareStatement(sqlSelectID)) {
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            user.setName(resultSet.getString(1));
            user.setSurname(resultSet.getString(2));
            user.setLogin(resultSet.getString(3));

        } catch (SQLException e) {
            Printable.printError(e.getLocalizedMessage());
        }
        return user;
    }

    @Override
    public boolean delete(Integer id) {
        String sqlSelectID = "DELETE FROM " + USER_TABLE + " WHERE " + USER_ID_COLUMN + "=?";
        try (PreparedStatement st = DBConnection.getDbConnection().prepareStatement(sqlSelectID)) {
            st.setInt(1, id);
            if (st.executeUpdate() > 0) {
                Printable.printInfo("Delete success");
                return true;
            }
            return false;
        } catch (SQLException e) {
            Printable.printError(e.getLocalizedMessage());
            return false;
        }
    }

    // ПОГОВОРИТЬ С ВИКОЙ
    @Override
    public boolean create(User user) {
        String sqlCreateUser = "INSERT INTO " + USER_TABLE + " (`"+ USER_ID_COLUMN + "`,`" + LOGIN_COLUMN + "`,`" + PASSWORD_COLUMN +
                "`,`" + ROLE_COLUMN + "`,`" + NAME_COLUMN + "`,`" + SURNAME_COLUMN + "`) VALUES " +
                "('" + user.getId() + "','" + user.getLogin() + "','" + user.getPassword() + "','" + user.getRole() + "','" +
                user.getName() + "','" + user.getSurname() + "');";

        try (PreparedStatement st = DBConnection.getDbConnection().prepareStatement(sqlCreateUser)) {
            if (st.executeUpdate() > 0) {
                Printable.printInfo("Create success");
                return true;
            }
            return false;
        } catch (SQLException e) {
            Printable.printError(e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public boolean update(User user) {
//        String sqlUpdateUser = "UPDATE " + USER_TABLE + " SET " +
//                + +
//                        " WHERE " + USER_ID_COLUMN + "=?";
//        try (PreparedStatement st = DBConnection.getDbConnection().prepareStatement(sqlUpdateUser)) {
//            st.setInt(1,user.getId());
//            if (st.executeUpdate() > 0) {
//                Printable.printInfo("Update success");
//                return true;
//            }
//            return false;
//        } catch (SQLException e) {
//            Printable.printError(e.getLocalizedMessage());
//            return false;
//        }
        return false;
    }
}
