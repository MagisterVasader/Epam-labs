package com.booking.service.logic;

import com.booking.Printable;
import com.booking.dao.DaoException;
import com.booking.dao.UserDao;
import com.booking.entity.User;
import com.booking.service.UserService;
import com.booking.service.exception.ServiceException;
import com.booking.service.exception.UserLoginNotUniqueException;
import com.booking.service.exception.UserNotExistsException;
import com.booking.service.exception.UserPasswordIncorrectException;

import java.util.List;

public class UserServiceImpl extends BaseService implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findById(Integer id) throws ServiceException {
        try {
            return userDao.read(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(User user) throws ServiceException {
        try {
            getTransaction().start();
            if(user.getId() != 0) {
                User storedUser = userDao.read(user.getId());
                if(storedUser != null) {
                    user.setPassword(storedUser.getPassword());
                    if(storedUser.getLogin().equals(user.getLogin()) || Boolean.TRUE.equals(userDao.readByLogin(user.getLogin()))) {
                        userDao.update(user);
                    } else {
                        throw new UserLoginNotUniqueException(user.getLogin());
                    }
                } else {
                    throw new UserNotExistsException(user.getId());
                }
            } else {
                if(Boolean.TRUE.equals(userDao.readByLogin(user.getLogin()))) {
                    Integer id = userDao.create(user);
                    user.setId(id);
                } else {
                    throw new UserLoginNotUniqueException(user.getLogin());
                }
            }
            getTransaction().commit();
        } catch(DaoException e) {
            try {
                getTransaction().rollback();
            } catch(ServiceException e1) {
                Printable.printError(e1.getLocalizedMessage());
            }
            throw new ServiceException(e);
        } catch(ServiceException e) {
            try {
                getTransaction().rollback();
            } catch(ServiceException e1) {
                Printable.printError(e1.getLocalizedMessage());
            }
            throw e;
        }
    }

    @Override
    public void changePassword(Integer userId, String oldPassword, String newPassword) throws ServiceException {
        try {
            getTransaction().start();
            User user = userDao.read(userId);
            if(user != null) {
                if(user.getPassword().equals(oldPassword)) {
                    user.setPassword(newPassword);
                    userDao.update(user);
                } else {
                    throw new UserPasswordIncorrectException(user.getId());
                }
            } else {
                throw new UserNotExistsException(userId);
            }
            getTransaction().commit();
        } catch(DaoException e) {
            try {
                getTransaction().rollback();
            } catch(ServiceException e1) {
                Printable.printError(e1.getLocalizedMessage());
            }
            throw new ServiceException(e);
        } catch(ServiceException e) {
            try {
                getTransaction().rollback();
            } catch(ServiceException e1) {
                Printable.printError(e1.getLocalizedMessage());
            }
            throw e;
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            userDao.delete(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}