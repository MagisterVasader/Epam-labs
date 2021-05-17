package com.booking.controller;

import com.booking.Printable;
import com.booking.pool.ConnectionPool;
import com.booking.pool.ConnectionPoolException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationStartListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            ServletContext servletContext = event.getServletContext();
            String jdbcDriver = servletContext.getInitParameter("jdbc-driver");
            String jdbcUrl    = servletContext.getInitParameter("jdbc-url");
            String jdbcUser = servletContext.getInitParameter("jdbc-username");
            String jdbcPassword = servletContext.getInitParameter("jdbc-password");

            ConnectionPool pool = ConnectionPool.getInstance();
            pool.init(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword, 5, 100, 0);
        } catch (ConnectionPoolException e) {
            Printable.printFatal("Can't initialize class " + ConnectionPool.class.getName(),e);
        }
    }
}