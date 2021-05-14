package com.booking.controller;

import com.booking.Printable;
import com.booking.util.Connector;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationStartListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            Connector.init("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/epam_project_db?useUnicode=true&characterEncoding=UTF-8", "root", "");
        } catch (ClassNotFoundException e) {
            Printable.printError("Can't initialize class " + Connector.class.getName());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}