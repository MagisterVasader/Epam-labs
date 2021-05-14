package com.booking.service;

import com.booking.service.exception.TransactionException;

public interface Transaction {
    void start() throws TransactionException;
    void commit() throws TransactionException;

    void rollback() throws TransactionException;
}