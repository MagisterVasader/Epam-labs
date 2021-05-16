package com.booking;

import com.booking.dao.DaoException;
import com.booking.pool.ConnectionPoolException;

public class Main {
    public static void main(String[] args) throws ConnectionPoolException, DaoException {

        /**
         BillDaoImpl billDao = new BillDaoImpl();
         billDao.setConnection(pool.getConnection());
         OrderDaoImpl orderDao = new OrderDaoImpl();
         orderDao.setConnection(pool.getConnection());
         RoomDaoImpl roomDao = new RoomDaoImpl();
         roomDao.setConnection(pool.getConnection());
         UserDaoImpl userDao = new UserDaoImpl();
         userDao.setConnection(pool.getConnection());

         System.out.println(billDao.read(1));
         System.out.println(orderDao.read(1));
         System.out.println(roomDao.read(1));
         System.out.println(userDao.read(1));
         /**
         Order order = new Order();
         order.setRoomId(10);
         order.setDurationStart(new Date(40000000));
         order.setDurationEnd(new Date(5000000));
         order.setUserId(5);

         Order newOrder = orderDao.read(orderDao.create(order));
         System.out.println(newOrder);

         Bill bill = new Bill();
         bill.setId(newOrder.getId());
         bill.setTotalPrice(2000);

         Room room = new Room();
         room.setComfort(Comfort.LUX);
         room.setCapacity(2);
         room.setFree(true);
         room.setPrice(1000);

         User user = new User();
         user.setRole(Role.CLIENT);
         user.setLogin("login6");
         user.setPassword("password6");


         System.out.println(billDao.read(billDao.create(bill)));
         System.out.println(roomDao.read(roomDao.create(room)));
         System.out.println(userDao.read(userDao.create(user)));
         /**

         Bill bill = billDao.read(5);
         Order order = orderDao.read(5);
         Room room = roomDao.read(11);
         User user = userDao.read(6);

         bill.setTotalPrice(6666);
         order.setRoomId(11);
         room.setPrice(4444);
         user.setPassword("password6666");

         billDao.update(bill);
         orderDao.update(order);
         roomDao.update(room);
         userDao.update(user);
         /**

         userDao.delete(6);
         billDao.delete(5);
         orderDao.delete(5);
         roomDao.delete(11);

         List<Bill> bills = billDao.readAll();
         System.out.println(bills);
         */


    }
}
