package com.booking.service.logic;

import com.booking.dao.DaoException;
import com.booking.dao.UserDao;
import com.booking.entity.User;
import com.booking.service.UserService;
import com.booking.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl extends BaseService implements UserService {
    public static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User readByLoginAndPassword(String login, String password) throws ServiceException {
        try {
            return userDao.readByLoginAndPassword(login,password);
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public User readById(Integer id) throws ServiceException {
        try {
            return userDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> readAll() throws ServiceException {
        try {
            return userDao.readAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(User user) throws ServiceException {
        try {
            getTransaction().start();
            if (user.getId() == null) {
                userDao.create(user);
            } else {
                userDao.update(user);
            }
            getTransaction().commit();
        } catch (DaoException | ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
                LOGGER.error(e1);
            }
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            userDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}