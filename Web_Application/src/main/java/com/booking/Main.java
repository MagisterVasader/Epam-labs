package com.booking;

import com.booking.dao.UserDao;
import com.booking.entity.Role;
import com.booking.entity.User;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        User user = new User();
        user.setId(6);
        user.setLogin("login6");
        user.setPassword("password6");
        user.setRole(Role.ADMIN);
        user.setName("name6");
        user.setSurname("surname6");
        userDao.create(user);
    }
}
