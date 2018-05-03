package com.oleksii.model.dao;

import com.oleksii.model.entity.Order;

public abstract class DaoFactory {
    
    private static class DaoFactoryHolder {
        static final DaoFactory td = new JdbcDaoFactory();
    }
    
    public static DaoFactory getInstance() {
        return DaoFactoryHolder.td;
    }

    public abstract TravelDAO getTravelDao(); 
    
    public abstract CrudDAO<Order, Integer> getOrderDao(); 

}
