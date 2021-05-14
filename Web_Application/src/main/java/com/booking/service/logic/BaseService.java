package com.booking.service.logic;

import com.booking.service.Transaction;

public abstract class BaseService {
    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
