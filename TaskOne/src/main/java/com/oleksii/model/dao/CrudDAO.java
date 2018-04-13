package com.oleksii.model.dao;

import java.util.List;

//public interface CrudDAO<T, Id> extends AutoCloseable { // AutoCloseable
public interface CrudDAO<T, Id> { 
    
    void create(T t);
    void update(T t);

    T findOne(Id id);

    void delete(Id id);

    boolean exists(Id id);

    List<T> findAll();
}
