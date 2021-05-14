package com.booking.dao;

import com.booking.entity.Entity;
public interface Dao<K, T extends Entity> {
    T read(K id) throws DaoException;

    Integer create(T entity) throws DaoException;

    void update(T entity) throws DaoException;

    void delete(K id) throws DaoException;
}
