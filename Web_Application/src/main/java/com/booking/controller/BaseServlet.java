package com.booking.controller;

import com.booking.util.MainServiceFactoryImpl;
import com.booking.util.ServiceFactory;

import javax.servlet.http.HttpServlet;

public abstract class BaseServlet extends HttpServlet {
    protected ServiceFactory getFactory() {
        return new MainServiceFactoryImpl();
    }
}
