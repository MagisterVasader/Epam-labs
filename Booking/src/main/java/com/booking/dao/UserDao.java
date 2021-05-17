package com.booking.dao;

import com.booking.entity.User;

import java.util.List;

public interface UserDao extends Dao<Integer, User> {
    List<User> readAll() throws DaoException;

    Boolean readByLogin(String login) throws DaoException;

    User readByLoginAndPassword(String login, String password) throws DaoException;
}
