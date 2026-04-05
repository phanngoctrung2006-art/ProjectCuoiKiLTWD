package com.cafe.dao;

import java.util.List;

public interface GenericDAO<T, ID> {
    T save(T entity);

    T update(T entity);

    void delete(ID id);

    T findById(ID id);

    List<T> findAll();

    long count();

    boolean existsById(ID id);
}
