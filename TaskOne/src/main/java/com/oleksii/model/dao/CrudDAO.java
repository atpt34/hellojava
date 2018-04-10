package com.oleksii.model.dao;

import java.util.List;

public interface CrudDAO<T, Id> {
    T save(T t);
    T findOne(Id id);
    void delete(Id id);
    boolean exists(Id id);
    List<T> findAll();
}
