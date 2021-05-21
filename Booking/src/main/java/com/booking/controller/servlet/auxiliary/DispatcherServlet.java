package com.booking.controller.servlet.auxiliary;

import com.booking.controller.servlet.Forward;
import com.booking.controller.servlet.Servlet;
import com.booking.controller.servlet.ServletFactory;
import com.booking.util.MainServiceFactoryImpl;
import com.booking.util.ServiceFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }

    public ServiceFactory getServiceFactory() {
        return new MainServiceFactoryImpl();
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String context = req.getContextPath();

        int postfixIndex = url.lastIndexOf(".html");
        if(postfixIndex != -1) {
            url = url.substring(context.length(), postfixIndex);
        } else {
            url = url.substring(context.length());
        }

        Servlet servlet = ServletFactory.getServlet(url);
        Forward forward = null;

        if(servlet != null) {
            try(ServiceFactory factory = getServiceFactory()) {
                servlet.setServiceFactory(factory);
                forward = servlet.execute(req, resp);
            } catch(Exception e) {
                throw new ServletException(e);
            }
        }

        if(forward != null && forward.isRedirect()) {
            resp.sendRedirect(context + forward.getUrl());
        } else {
            if(forward != null && forward.getUrl() != null) {
                url = forward.getUrl();
            }
            req.getRequestDispatcher("/WEB-INF/jsp" + url + ".jsp").forward(req, resp);
        }
    }
}
