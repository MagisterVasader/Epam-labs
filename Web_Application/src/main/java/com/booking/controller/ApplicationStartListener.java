package com.booking.controller;

import com.booking.Printable;
import com.booking.pool.ConnectionPool;
import com.booking.pool.ConnectionPoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.TimeZone;

public class ApplicationStartListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            String dbDriver = "com.mysql.cj.jdbc.Driver";
            String dbName = "web_app_epam?useUnicode=true&serverTimezone=" + TimeZone.getDefault().getID() + "&useSSL=false&allowPublicKeyRetrieval=true";
            String dbUser = "root";
            String dbPassword = "asaa220708";
            String connectionString = "jdbc:mysql://localhost:3306/" + dbName;

            ConnectionPool pool = ConnectionPool.getInstance();
            pool.init(dbDriver, connectionString, dbUser, dbPassword, 5, 100, 0);
        } catch (ConnectionPoolException e) {
            Printable.printError("Can't initialize class " + ConnectionPool.class.getName());
        }
    }
}