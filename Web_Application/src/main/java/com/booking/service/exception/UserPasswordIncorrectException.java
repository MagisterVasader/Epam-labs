package com.booking.service.exception;

public class UserPasswordIncorrectException extends ServiceException {
    private Integer id;

    public UserPasswordIncorrectException(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}