package com.booking.dao;

import com.booking.entity.Entity;

import java.util.List;

public interface Dao<K, T extends Entity> {
    List<T> findAll();
    T findEntityById(K id);
    boolean delete(K id);
    boolean create(T entity);
    boolean update(T entity);
}
