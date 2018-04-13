package com.oleksii.model.dao;

import com.oleksii.model.entity.Order;
import com.oleksii.model.entity.Travel;

public abstract class DaoFactory {
    
    private static DaoFactory td;
    
    public static DaoFactory getInstance() {
        if (td == null) {
            synchronized (DaoFactory.class) {
                if (td == null) {
                    td = new JdbcDaoFactory();
                }
            }
        }
        return td;
    }

    public abstract CrudDAO<Travel, Integer> getTravelDao(); 
    
    public abstract CrudDAO<Order, Integer> getOrderDao(); 

}
