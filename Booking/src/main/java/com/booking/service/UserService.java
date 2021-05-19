package com.booking.service;

import com.booking.entity.User;
import com.booking.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    User readById(Integer id) throws ServiceException;

    List<User> readAll() throws ServiceException;

    User readByLoginAndPassword(String login,String password) throws ServiceException;

    void save(User user) throws ServiceException;

    void delete(Integer id) throws ServiceException;
}
