package com.booking.controller.servlet;

public class Forward {
    private final String url;
    private final boolean redirect;

    public Forward(String url, boolean redirect) {
        this.url = url;
        this.redirect = redirect;
    }

    public Forward(String url) {
        this(url, true);
    }

    public String getUrl() {
        return url;
    }

    public boolean isRedirect() {
        return redirect;
    }
}