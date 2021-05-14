package com.booking.service;

import com.booking.entity.User;
import com.booking.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    User findById(Integer id) throws ServiceException;

    List<User> findAll() throws ServiceException;

    void save(User user) throws ServiceException;

    void changePassword(Integer userId, String oldPassword, String newPassword) throws ServiceException;

    void delete(Integer id) throws ServiceException;
}
